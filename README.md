# GoodWallTask

Project build using

Kotlin - First class and official programming language for Android development.

Coroutines/Rx - For asynchronous and more..

Flows - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. For example, you can use a flow to receive live updates from a database.

Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously. The emitted values must be of the same type. For example, a Flow<Int> is a flow that emits integer values.

Architecture
Clean Architecture & 
MVVM pattern (View - ViewModel - Model)

Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.

LiveData - Data objects that notify views when the underlying database changes.

ViewModel - Stores UI-related data that isn't destroyed on UI changes.

ViewBinding - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.

Navigation - Android Jetpack's Navigation component refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app.

Room - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. In particular, Room provides the following benefits:

Compile-time verification of SQL queries.
Convenience annotations that minimize repetitive and error-prone boilerplate code.
Streamlined database migration paths.

The most common use case is to cache relevant pieces of data so that when the device cannot access the network, the user can still browse that content while they are offline.

Dependency Injection
Hilt - Easier way to incorporate Dagger DI into Android apps.

Retrofit2 - constructing the REST API

OkHttp3 - implementing interceptor, logging and mocking web server

Picasso - loading images.

Moshi - JSON representation

Material Components for Android - Modular and customizable Material Design UI components for Android.

Clean Architecture
Clean architecture promotes separation of concerns, making the code loosely coupled. This results in a more testable and flexible code. This approach divides the project in 3 modules: presentation, data and domain.

Presentation: Layer with the Android Framework, the MVVM pattern and the DI module. Depends on domain to access the use cases and on di, to inject dependencies.
Domain: Layer with the business logic. Contains the use cases, in charge of calling the correct repository or data member.
Data: Layer with the responsibility of selecting the proper data source for the domain layer. It contains the implementations of the repositories declared in the domain layer. It may, for example, check if the data in a database is up to date, and retrieve it from a service if it’s not.
As there isn’t a single way to implement Clean Architecture, this could affront changes in the future.

Domain
Business logic can be defined as the core operations done by the application. The domain tries to encapsulate this business logic, to make it agnostic of its context. The components of the domain are:

Entities: Simple classes that represent the objects in which the business is based.
Repositories: Interfaces used by the use cases. Implemented in the data layer.
Use cases: Also called interactors. They enclose a single action, like getting data from a database or posting to a service. They use the repositories to resolve the action they are supposed to do. They usually override the operator “invoke”, so they can be called as a function.
Data
The data layer is the implementation of all the repositories declared by the domain layer. This acts as a support of the business layer, from where it obtains the data needed to be shown in the UI.

Data is also an Android module so, besides databases and network requests, it can provide locations, bluetooth access, gyroscope data, among other information respective to the device. This could be separated in another module to provide independency from the framework.

It’s the repository job to know what should be the source of the data. The repository should decide whether the data in the local database is good enough or if it should pull it from a service. The repository shouldn’t be tied to an implementation of database/services. It should have references to interfaces that access the actual framework. A boolean may be passed as a parameter to the repository to force an update from a specific source.

Presentation
Presentation layer contains every component involved in showing information to the user. The main part of this layer are the Views and ViewModels that will be explained in the next section. In general, the presentation layer is the one using all the Use Cases/Interactors that we created in the domain layer.

Views in this layer are the fragments and activities designed to show information to the user. In MVVM, these views are separated from the logic, which is encapsulated in the ViewModel.

MVVM
The Model View ViewModel pattern helps with the separation of concerns, dividing the user interface with the logic behind. The decision to use this pattern is mainly based on the support Google has been giving to it. Not only they have created a ViewModel class to use as a parent to the viewmodels, there is also a huge use of the pattern in official Android presentations and samples. Moreover, MVVM is vastly used in today’s Android development, and combines very well with Android Architecture Components like LiveData and DataBindings.

Model
As we are implementing MVVM alongside with Clean Architecture, we decided not to have a model class per se. The ViewModel interacts directly with the domain, utilizing the use cases.

View Model
The orchestrator of the relationship between the data and the user interface of the application. The ViewModel has the logic to convert what the use cases provide into information that the view can understand and present. Furthermore, it has the logic to react to the user’s input, and call the pertinent use cases.

The most useful part of the Android’s ViewModel class is its lifecycle consciousness. It only communicates to the View with LiveData components, so it’s totally agnostic of contexts and activities: it can keep the information alive even against configuration changes like screen rotations or calls to background.

View
The view in our implementation of MVVM is actually a Fragment or an Activity. The views enclose everything needed to handle the user interface. They observe the ViewModel, using LiveData components, and react to its changes as they need to.

Unit Test - A local test runs directly on your own workstation, rather than an Android device or emulator. As such, it uses your local Java Virtual Machine (JVM), rather than an Android device to run tests. Local tests enable you to evaluate your app's logic more quickly. However, not being able to interact with the Android framework creates a limitation in the types of tests you can run.
