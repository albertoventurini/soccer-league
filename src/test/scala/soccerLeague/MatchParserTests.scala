package soccerLeague

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MatchParserTests extends FunSuite {

  test("parse_emptyInput_returnsEmptyListOfResults") {

    val matchResults = MatchParser.parse(List())

    assert(matchResults.isEmpty)

  }

  test("parse_oneMatch_returnsParsedMatch") {

    val matchResults = MatchParser.parse(List("Lions 3, Snakes 3"))

    assert(matchResults.size === 1)
    assert(matchResults.head.score1 === 3)
    assert(matchResults.head.score2 === 3)
    assert(matchResults.head.team1 === "Lions")
    assert(matchResults.head.team2 === "Snakes")
  }

  test("parse_multipleMatches_returnsParsedMatches") {
/*
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
 */
    val matchResults = MatchParser.parse(
      List("Tarantulas 1, FC Awesome 0", "Lions 1, FC Awesome 1")
    )

    assert(matchResults.size === 2)

    assert(matchResults(0).score1 === 1)
    assert(matchResults(0).score2 === 0)
    assert(matchResults(0).team1 === "Tarantulas")
    assert(matchResults(0).team2 === "FC Awesome")

    assert(matchResults(1).score1 === 1)
    assert(matchResults(1).score2 === 1)
    assert(matchResults(1).team1 === "Lions")
    assert(matchResults(1).team2 === "FC Awesome")
  }

  test("parse_teamWithNumericCharacterInName_isParsedCorrectly") {

    val matchResults = MatchParser.parse(List("123 team 1, 1 team 2 10"))

    assert(matchResults.head.score1 === 1)
    assert(matchResults.head.score2 === 10)
    assert(matchResults.head.team1 === "123 team")
    assert(matchResults.head.team2 === "1 team 2")

  }

}
