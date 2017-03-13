package soccerLeague.common

object LeagueRankFormatter {

  // Formats a LeagueRank into a String
  def format(leagueRank: Seq[TeamWithPoints]): String = {

    var index = 0
    var previousRank: Option[Int] = None
    var previousPoints: Option[Int] = None

    leagueRank.map { case TeamWithPoints(team, points) => {
        index += 1
        val rank = if(previousPoints.isDefined && points == previousPoints.get) previousRank.get else index
        previousRank = Some(rank)
        previousPoints = Some(points)
        val pointsSuffix = if(points == 1) "pt" else "pts"
        s"$rank. $team, $points $pointsSuffix"
      }
    }.mkString("\n")

  }
}
