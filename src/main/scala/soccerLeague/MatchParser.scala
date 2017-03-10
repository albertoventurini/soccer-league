package soccerLeague

object MatchParser {

  def parse(lines: Seq[String]): Seq[MatchResult] = lines.map(parseLine)

  private def parseLine(line: String): MatchResult = {
    val teamsWithScores = line.split(',').map(_.trim)

    // Regex to extract the team name and score
    val pattern = "^(.*) ([0-9]*)$".r

    val pattern(team1, score1) = teamsWithScores(0)
    val pattern(team2, score2) = teamsWithScores(1)

    new MatchResult(team1, score1.toInt, team2, score2.toInt)
  }
}
