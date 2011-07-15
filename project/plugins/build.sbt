
//Resolver.url("Typesafe Repository", typesafeRepoUrl)(pattern)

resolvers ++= Seq("Web plugin repo" at "http://siasia.github.com/maven2",{
val typesafeRepoUrl = new java.net.URL("http://repo.typesafe.com/typesafe/releases")
val pattern = Patterns(false, "[organisation]/[module]/[sbtversion]/[revision]/[type]s/[module](-[classifier])-[revision].[ext]")
Resolver.url("Typesafe Repository", typesafeRepoUrl)(pattern)
}
)

libraryDependencies ++= Seq(
  "com.typesafe.sbteclipse" %% "sbteclipse" % "1.1",
  "com.github.siasia" %% "xsbt-web-plugin" % "0.1.0-0.10.0"
)

