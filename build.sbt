// ------------------------------
// Automated code formatter before compilaion
// Disabled by default because this is confusing for beginners
//scalariformSettings
libraryDependencies ++= Seq(
  "org.skinny-framework" %% "skinny-orm"      % "1.3.20",
  "postgresql"           %  "postgresql"      % "9.1-901.jdbc4",
  "ch.qos.logback"       %  "logback-classic" % "1.1.+"
)

// ------------------------------
// for ./skinnny console
initialCommands := """
import skinny._
import _root_.controller._, model._
import org.joda.time._
import scalikejdbc._, config._
DBSettings.initialize()
"""
