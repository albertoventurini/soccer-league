package soccerLeague

import org.junit.Before
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RankerTest extends FunSuite {

  private val winScore = 3
  private val drawScore = 1
  private val lossScore = 0
  private val rules = new LeagueRules(winScore, drawScore, lossScore)
  private val ranker = new Ranker(rules)

  // This is just to get TDD bootstrapped (ensures we have an implementation of the rank method)
  test("rank_emptyChampionship_shouldProduceEmptyRank") {

    val championship = List()
    val rankResult = ranker.rank(championship)

    assert(rankResult.isEmpty)

  }

  test("rank_oneMatchWithVictory_shouldProduceCorrectRank") {

    val match1 = new MatchResult("team1", 0, "team2", 1)
    val championship = List(match1)
    val rankResult = ranker.rank(championship)

    assert(rankResult(0).team === "team2")
    assert(rankResult(0).points === winScore)
    assert(rankResult(1).team === "team1")
    assert(rankResult(1).points === lossScore)

  }

  test("rank_oneMatchWithDraw_shouldProduceCorrectRank") {

    val match1 = new MatchResult("team1", 1, "team2", 1)
    val championship = List(match1)
    val rankResult = ranker.rank(championship)

    assert(rankResult(0).points === drawScore)
    assert(rankResult(1).points === drawScore)

  }

  test("rank_manyMatches_shouldProduceCorrectRank") {
/*
Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0
 */
    val match1 = new MatchResult("Lions", 3, "Snakes", 3)
    val match2 = new MatchResult("Tarantulas", 1, "FC Awesome", 0)
    val match3 = new MatchResult("Lions", 1, "FC Awesome", 1)
    val match4 = new MatchResult("Tarantulas", 3, "Snakes", 1)
    val match5 = new MatchResult("Lions", 4, "Grouches", 0)

    val championship = List(match1, match2, match3, match4, match5)
    val rankResult = ranker.rank(championship)

/*
1. Tarantulas, 6 pts
2. Lions, 5 pts
3. FC Awesome, 1 pt
3. Snakes, 1 pt
5. Grouches, 0 pts
*/

    assert(rankResult(0).team === "Tarantulas")
    assert(rankResult(0).points === winScore*2)
    assert(rankResult(1).team === "Lions")
    assert(rankResult(1).points === winScore + drawScore*2)
    assert(rankResult(2).team === "FC Awesome")
    assert(rankResult(2).points === drawScore + lossScore)
    assert(rankResult(3).team === "Snakes")
    assert(rankResult(3).points === drawScore + lossScore)
    assert(rankResult(4).team === "Grouches")
    assert(rankResult(4).points === lossScore)


  }

}
