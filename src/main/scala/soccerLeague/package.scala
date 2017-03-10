// Contains common type and class definitions
package object soccerLeague {

  // LeagueRules contains common rules for the league, e.g. how many points to assign for each match
  class LeagueRules(val winScore: Int, val drawScore: Int, val lossScore: Int)

  class MatchResult(val team1: String, val score1: Int, val team2: String, val score2: Int)

  type Championship = Seq[MatchResult]

  case class TeamWithPoints(val team: String, val points: Int)

  // LeagueRank is a list of teams with their respective points
  type LeagueRank = Seq[TeamWithPoints]
}
