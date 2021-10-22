# Waracle-CodeTest
All Requirements completed as per documentation provided. 

# Architecture Design
MVVM
![Alt text](app/docs/images/mvvm_architecture.png?raw=true "MVVM Architecture")

LiveData: Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

ViewModel: ViewModel objects are also lifecycle-aware. They are automatically cleared when the Lifecycle they are observing gets permanently destroyed.
The purpose of the ViewModel is to acquire and keep the information that is necessary for an Activity or a Fragment. The Activity or the Fragment should be able to observe changes in the ViewModel. ViewModels usually expose this information via LiveData or Android Data Binding.


# Libraries Used
Dagger 
Coroutines
Retrofit 
GSON
Robolectric
Mockito
Bindingcollectionadapter2

# Further Improvements
- Refactor project to leverage the Paging library.
- Abstract the Searches logic 
