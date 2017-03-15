package soccerLeague.akka

import akka.actor.{ActorSystem, Props}
import akka.pattern.Patterns
import akka.testkit.TestActorRef
import akka.util.Timeout
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import soccerLeague.common.{LeagueRules, MatchResult, TeamWithPoints}
import sun.management.counter.Units
import akka.pattern.ask

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.Future

@RunWith(classOf[JUnitRunner])
class RankerActorTests extends FunSuite {

  trait RankerActorTestsHelper {
    implicit val system = ActorSystem()
    val leagueRules = new LeagueRules(winScore = 3, drawScore = 1, lossScore = 0)
    val rankerRef = TestActorRef[RankerActor](Props(new RankerActor(leagueRules)))
    val rankerActor = rankerRef.underlyingActor
  }

  test("rank_oneMatchWithVictory_shouldProduceCorrectRank") {
    new RankerActorTestsHelper {
      implicit val timeout = Timeout(5 seconds)
      val future = rankerRef ? new MatchResult("team1", 0, "team2", 1)
      val result = Await.result(future, timeout.duration).asInstanceOf[Seq[TeamWithPoints]]
      assert(result(0).team === "team2")
      assert(result(0).points === 3)
      assert(result(1).team === "team1")
      assert(result(1).points === 0)
    }
  }


}
