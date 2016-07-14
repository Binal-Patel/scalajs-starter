package com.nysu

import scala.concurrent.Future

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.Any.fromFunction1
import scala.scalajs.js.JSApp

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.ext.{ Ajax, KeyCode }

import com.nysu.helper.Components
import com.nysu.model.{ Team, TeamBio }

import scalatags.JsDom.all._

import upickle.default._
import upickle._

object TeamSearch extends JSApp {

  val teams = Seq( "incredibles", "avengers", "ducktales" )
  val team = Team( "incredibles", "", Seq( "himanshu4141" ) )
  val bio = TeamBio( "Himanshu Yadav", "himanshu4141", "himanshu4141@gmail.com", "https://avatars.githubusercontent.com/u/1370357?v=3" )

  def main() : Unit = {
    println( "hello" )
    val inputBox = input( placeholder := "Search Here").render
    val searchBtn = button( "Search" ).render
    val emptyDiv = div.render
    val container = div( `class` := "text-center" )(
      h1( "Welcome To HBC" ),
      div(
        inputBox,
        searchBtn ), p,
      emptyDiv ).render

    searchBtn.onclick = { ( event : dom.Event ) ⇒ doSomething() }
    inputBox.onkeypress = { ( event : dom.KeyboardEvent ) ⇒ if ( event.keyCode == KeyCode.Enter ) doSomething() }

      def doSomething() = {
        val teamName = inputBox.value.toLowerCase()
        if ( teams.contains( teamName ) ) {
          fetchAndRenderTeam( teamName, emptyDiv )
        } else {
          emptyDiv.innerHTML = "No Results !!!"
        }
      }
    document.body.appendChild( container.render )
  }

  def fetchAndRenderTeam( teamName : String, divToReplace : dom.html.Div ) = {
    for {
      team ← getTeam( teamName )
      members ← getMemberCard( team )
    } yield {
      divToReplace.innerHTML = Components.renderTeam( team, members ).toString()
    }
  }

  def getMemberCard( team : Team ) = {
    val members = team.members.map { userId ⇒
      {
        Ajax.get( s"https://api.github.com/users/$userId" ).map { x ⇒ read[TeamBio]( x.responseText ) }
      }
    }
    Future.sequence( members )
  }

  def getTeam( teamName : String ) = {
    Ajax.get( s"$teamName.json" ).map { x ⇒
      read[Team]( x.responseText )
    }
  }
}
