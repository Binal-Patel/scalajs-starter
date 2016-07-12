package com.nysu

import scala.scalajs.js.JSApp
import com.nysu.model.{ Team, TeamBio }

object TeamSearch extends JSApp {

  val teams = Seq( "incredibles", "avengers", "ducktales" )
  val team = Team("incredibles","",Seq("himanshu4141"))
  val bio  = TeamBio("Himanshu Yadav", "himanshu4141","himanshu4141@gmail.com","https://avatars.githubusercontent.com/u/1370357?v=3")
  
  def main() : Unit = {
    println( s"Team :- $team" )
    println(s"Team Bio :- $bio")
  }
  
}
