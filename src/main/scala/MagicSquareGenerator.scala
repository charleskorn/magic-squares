import IntegerExtensions._
import MatrixExtensions._
import MatrixMethods._
import breeze.linalg._
import breeze.numerics.floor

object MagicSquareGenerator {
  // See http://www.mathworks.com/moler/exm/chapters/magic.pdf for an explanation
  // of the algorithms used here.
  def generateMagicSquare(n: Int): DenseMatrix[Int] = {
    if (isOdd(n)) {
      generateOddSquare(n)
    } else if (n == 2) {
      throw NoMagicSquareExistsException(s"There is no magic square of order $n.")
    } else if (isDoublyEven(n)) {
      generateDoublyEvenSquare(n)
    } else if (isSinglyEven(n)) {
      generateSinglyEvenSquare(n)
    } else {
      throw NoMagicSquareExistsException(s"There is no magic square of order $n.")
    }
  }

  private def isOdd(n: Int) = n % 2 == 1

  private def isDoublyEven(n: Int) = n % 4 == 0

  private def isSinglyEven(n: Int) = n % 2 == 0 && !isDoublyEven(n)

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

  private def generateSinglyEvenSquare(n: Int): DenseMatrix[Int] = {
    val halfN = n / 2
    val quarter = generateOddSquare(halfN)

    val factor = halfN ** 2
    val top = DenseMatrix.vertcat(quarter, quarter + 3 * factor)
    val bottom = DenseMatrix.vertcat(quarter + 2 * factor, quarter + factor)
    var joined = DenseMatrix.horzcat(top, bottom)

    doSwaps(joined)

    joined
  }

  private def doSwaps(matrix: DenseMatrix[Int]): Unit = {
    val n = matrix.cols
    val halfN = n / 2
    val leftSwapSize = (halfN - 1) / 2

    val middleRowInTopHalf = leftSwapSize
    val lastRowInTopHalf = halfN - 1
    val firstRowInBottomHalf = halfN
    val middleRowInBottomHalf = firstRowInBottomHalf + leftSwapSize
    val lastRowInBottomHalf = n - 1
    val columnsToSwapOnLeft = 0 until leftSwapSize

    matrix.swap(
      0 until middleRowInTopHalf, columnsToSwapOnLeft
    )(
      firstRowInBottomHalf until middleRowInBottomHalf, columnsToSwapOnLeft
    )

    matrix.swap(
      middleRowInTopHalf + 1 to lastRowInTopHalf, columnsToSwapOnLeft
    )(
      middleRowInBottomHalf + 1 to lastRowInBottomHalf, columnsToSwapOnLeft
    )

    matrix.swap(
      middleRowInTopHalf to middleRowInTopHalf, 1 to leftSwapSize
    )(
      middleRowInBottomHalf to middleRowInBottomHalf, 1 to leftSwapSize
    )

    val rightSwapSize = leftSwapSize - 1

    if (rightSwapSize > 0) {
      val lastColumn = n - 1
      val firstColumnToSwapOn = lastColumn - rightSwapSize + 1
      val columnsToSwapOnRight = firstColumnToSwapOn to lastColumn

      matrix.swap(
        0 to lastRowInTopHalf, columnsToSwapOnRight
      )(
        firstRowInBottomHalf to lastRowInBottomHalf, columnsToSwapOnRight
      )
    }
  }
}
