# Trend_Hub
  Trend Hub is an androdi application which consists of an trending repositories, which is retrieved from **apiary.io**

## Product Specifications
  1. Consists a List of Trending Repositories
  2. Consists of search filter to filter the list according to our search input
  3. Consists of Pull to refresh mode, so that can refresh and fetch new inputs.
  4. Supports both online and offline mode, in which once data is received from api , it stored locally.
  5. Progress bar , which helps to note the Loading state.
  6. Network manager, in which helps to note the internet connection, if no internet we can view in offline mode

## Technical Specifications
 **Api Used** : https://private-29133f-githubtrendingapi.apiary-mock.com/repositories (from apiary.io)
 
 **Library Used** : Retrofit 2, Room, LiveData
 
 **Architecture** : MVVM (Model-View-ViewModel)
