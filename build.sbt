name := "Slick_Student_Akash"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc4",
  "com.typesafe.slick"  %%     "slick-hikaricp"           %      "3.1.1",
  "ch.qos.logback"       %     "logback-classic"          %      "1.1.3",
  "com.typesafe.slick"   %%    "slick"            	      %      "3.1.1",
  "org.scalatest"        %%    "scalatest"    	      %      "2.2.5"     %    "test",
  "com.h2database"       % 	   "h2"                       %      "1.4.187"    %   "test"
)
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.38"