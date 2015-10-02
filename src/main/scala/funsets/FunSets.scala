package funsets

import common._


object FunSets {

  type Set = Int => Boolean

  def contains(s: Set, elem: Int): Boolean = s(elem)

  def singletonSet(elem: Int): Set = {(num:Int)=> num == elem}

  def union(s: Set, t: Set): Set = {(num:Int)=>s(num)||t(num)}

  def intersect(s: Set, t: Set): Set = {(num:Int)=>s(num)&&t(num)}

  def diff(s: Set, t: Set): Set = {(num:Int)=>s(num)&& !t(num)}

  def filter(s: Set, p: Int => Boolean): Set = {(num:Int)=>s(num)&&p(num)}

  val bound = 1000

  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a < -1000) true
      else if (s(a) && !p(a)) false
      else iter(a-1)
    }
    iter(bound)
  }

  def exists(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a < -1000) false
      else if (s(a) && p(a)) true
      else iter(a-1)
    }
    iter(bound)
  }

  /**
   * Возвращает множество преобразованное применением `f` к каждому элементу `s`.
   */
  def map(s: Set, f: Int => Int): Set = {(num: Int) => exists(s, (p: Int) => f(p) == num)}
  /**
   * Отображает содержимое множества
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
   * Выводит на печать содержимое множества на консоль.
   */
  def printSet(s: Set) {
    println(toString(s))
  }
}
