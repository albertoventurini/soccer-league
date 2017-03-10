package soccerLeague

// A Ranker is initialized with rules, e.g. how many points to assign for each match.
// It exposes a 'rank' method that, given a Championship, returns the calculated rank.
class Ranker(rules: LeagueRules) {

  def rank(championship: Championship): LeagueRank = {

    def calcDeltaPoints(score1: Int, score2: Int): Int = {
      if(score1 == score2) rules.drawScore
      else if(score1 > score2) rules.winScore
      else rules.lossScore
    }

    val emptyRank = Map[String, Int]()

    val rank = championship.foldLeft(emptyRank)((prevRank, m) => {

      val newPoints1 = prevRank.getOrElse(m.team1, 0) + calcDeltaPoints(m.score1, m.score2)
      val newPoints2 = prevRank.getOrElse(m.team2, 0) + calcDeltaPoints(m.score2, m.score1)

      prevRank + (m.team1 -> newPoints1) + (m.team2 -> newPoints2)
    })

    // sort by score, in reverse order (i.e. the team with most points is first)
    rank.toSeq.map { case (t, s) => new TeamWithPoints(t, s) }.sortBy(- _.points)
  }

}
