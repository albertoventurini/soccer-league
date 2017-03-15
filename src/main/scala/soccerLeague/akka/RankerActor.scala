package soccerLeague.akka

import akka.actor.Actor
import soccerLeague.common.{LeagueRules, MatchResult, TeamWithPoints}

// Stateful Ranker actor that maintains a rank.
// The rank gets updated every time a MatchResult message is received.
class RankerActor(rules: LeagueRules) extends Actor {

  private var rank: Seq[TeamWithPoints] = List()
  private var rankMap = Map[String, Int]()

  def receive = {
    case MatchResult(team1, score1, team2, score2) => {
      updateRank(MatchResult(team1, score1, team2, score2))
      sender ! rank
    }
    case Message("getRank") => {
      sender ! rank
    }
  }

  private def updateRank(matchRes: MatchResult): Unit = {

    def calcDeltaPoints(score1: Int, score2: Int): Int = {
      if(score1 == score2) rules.drawScore
      else if(score1 > score2) rules.winScore
      else rules.lossScore
    }

    val newPoints1 = rankMap.getOrElse(matchRes.team1, 0) + calcDeltaPoints(matchRes.score1, matchRes.score2)
    val newPoints2 = rankMap.getOrElse(matchRes.team2, 0) + calcDeltaPoints(matchRes.score2, matchRes.score1)

    rankMap += (matchRes.team1 -> newPoints1, matchRes.team2 -> newPoints2)

    // Sort by score, in reverse order (i.e. the team with most points is first).
    // Teams that have the same score are sorted in alphabetical order.
    // TODO: this *can* be improved by only moving around the two teams that are changing.
    rank = rankMap.toSeq
      .map { case (t, s) => new TeamWithPoints(t, s) }
      .sortBy { case TeamWithPoints(team, points) => (-points, team) }
  }

}
