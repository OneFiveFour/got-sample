# What would change in large-scale apps?

* GameOfThronesService should be an interface that is then implemented in an XXXImpl class. This would especially be helpful for unit testing.
* For more complex APIs it would make sense to create separate services for separate API topics. The Retrofit instance would then be an externalized object.
* Also for more complex APIs, the NetworkModule should be refactored into several modules each with a specific responsibility.

# What was left out for brevity

* I did not follow the usual git flow.
* The amount of UnitTests was reduced to just exemplary samples.
* Since this app is fairly simple, the UseCase layer was skipped. ViewModel talk directly to the data/domain layer.
* THe ViewModels expose the data directly as Flow and "as-it-is". Especially when working with Compose, they would expose them as UI state instead (StateFlow or SharedFlow).
* The fallback error messages are not I18Nized.

# Other comments

The list view is done the old-school way of using a xml layout with view/databinding. The detail view is done using Compose.
Since we are currently in a transition phase between the two, I decided to give sample code for both of them.

The Compose-way of navigating the app by using routes does not work together with Fragments.
This is why the app is currently still using the navigation components library.

The pageSize is hardcoded to 50.
If it was a requirement to make this dynamic, the HousesRemoteMediator would need to implement more logic to
fetch and store the "nextPageKey" in the house_paging_key database table.

I wanted to add UnitTests for the HouseRemoteMediator as described here: https://developer.android.com/topic/libraries/architecture/paging/test#remotemediator-tests
Unfortunately I got stuck in dependency conflicts, because the paging library force uses for some reason androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0
while I want to use the newest version androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1. I tried to exclude this dep from the paging lib as well as enforcing my version,
but after several sad attempts, I didn't want to spend more time on that and move on with the project...

# Todo

* StartUp Lib
* Hilt