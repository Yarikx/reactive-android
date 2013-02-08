import sbt._

import Keys._
import AndroidKeys._

object General {
  val settings = Defaults.defaultSettings ++ Seq (
    name := "Reactive Android",
    version := "0.2",
    versionCode := 0,
    scalaVersion := "2.9.2",
    platformName in Android := "android-16"
  )

  val proguardSettings = Seq (
    useProguard in Android := true
  )

  lazy val fullAndroidSettings =
    General.settings ++
    AndroidProject.androidSettings ++
    TypedResources.settings ++
    proguardSettings ++
    AndroidManifestGenerator.settings ++
    AndroidMarketPublish.settings ++ Seq (
      keyalias in Android := "change-me",
      libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test"
    )

}

object AndroidBuild extends Build {
  lazy val main = Project (
    "ReactiveAndroid",
    file("."),
    settings = General.fullAndroidSettings
  ) dependsOn core

  lazy val tests = Project (
    "tests",
    file("tests"),
    settings = General.settings ++
               AndroidTest.androidSettings ++
               General.proguardSettings ++ Seq (
      name := "Reactive AndroidTests"
    )
  ) dependsOn main

  lazy val core = Project (
    "core",
    file("reactive-core"),
    settings = General.settings
  )
}
