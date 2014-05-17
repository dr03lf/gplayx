name := "gplayx"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)

libraryDependencies += "org.springframework" % "spring-core" % "4.0.3.RELEASE"

play.Project.playScalaSettings
