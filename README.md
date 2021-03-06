# A quick walkthrough how to enable CDI for spring data (neo4j) for play 2.4 with scala

Update 2.4 for the playframeork was a little bit tricky in terms of CDI (and scala / spring). I had problems getting my spring contexts start again. Therefore i needed to introduced spring-guice.

In this repository i will show the needed 6 steps to kickstart the project. All important steps are mentioned in this README.
You can also have a look the first 6 commits (commit messages equals headlines in README), where i did exactly the same like what i described in this file, see https://github.com/unterstein/play-2.4-scala-spring-data-sample/commits/master .

To start this example app, just clone this repo, browse to this folder in your shell and do

```
activator run
```

and open http://localhost:9000 in your favourite browser :-)


# Step 1 - Init the project

The initial step using "activator new -> play-scala" template and writing the skeleton of this README.


# Step 2 - Add dependencies

Added the following dependencies:

```
  // utilities
  "javax.inject" % "javax.inject" % "1",
  "com.sun.jersey" % "jersey-core" % "1.19",
  "javax.validation" % "validation-api" % "1.1.0.Final",
  // spring data
  "org.springframework.data" % "spring-data-neo4j" % "3.4.2.RELEASE",
  "org.springframework.data" % "spring-data-neo4j-rest" % "3.4.2.RELEASE",
  // spring guice
  "org.springframework.guice" % "spring-guice" % "1.0.0.BUILD-SNAPSHOT"
```

Spring-guice is not (yet) released officially, therefore we must add the following resolver:

```
resolvers += "Spring Snapshots" at "http://maven.springframework.org/snapshot"
```


# Step 3 - Add spring module (yeah, you need a module now!)

For spring-guice we need a SpringModule, references the @Configuration. Module and configuration are located in app/modules.
The configuration properties are located in conf/neo4j.conf.

The magic trick to enable our module is to add the following line to a play configuration file of your choice (also conf/neo4j.conf in this example):

```
play.modules.enabled += "modules.SpringNeo4jModule"
```


# Step 4 - Add (sample) data model and repositories

Added sample a data model and repository, taken from my good friend`s @tuxBurner awesome play-neo4jplugin, see https://github.com/tuxBurner/play-neo4jplugin.


# Step 5 - Adapt controller to use repository

Adapted changes to controller and added example index.scala.html (also taken from play-neo4jplugin).

The trick is to use @Named and @Inject, see:
```
@Named
class ApplicationController @Inject()(worldRepository: WorldRepository, initialDataService: InitialDataService) extends Controller {
...
}
```


# Step 6 - Add test data

Test data already added in step 4, therefore nothing to do here! :-)


# Conclusion

We saw how to initialize and start our spring context for spring-data, in this example for spring-data-neo4j. You can use this template for (nearly) all spring-data projects.
For example you could use this for spring-data-elasticsearch, see https://github.com/unterstein/play-spring-data-elasticsearch-example .

If you like the eBean style of more static access of your repositories, you might be interested in plugins to use your favourite database technology in your favourite style of coding.
I would also prefer this style ;-)

For neo4j, tuxBurner implemented a plugin: https://github.com/tuxBurner/play-neo4jplugin

For elasticsearch, i implemented a plugin: https://github.com/unterstein/play-elasticplugin


Cheers,
Johannes
