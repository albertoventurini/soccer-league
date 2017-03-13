package soccerLeague.common

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LeagueRankFormatterTests extends FunSuite {

  test("format_emptyInput_shouldReturnEmptyString") {

    val result = LeagueRankFormatter.format(List())

    assert(result.isEmpty)
  }

  test("format_oneElement_shouldReturnFormattedElement") {

    val leagueRank = List(new TeamWithPoints("A-team", 123))

    val result = LeagueRankFormatter.format(leagueRank)

    assert("1. A-team, 123 pts" === result)
  }

  test("format_teamWithOnePoint_shouldReturnCorrectlyFormattedElement") {

    val leagueRank = List(new TeamWithPoints("Juventus", 1))

    val result = LeagueRankFormatter.format(leagueRank)

    assert("1. Juventus, 1 pt" === result)
  }

  test("format_multipleElements_shouldReturnFormattedElements") {

    val leagueRank = List(
      new TeamWithPoints("Some team", 99),
      new TeamWithPoints("Some other team", 42),
      new TeamWithPoints("Another team", 1),
      new TeamWithPoints("Last team", 0)
    )

    val result = LeagueRankFormatter.format(leagueRank)

    assert("1. Some team, 99 pts\n2. Some other team, 42 pts\n3. Another team, 1 pt\n4. Last team, 0 pts" === result)

  }

}
