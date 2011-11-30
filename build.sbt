// Set the project name to the string 'My Project'
name := "My Project"

// The := method used in Name and Version is one of two fundamental methods.
// The other method is <<=
// All other initialization methods are implemented in terms of these.
version := "1.0"


// Add a single dependency
//libraryDependencies += "junit" % "junit" % "4.8" % "test"

// Add multiple dependencies "org.ow2.jonas" % "jaxrs-jersey" % "5.3.0-M3",
resolvers ++= Seq(
"Java.net Repository for Maven" at "http://download.java.net/maven/2/",
Resolver.url("twitter.com", url("http://maven.twttr.com/")),
Resolver.url("lag.net", url("http://www.lag.net/repo/"))
)

libraryDependencies ++= Seq(
	"com.sun.jersey" % "jersey-server" % "1.4" %"compile",
	"com.google.code.gson" % "gson" % "1.7.1",
	"mysql" % "mysql-connector-java" % "5.1.17",
	"commons-dbcp" % "commons-dbcp" % "1.4",
	"com.twitter" % "querulous" % "2.1.5",
	"com.twitter" % "util-core" % "1.7.0",
	"org.mortbay.jetty" % "jetty" % "6.1.25" % "jetty",
	"net.debasishg" % "sjson_2.9.0" % "0.12",
	"org.scalatest" %% "scalatest" % "1.6.1",
	"org.mockito" % "mockito-core" % "1.8.5"
	)

seq(webSettings :_*)

scalaVersion := "2.9.0"

// Use the project version to determine the repository to publish to.
publishTo <<= version { (v: String) =>
  if(v endsWith "-SNAPSHOT")
    Some(ScalaToolsSnapshots)
  else
    Some(ScalaToolsReleases)
}
