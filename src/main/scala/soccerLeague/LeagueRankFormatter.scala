package soccerLeague

//
object LeagueRankFormatter {

  def format(leagueRank: LeagueRank): String = {

    var index = 0

    leagueRank.map(teamWithPoints => {
      index += 1
      formatTeamPoints(index, teamWithPoints)
    }).mkString("\n")

  }

  private def formatTeamPoints(index: Int, teamWithPoints: TeamWithPoints): String = {
    val team = teamWithPoints.team
    val points = teamWithPoints.points
    val pointsSuffix = if(points == 1) "pt" else "pts"

    s"$index. $team, $points $pointsSuffix"
  }

}
