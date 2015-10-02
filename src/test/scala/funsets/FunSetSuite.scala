package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  test("adding ints") {
    assert(1 + 2 === 3)
  }

  import FunSets._

  test("contains implemented") {
    assert(contains(x => true, 7))
  }

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
  }

  test("singletonSet contains") {
    new TestSets {
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s2, 1), "Singleton")
    }
  }

  test("union, intersect, diff") {
    new TestSets {
      val u = union(s2, s3)
      val uu = union(u,s4)
      val i = intersect(u, s2)
      val d = diff(uu, s2)
      assert(contains(u, 3))
      assert(contains(i, 2))
      assert(!contains(i, 4))
      assert(contains(d, 3))
      assert(contains(d, 4))
      assert(!contains(d, 2))
      assert(!contains(uu, 1))
    }
  }

  test("forall") {
    new TestSets {
      val u = union(s2, s3)
      val uu = union(u,s4)
      val i = intersect(u, s2)
      val d = diff(uu, s2)
      val ff = (a:Int) => a>2
      assert(!forall(u, ff))
      assert(!forall(i, ff))
      assert(forall(d, ff))
      assert(!forall(uu, ff))
    }
  }

  test("filter") {
    new TestSets {
      val u = union(s2, s3)
      val uu = union(u,s4)
      val i = intersect(u, s2)
      val d = diff(uu, s2)
      val ff = (a:Int) => a<3
      val f1 = filter(u, ff)
      val f2 = filter(uu, ff)
      val f3 = filter(i, ff)
      val f4 = filter(d, ff)
      assert(contains(f1, 2))
      assert(!contains(f1, 3))
      assert(contains(f2, 2))
      assert(!contains(f2, 4))
      assert(!contains(f2, 1))
      assert(contains(f3, 2))
      assert(!contains(f3,4))
      assert(!contains(f4,1))
      assert(!contains(f4,2))
      assert(!contains(f4,3))
      assert(!contains(f4,4))
    }
  }

  test("exists") {
    new TestSets {
      val u = union(s2, s3)
      val uu = union(u,s4)
      val i = intersect(u, s2)
      val d = diff(uu, s2)
      val ff = (a:Int) => a<3
      val ff1 = (a:Int) => a>3
      assert(exists(u, ff))
      assert(!exists(u, ff1))
      assert(exists(uu, ff))
      assert(exists(uu, ff1))
      assert(exists(i, ff))
      assert(!exists(i, ff1))
      assert(!exists(d,ff))
      assert(exists(d,ff1))
    }
  }

  test("map") {
    new TestSets {
      val u = union(s2, s3)
      val uu = union(u,s4)
      val d = diff(uu, s2)
      val f = (a:Int) => a*a
      val f1 = (a:Int) => a-1
      val m1 = map(u,f)
      val m2 = map(d,f1)
      assert(contains(m1, 4))
      assert(!contains(m1, 6))
      assert(contains(m1, 9))
      assert(contains(m2, 2))
      assert(contains(m2, 3))
      assert(!contains(m2, 4))
      assert(!contains(m2, 1))
    }
  }
}
