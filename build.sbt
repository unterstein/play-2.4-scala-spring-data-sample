name := """play-spring-data-sample"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  // utilities
  "javax.inject" % "javax.inject" % "1",
  "com.sun.jersey" % "jersey-core" % "1.19",
  "javax.validation" % "validation-api" % "1.1.0.Final",
  // spring data
  "org.springframework.data" % "spring-data-neo4j" % "3.4.2.RELEASE",
  "org.springframework.data" % "spring-data-neo4j-rest" % "3.4.2.RELEASE",
  // spring guice
  "org.springframework.guice" % "spring-guice" % "1.0.0.BUILD-SNAPSHOT"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "Spring Snapshots" at "http://maven.springframework.org/snapshot"


// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
