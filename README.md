# Code Overview

The app is divided into 3 modules:

1. **APP**:
This is the UI layer of the app. It holds and is responsible for everything related to what is visible to the user.
The app module has access to the data module, but not to the network module.
ViewModels are fetching data from the data module and prepare that data into whatever form is wanted by their according Fragment/Composable

2. **DATA**
The data layer connects the payloads coming from the outside/server and the payloads used within the app domain.
It transforms foreign domain models into our own models and takes care about when & what API endpoint is called.
Repositories expose the converted data classes to the UI layer. To do so, they call the appropriate api call in the network layer.
The data layer has access to the network layer, but not to the UI layer.
The data layer is mostly pure Kotlin code. Only in rare cases some Android-specific dependencies should be found here. For example using the Paging Library.

3. **NETWORK**
As the name suggests, this layer is responsible for getting data from the network. 
It is the one that is actually executing the API network calls. It exposes these server responses to the data layer.
The network layer has neither access to the data layer nor to the UI layer. It stands on its own so to say.
It also is written purely in Kotlin code. No Android-specific must be found here.

# Features

* Paged loading of the API calls using the Android Paging Library 3.0
* Loading indicator and Swipe To Refresh functionality
* FastScrolling by alphabet letters
* Letter separators at the beginning of each new list section
* Custom Color Theme that allows using the clients naming conventions and is not necessarily bound to Material restrictions.
* Both: old-school XML + MVVM for one screen and modern Compose architecture for the other screen
* Chained API Calls using different ways of coroutines (First get the house and then its Lord and Sworn Members)

# What would change in large-scale apps?

* GameOfThronesService should be an interface that is then implemented in an XXXImpl class. This would especially be helpful for unit testing.
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

* Due to the mix of XML and Compose it was not possible to create fancy screen transitions with a sensible amount of effort.

# Todo

* StartUp Lib
* Gradle Release Code
* maybe pipeline code
* dark theme
* comment all composables