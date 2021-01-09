# WeatherApp

An application i created as part of a technical test to show the 4 day weather forecast. There is an automcomplete search bar
with predictions using the Google maps API, the weather API is provided by openweather:https://openweathermap.org/api

Libraries Used:

- RXKotlin 
- Dagger2 
- Picasso
- Butterknife 
- Retrofit 
- IO network library
- Room Database 

i have used code from this project:https://github.com/alphamu/WeatheringHeights

i used code realted to:
- The network fragment helper
- Search autocomplete Adapter 

There are Three Screens in my Application:

## 1st Screen - Weather Search and Saved searches 

There is an autocomplete search bar, searches are initiated after the third character is entered. 
Once a search has been initiated the app will take you to the 2nd screen: search results.

On this screen you also have a recent searches list which displays the recent locations searched for, if an element from this list is selected it will initiate a search for that particular city again.

## 2nd Screen - Basic Weather Infomation

This screen which will list four day weather in increments of 3 hours. 
For more detailed information the user can press the arrow to the right of the screen to be shown the 3rd screen: Detail View.

TODO:  this screen is only meant to list one list element for each day, with min-max temp, but at the moment its showing in 3-hour increments.

Solution: I need to filter this list and recalculate the min-max for the day and display this information, or find a new api endpoint which returns data in the required format.

## 3rd Screen - Detailed Weather Information

Same as above but with some more details.


