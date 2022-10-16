package io.redandroid.data.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haroldadmin.cnradapter.NetworkResponse
import io.redandroid.data.converter.HousesConverter
import io.redandroid.data.model.House
import io.redandroid.network.api.HouseService
import okhttp3.Headers
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * This class is responsible for fetching the next batch of houses from the API.
 */
class HousePagingSource @Inject constructor(
    private val houseService: HouseService
) : PagingSource<Int, House>() {

    companion object {
        /**
         * the page key of the first page of data
         */
        private const val STARTING_PAGE_INDEX = 1

        /**
         * the GET param key that holds the number for the "next" page key
         */
        private const val PAGE_KEY_QUERY_PARAM = "page"

        /**
         * The header key where we can find the "next" page key
         */
        private const val PAGING_HEADER_KEY = "Link"
    }

    /**
     * Regex pattern to match a URL
     */
    private val urlPattern: Pattern = Pattern.compile(
        "(?:^|[\\W])((ht|f)tp(s?)://|www\\.)"
                + "(([\\w\\-]+\\.)+?([\\w\\-.~]+/?)*"
                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]*$~@!:/{};']*)",
        Pattern.CASE_INSENSITIVE or Pattern.MULTILINE or Pattern.DOTALL
    )


    /**
     * This method is loading the next batch of data from the API.
     *
     * @return A [LoadResult] also contains information about the current page position
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, House> {

        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            when (val response = houseService.getHouses(position)) {

                is NetworkResponse.Success -> {

                    val houses = HousesConverter.convert(response.body)

                    val nextKey = when {
                        houses.isEmpty() -> null
                        else -> extractNextKey(response.headers)
                    }

                    Timber.d("+++ got ${houses.size} houses for pageKey $position. Next key is $nextKey")

                    LoadResult.Page(
                        data = houses,
                        prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                        nextKey = nextKey
                    )
                }

                is NetworkResponse.ServerError -> {
                    val throwable = response.error ?: Throwable(response.response?.message() ?: "Unknown Server Error")
                    LoadResult.Error(throwable)
                }
                is NetworkResponse.NetworkError -> {
                    val throwable = response.error
                    LoadResult.Error(throwable)
                }
                is NetworkResponse.UnknownError -> {
                    val throwable = response.error
                    LoadResult.Error(throwable)
                }
            }


        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    /**
     *
     * Extracts the page key for the next page.
     *
     * The Game Of Thrones API returns this information in the response headers.
     * The header key [PAGING_HEADER_KEY] contains 3 html links, each for a different
     * position in the list (next, first, last). This method extracts the "next" link.
     *
     * @return the number for the next page to fetch.
     */
    private fun extractNextKey(headers: Headers): Int {
        // get all links from headers
        val links = headers[PAGING_HEADER_KEY] ?: return STARTING_PAGE_INDEX

        // split all links by comma-delimiter and find the one we need
        val lineWithNextLink = links
            .split(",")
            .firstOrNull { it.contains("rel=\"next\"") } ?: return STARTING_PAGE_INDEX

        // extract the url from the previously found String
        val matcher = urlPattern.matcher(lineWithNextLink)
        if (!matcher.find()) return 1

        // get the page query parameter from the url
        val url = lineWithNextLink.substring(matcher.start(), matcher.end())
        val uri = Uri.parse(url)
        val pageKey = uri.getQueryParameter(PAGE_KEY_QUERY_PARAM) ?: return STARTING_PAGE_INDEX

        // return the pageKey as Integer
        return Integer.parseInt(pageKey)
    }

    /**
     * The refresh key is used for the initial load of the next PagingSource, after invalidation.
     */
    override fun getRefreshKey(state: PagingState<Int, House>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}