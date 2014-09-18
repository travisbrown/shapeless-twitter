scalaVersion := "2.11.2"

// So much noise, but can't live without it.
scalacOptions += "-Xlog-implicits"

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.1.0-SNAPSHOT" changing(),
  "com.twitter" %% "algebird-core" % "0.8.0",
  "com.twitter" %% "bijection-core" % "0.7.0",
  "com.twitter" %% "chill" % "0.5.0",
  "org.scalatest" %% "scalatest" % "2.2.2" % "test",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided"
)
