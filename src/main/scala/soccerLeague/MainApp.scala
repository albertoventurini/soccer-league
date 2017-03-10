package soccerLeague

object MainApp {

  def main(args: Array[String]): Unit = {

    val lines = io.Source.stdin.getLines().toList
      .filterNot(_.isEmpty) // Remove empty lines (e.g. at the end of the file)

    val leagueRank = LeagueRankApp(lines)

    println(leagueRank)
  }

}

