package soccerLeague

object LeagueRankApp {

  def apply(lines: Seq[String]): String = {
    val matchResults = MatchParser.parse(lines)

    val leagueRules = new LeagueRules(winScore = 3, drawScore = 1, lossScore = 0)
    val ranker = new Ranker(leagueRules)

    val leagueRank = ranker.rank(matchResults)
    LeagueRankFormatter.format(leagueRank)
  }

}
