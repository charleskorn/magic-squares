import IntegerExtensions._
import MatrixMethods._
import breeze.linalg._
import breeze.numerics.floor

object MagicSquareGenerator {
  // See http://www.mathworks.com/moler/exm/chapters/magic.pdf for an explanation
  // of the algorithms used here.
  def generateMagicSquare(n: Int): DenseMatrix[Int] = {
    n match {
      case 0 =>
        DenseMatrix.zeros[Int](0, 0)
      case 2 =>
        throw NoMagicSquareExistsException("There is no magic square of order 2.")
      case _ if isOdd(n) =>
        generateOddSquare(n)
      case _ if isDoublyEven(n) =>
        generateDoublyEvenSquare(n)
    }
  }

  private def isOdd(n: Int) = n % 2 == 1
  private def isDoublyEven(n: Int) = n % 4 == 0

  private def generateOddSquare(n: Int): DenseMatrix[Int] = {
    val I = rainbowMatrix(n)
    val J = rainbowMatrix(n).t

    val A = (I + J + (n - 3) / 2).mod(n)
    val B = (I + 2 * J - 2).mod(n)

    n * A + B + 1
  }

  private def generateDoublyEvenSquare(n: Int): DenseMatrix[Int] = {
    DenseMatrix.tabulate(n, n) { (i, j) =>
      val position = i * n + j + 1
      val shouldTakeOpposite = floor(((i + 1) % 4) / 2) == floor(((j + 1) % 4) / 2)

      if (shouldTakeOpposite) {
        n ** 2 + 1 - position
      } else {
        position
      }
    }
  }

}
