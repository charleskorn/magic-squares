import breeze.linalg._

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
    }
  }

  private def isOdd(n: Int) = n % 2 == 1

  private def generateOddSquare(n: Int): DenseMatrix[Int] = {
    val I = stepMatrix(n)
    val J = stepMatrix(n).t

    val A = modulus(I + J + (n - 3)/2, n)
    val B = modulus(I + 2*J - 2, n)

    n*A + B + 1
  }

  def stepMatrix(n: Int): DenseMatrix[Int] = {
    DenseMatrix.tabulate(n, n){(i, _) => i + 1}
  }

  def modulus(matrix: DenseMatrix[Int], n: Int): DenseMatrix[Int] = {
    matrix.map(v => v % n)
  }
}
