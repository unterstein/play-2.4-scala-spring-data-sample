# A quick walkthrough how to enable CDI for spring data (neo4j) for play 2.4 with scala

Update 2.4 for the playframeork was a little bit tricky in terms of CDI (and scala / spring). I had problems getting my spring contexts start again. Therefore i needed to introduced spring-guice.

In this repository i will show the needed X steps to kickstart the project.

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

# Step 5 - Adapt controller to use repository

# Step 6 - Add test data