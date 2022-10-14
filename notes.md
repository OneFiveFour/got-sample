# What could be done cleaner?

* GameOfThronesService should be an interface that is then implemented in an XXXImpl class. This would especially be helpful for unit testing.
* For more complex APIs it would make sense to create separate services for separate API topics. The Retrofit instance would then be an externalized object.
* Also for more complex APIs, the NetworkModule should be refactored into several modules each with a specific responsibility.

# Todo

* StartUp Lib
* Hilt