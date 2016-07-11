enablePlugins(ScalaJSPlugin)

name := "teamsearch"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.0",
  "com.lihaoyi" %%% "scalatags" % "0.4.6",
  "com.lihaoyi" %%% "upickle" % "0.4.1"
)