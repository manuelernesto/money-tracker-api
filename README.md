### Money Tracker API

This is an API developed with modern server side tools:

* [Kotlin](https://github.com/JetBrains/kotlin)
* [Ktor](https://github.com/ktorio/ktor)
* [Exposed](https://github.com/JetBrains/Exposed)

### How to run the project

* Clone the repository

```
git clone https://github.com/manuelernesto/money-tracker-api.git
```

* Create a postgres database

``` 
CREATE DATABASE money_tracker_db;
```

* Go to the project folder and run it

``` 
DB_URL=jdbc:postgresql://localhost:5432/money_tracker_db DB_PASSWORD=dbpassword DB_USER=dbuser ./gradlew run
``` 
