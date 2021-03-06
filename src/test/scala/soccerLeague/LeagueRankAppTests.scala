package soccerLeague

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LeagueRankAppTests extends FunSuite {

  test("LeagueRankApp_shouldProduceExpectedOutput") {
    val input = """Lions 3, Snakes 3
                  |Tarantulas 1, FC Awesome 0
                  |Lions 1, FC Awesome 1
                  |Tarantulas 3, Snakes 1
                  |Lions 4, Grouches 0""".stripMargin

    val expectedOutput = """1. Tarantulas, 6 pts
                           |2. Lions, 5 pts
                           |3. FC Awesome, 1 pt
                           |3. Snakes, 1 pt
                           |5. Grouches, 0 pts""".stripMargin

    val actualOutput = LeagueRankApp(input.split("\n"))

    println(expectedOutput)
    println(actualOutput)

    assert(expectedOutput.trim === actualOutput.trim)
  }

}
