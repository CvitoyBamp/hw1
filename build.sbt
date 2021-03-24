name := "hw1"

version := "homework_1"

scalaVersion := "2.13.5"

val circeVersion = "0.14.0-M4"
libraryDependencies ++= Seq(
  "io.circe"  %% "circe-core"     % circeVersion,
  "io.circe"  %% "circe-generic"  % circeVersion,
  "io.circe"  %% "circe-parser"   % circeVersion,
  "io.circe" %% "circe-jawn" % circeVersion
)