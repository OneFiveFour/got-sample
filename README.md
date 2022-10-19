# Features

* Paged loading of the API calls using the Android Paging Library 3.0
* Loading indicator and Swipe To Refresh functionality
* FastScrolling by alphabet letters
* Letter separators at the beginning of each new list section
* Color Theme following the Material Guidelines
* Mixture of old-school XML + MVVM and modern Compose architecture

# What would change in large-scale apps?

* GameOfThronesService should be an interface that is then implemented in an XXXImpl class. This would especially be helpful for unit testing.
* For more complex APIs it would make sense to create separate services for separate API topics. The Retrofit instance would then be an externalized object.
* Also for more complex APIs, the NetworkModule should be refactored into several modules each with a specific responsibility.

# What was left out for brevity

* I did not follow the usual git flow, just a main branch here.
* The amount of UnitTests was reduced to just exemplary samples.
* Since this app is fairly simple, the UseCase layer was skipped. ViewModels can talk directly to the data/domain layer.
* The fallback error messages are not I18Nized.

# Other comments

* The list view is done the old-school way of using a xml layout with view/databinding. The detail view is done using Compose.
Since we are currently in a transition phase between the two, I decided to give sample code for both of them.

* The way of navigating a Compose app by using routes does not work together with Fragments.
This is why the app is currently still using the navigation components library.

* The pageSize is hardcoded to 50.
If it was a requirement to make this dynamic, the HousesRemoteMediator would need to implement additional logic to
fetch and store the "nextPageKey" in the house_paging_key database table.

* Since tha page key is a simple integer (when pageSize is fixed), it is okay that Room is creating my PagingSource<Int, House>.
If I was required to parse the next pageKey from the server response (it is "hidden" in the response header), you can find an implementation for that here:
[https://github.com/OneFiveFour/got-sample/blob/c2c59fb0519a5b9278e1f413fe5a54a1448949c6/data/src/main/java/io/redandroid/data/paging/HousePagingSource.kt]

# Todo

* StartUp Lib
* App Icon