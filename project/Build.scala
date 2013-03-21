import sbt._

import Keys._
import AndroidKeys._

object General {
  val settings = Defaults.defaultSettings ++ Seq (
    name := "Reactive Android",
    version := "0.2",
    versionCode := 0,
    scalaVersion := "2.10.1",
    platformName in Android := "android-16"
  )

  val proguardSettings = Seq (
    useProguard in Android := true,
    javacOptions ++= Seq("-source", "1.6", "-target", "1.6")
  )

  lazy val fullAndroidSettings =
    General.settings ++
    AndroidProject.androidSettings ++
    TypedResources.settings ++
    proguardSettings ++
    AndroidManifestGenerator.settings ++
    AndroidMarketPublish.settings ++ Seq (
      keyalias in Android := "change-me",
      libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"
    )

  lazy val androidLibSettings = 
    Defaults.defaultSettings ++
    AndroidProject.androidSettings ++
    Seq (
    version := "0.2",
    versionCode := 0,
    scalaVersion := "2.10.1",
    platformName in Android := "android-16"
  )


}

  
object AndroidBuild extends Build {
  lazy val android = Project (
    "android",
    file("reactive-android"),
    settings = General.androidLibSettings
  ) dependsOn core

  lazy val core = Project (
    "core",
    file("reactive-core"),
    settings = coreDefaults
  )

  lazy val tests = Project (
    "tests",
    file("reactive-android/tests"),
    settings = General.settings ++
               AndroidTest.androidSettings ++
               General.proguardSettings ++ Seq (
      name := "Reactive AndroidTests"
    )
  ) dependsOn android

  lazy val ademo = Project (
    "demo",
    file("reactive-demo"),
    settings = General.fullAndroidSettings ++ Seq(
      libraryDependencies += "com.google.android" % "support-v4" % "r7"
    )
  ) dependsOn android

  val sonatypeSnapshots = "http://oss.sonatype.org/content/repositories/snapshots/"
  val coreDefaults = Defaults.defaultSettings ++ Seq(
      version := "0.3.0",
      scalaVersion := "2.10.0",
      resolvers ++= List(
        "Sonatype snapshots" at sonatypeSnapshots,
        "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"
      ),
      checksums in update := Nil,
      scalacOptions in (Compile, compile) += "-deprecation",
      (scalacOptions in (Compile, doc) <++= (baseDirectory).map{ bd =>
        Seq("-sourcepath", bd.getAbsolutePath, "-doc-source-url", "http://github.com/nafg/reactive/tree€{FILE_PATH}.scala")
      }),
      crossScalaVersions := List("2.10.0", "2.9.2"),
      libraryDependencies <++= (scalaVersion) { v => List(
           "org.scalatest" %% "scalatest" % (
             if(v startsWith "2.9") "2.0.M6-SNAP3"
             else "2.0.M6-SNAP5"
           ) % "test",
           "org.scalacheck" %% "scalacheck" % (
             if(v startsWith "2.8") "1.8"
             else "1.10.1-SNAPSHOT"
           ) % "test" cross CrossVersion.full
        ) ++
        List("org.scala-lang" % "scala-actors" % v).filter(_ => v startsWith "2.10")
      },
      testOptions in Test += Tests.Argument("-oF"),
      unmanagedClasspath in Compile += Attributed.blank(new java.io.File("doesnotexist"))
    )

}
