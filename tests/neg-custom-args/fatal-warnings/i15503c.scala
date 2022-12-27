// scalac: -Wunused:privates
trait C
class A:
  self: C => // OK
  class B:
    private[A] val a = 1 // OK
    private[B] val b = 1 // OK
    private[this] val c = 1 // error
    private val d = 1 // error

    private[A] val e = 1 // OK
    private[this] val f = e // OK
    private val g = f // OK

    private def fac(x: Int): Int = // error
      if x == 0 then 1 else x * fac(x - 1)

    val x = 1 // OK
    def y = 2 // OK
    def z = g // OK