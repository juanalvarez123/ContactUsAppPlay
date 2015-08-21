name := """play-java-intro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  javaWs,  
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.6.1",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.6.1",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.1",
  "com.fasterxml.jackson.module" % "jackson-module-paranamer" % "2.6.1"
)     

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := true