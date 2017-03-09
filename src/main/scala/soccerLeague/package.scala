import scala.collection.immutable.SortedMap

package object soccerLeague {

  type Championship = List[MatchResult]

  class TeamPoints(val team: String, val points: Int)

  // LeagueRank is a list of teams and their respective points
  type LeagueRank = Seq[TeamPoints]

}
