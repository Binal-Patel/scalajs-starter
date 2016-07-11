package com.nysu.model

case class Team(
  name : String,
  logo : String,
  members : Seq[String] )

case class TeamBio(
  name : String,
  login : String,
  email : String,
  avatar_url : String )
