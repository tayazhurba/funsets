package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * Этот класс - набор методов для объектов в классе FunSets. Для запуска тестов вы можете:
  *  - набрать команду "test" в sbt консоли (sbt test)
 *   - В IDE правой кнопкой на файле "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Ссылка на scaladoc - очень понятный и детализированный туториал по FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Операторы
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Тесты написаны с использованием оператора "test" и метода "assert".
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * Для тестов ScalaTest, существуют специальный оператор эквивалентности "===" который
   * может быть использован внутри "assert". Если assertion не выполняется, оба значения будут напечатаны в сообщении об ошибке.
   * В противном случае, когда используем "==", сообщение о провале теста скажет только
   * "assertion failed", без показа значений.
   *
   * Попробуйте это в действии! Измените значения так, чтобы assert давал fail и
   * взгляните на сообщение об ошибке.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * Когда пишите тесты, вам бы часто хотелось повторно использовать определенные значения
   * для множества тестов. Например, Мы бы хотели создать Int-set и сделать для него
   * несколько тестов
   * 
   * 
   * Вместо того чтобы копи-пастить код для создания тестового set-а в каждом тесте
   * мы можем сохранить его в классе теста используя val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * Однако, что случится, если метод "singletonSet" содержит баг и падает? Тогда
   * тестовые методы даже не будут запущены, т.к. создание тест-класса падает!
   * 
   * Вместо этого мы, мы поместим совместно разделяемые значения в отдельный trait 
   * (трейты похожи на абстрактные классы), и создадим экземпляр внутри каждого тестового метода.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * Этот тест сейчас выключен (засчет использования "ignore") поскольку метод
   * "singletonSet" еще не реализован и тест провалится.
   * 
   * Когда вы напишите реализацию "singletonSet", замените 
   * функцию "ignore" на "test".
   */
  ignore("singletonSet(1) contains 1") {
    
    /**
     * Мы создадим новый экземпляр трейта "TestSets", это даст нам доступ
     * к значениям от "s1" до "s3". 
     */
    new TestSets {
      /**
       * Строковый аргумент команды "assert" - это сообщение, которое выводится в случае
       * провала теста. Это помогает идентифицировать, какой assert провалился.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  ignore("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
}
