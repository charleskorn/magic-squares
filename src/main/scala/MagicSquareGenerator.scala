object MagicSquareGenerator {
  def generateMagicSquare(n: Int): Array[Array[Int]] = {
    n match {
      case 0 =>
        Array()
      case 1 =>
        Array(Array(1))
    }
  }
}
