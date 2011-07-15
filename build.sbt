// Set the project name to the string 'My Project'
name := "My Project"

// The := method used in Name and Version is one of two fundamental methods.
// The other method is <<=
// All other initialization methods are implemented in terms of these.
version := "1.0"


// Add a single dependency
//libraryDependencies += "junit" % "junit" % "4.8" % "test"

// Add multiple dependencies "org.ow2.jonas" % "jaxrs-jersey" % "5.3.0-M3",

resolvers += "Java.net Repository for Maven" at "http://download.java.net/maven/2/"


libraryDependencies ++= Seq(
	"com.sun.jersey" % "jersey-server" % "1.4" %"compile",
	"com.google.code.gson" % "gson" % "1.7.1",
	"org.mortbay.jetty" % "jetty" % "6.1.25" % "jetty"
	)

seq(webSettings :_*)
// Exclude backup files by default.  This uses ~=, which accepts a function of
//  type T => T (here T = FileFilter) that is applied to the existing value.
// A similar idea is overriding a member and applying a function to the super value:
//  override lazy val defaultExcludes = f(super.defaultExcludes)
//
//sdefaultExcludes ~= (filter => filter || "*~")
/*  Some equivalent ways of writing this:
defaultExcludes ~= (_ || "*~")
defaultExcludes ~= ( (_: FileFilter) || "*~")
defaultExcludes ~= ( (filter: FileFilter) => filter || "*~")
*/

scalaVersion := "2.9.0"

// Use the project version to determine the repository to publish to.
publishTo <<= version { (v: String) =>
  if(v endsWith "-SNAPSHOT")
    Some(ScalaToolsSnapshots)
  else
    Some(ScalaToolsReleases)
}
