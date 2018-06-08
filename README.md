# android-kotlin-study

Weather forecast app. Pertty simple, one screen. 
Main point is to show kotlin in action, how to deal with kotlin and how to use popular android (and not only) libraries with kotlin.

Code is divided by 4 main parts/layers.
Data, Domain, DI, Presentation.
In Data package there is code that deal with data, code that communicate with API.
In Domain package - code that implement domain logic (it's pretty thin here hence this app contain no logic)
In DI package - everything related to dependency injection (Modules, Components, Scopes)
In Presentation package - code that is responsible for representing domain data. 

MVP pattern is used on Presentation layer. Where View is Fragment or Actitivity, Presenter is essence that bind presentation layer with domain. In this example Model is situated already on domain layer and it is implemented via UseCase classes.


<b>Libraries used</b>

Data layer: JxJava, Retrofit.

Presentation layer: Glide.

DI: Dagger2
