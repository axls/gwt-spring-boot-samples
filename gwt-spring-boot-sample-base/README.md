# Spring Boot GWT Gradle minimalistic project's template.

The project uses spring-boot for serving static content only. There are no any client-server communication samples in this project.

# Used libraries/tools/frameworks

GWT 2.8.2

Spring Boot 2.0 RC1

Gradle 4.5.1

# Build executable war


```
../gradlew build
```

After above command will be completed, application war file can be found in `build/libs` directory.

Next command may be used to launch executable war:

```
java -jar build/libs/gwt-spring-boot-sample-base-0.0.1-SNAPSHOT.war
```

The application can be reached in the browser by URL `http://localhost:8080`  

# Development

To start playing with code you have to start GWT `CodeServer` and Spring Boot server in separated processes.
This approach allows to restart server and client independently on each other.

To start the server side in one terminal:

```
../gradlew bootRun
```

And to start the CodeServer in another terminal:

```
../gradlew gwtSuperDev
```

After server and CodeServer has started, application will be available on URL `http://localhost:8080`. 
You can change client side without restarting. Incremental compilation will serve and apply changes in client side on page reload.
To apply changes in server side (not covered by hot swap) server should be restarted (or Spring Boot DevTools could be used for fast reloading server side).  

# Launch in Eclipse

Two plugins should be installed to launch this project in Eclipse:

* GWT Eclipse plugin should be installed (http://gwt-plugins.github.io/documentation/gwt-eclipse-plugin/Download.html).
* Spring Tools Suite plugin (latest version).

Before importing the project into Eclipse, execute next command to generate Eclipse project's required metadata files:

```
../gradlew eclipse
```


There are two Eclipse launchers in the project - `gwt-spring-boot-sample-base-codeserver` and `gwt-spring-boot-sample-base-server`.
The first one, according to the name, launches CodeServer, and the second one, launches Spring Boot application. After both parts launched application will be available on URL `http://localhost:8080`.  
