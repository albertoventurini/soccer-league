package soccerLeague

import soccerLeague.batch.BatchLeagueRankApp

object MainApp {

  def main(args: Array[String]): Unit = {

    val lines = io.Source.stdin.getLines().toList
      .filterNot(_.isEmpty) // Remove empty lines (e.g. at the end of the file)

    val leagueRankApp = new BatchLeagueRankApp
    val rank = leagueRankApp(lines)

    println(rank)
  }

}

