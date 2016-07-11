package com.nysu.helper

import com.nysu.model.Team
import com.nysu.model.TeamBio
import scalatags.JsDom.all._

object Components {
  
  def renderTeam( team : Team, members : Seq[TeamBio] ) = {
    div( `class` := "container" )(
      h1( team.name ),
      img( src := team.logo, height := 200 ),
      div( `class` := "row" )( for { member ← members } yield renderMemberCard( member ) ),
      div( `class` := "footer" )(
        div( `class` := "row text-center" )(
          hr( `class` := "divider" ),
          h5( "New York Scala University. All rights reserved." )
        )
      )
    )
  }

  def renderMemberCard( bio : TeamBio ) = {
    div( `class` := "col-md-4" )(
      div( `class` := "thumbnail" )(
        img( src := bio.avatar_url ),
        div( `class` := "caption text-center" )(
          h3( bio.name ),
          h5( bio.email ),
          a( href := s"https://github.com/${bio.login}", `class` := "btn btn-default", role := "button" )( "Github" )
        )
      )
    )

  }
}
