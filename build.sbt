name := "OC-TSP"

version := "1.0"

scalacOptions += "-deprecation"

mainClass in assembly := Some("main.Approximation")
assemblyJarName in assembly := "approx.jar"
