resolvers += Resolver.url("scalasbt releases", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

addSbtPlugin("org.scala-sbt" % "sbt-android-plugin" % "0.6.3-20130429-SNAPSHOT")

addSbtPlugin("org.ensime" % "ensime-sbt-cmd" % "0.1.0")
