import scala.math._

object IntegerExtensions {
  implicit class PowerInt(i: Int) {
    def **(b: Int): Int = pow(i, b).intValue
  }
}
