# What could be done cleaner?

* GameOfThronesService should be an interface that is then implemented in an XXXImpl class. This would especially be helpful for unit testing.
* For more complex APIs it would make sense to create separate services for separate API topics. The Retrofit instance would then be an externalized object.
* Also for more complex APIs, the NetworkModule should be refactored into several modules each with a specific responsibility.

# What was left out for brevity

* I did not follow the usual git flow.
* The amount of UnitTests was reduced to just exemplary samples.
* Since this app is fairly simple, the UseCase layer was skipped. ViewModel talk directly to the data/domain layer.

# Todo

* StartUp Lib
* Hilt