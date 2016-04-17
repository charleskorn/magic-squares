import IntegerExtensions._
import MatrixExtensions._
import MatrixMethods._
import breeze.linalg._
import breeze.numerics.floor

object MagicSquareGenerator {
  def generateMagicSquare(n: Int): DenseMatrix[Int] = {
    if (isOddOrder(n)) {
      generateOddSquare(n)
    } else if (isDoublyEvenOrder(n)) {
      generateDoublyEvenSquare(n)
    } else if (isSinglyEvenOrder(n)) {
      generateSinglyEvenSquare(n)
    } else {
      throw NoMagicSquareExistsException(s"There is no magic square of order $n.")
    }
  }

  private def isOddOrder(n: Int) = n % 2 == 1 && n > 0

  private def isDoublyEvenOrder(n: Int) = n % 4 == 0 && n > 0

  private def isSinglyEvenOrder(n: Int) = n % 2 == 0 && n % 4 != 0 && n > 2

  // See http://www.mathworks.com/moler/exm/chapters/magic.pdf for an explanation of the algorithm used here.
  private def generateOddSquare(n: Int): DenseMatrix[Int] = {
    val I = rainbowMatrix(n)
    val J = rainbowMatrix(n).t

    val A = (I + J + (n - 3) / 2).mod(n)
    val B = (I + 2 * J - 2).mod(n)

    n * A + B + 1
  }

  // See http://www.mathworks.com/moler/exm/chapters/magic.pdf for an explanation of the algorithm used here.
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

  // See http://www.1728.org/magicsq3.htm for an explanation of the algorithm used here.
  private def generateSinglyEvenSquare(n: Int): DenseMatrix[Int] = {
    val halfN = n / 2
    val quarter = generateOddSquare(halfN)

    val factor = halfN ** 2
    val top = DenseMatrix.vertcat(quarter, quarter + 3 * factor)
    val bottom = DenseMatrix.vertcat(quarter + 2 * factor, quarter + factor)
    var joined = DenseMatrix.horzcat(top, bottom)

    doSinglyEvenSquareSwaps(joined)

    joined
  }

  private def doSinglyEvenSquareSwaps(matrix: DenseMatrix[Int]): Unit = {
    val n = matrix.cols
    val halfN = n / 2
    val leftSwapSize = (halfN - 1) / 2

    val firstRowInTopHalf = 0
    val middleRowInTopHalf = leftSwapSize
    val lastRowInTopHalf = halfN - 1

    val firstRowInBottomHalf = halfN
    val middleRowInBottomHalf = firstRowInBottomHalf + leftSwapSize
    val lastRowInBottomHalf = n - 1

    val columnsToSwapOnLeft = 0 until leftSwapSize
    val columnsToSwapInMiddleRowsOnLeft = 1 to leftSwapSize

    matrix.swap(
      firstRowInTopHalf until middleRowInTopHalf, columnsToSwapOnLeft
    )(
      firstRowInBottomHalf until middleRowInBottomHalf, columnsToSwapOnLeft
    )

    matrix.swap(
      middleRowInTopHalf + 1 to lastRowInTopHalf, columnsToSwapOnLeft
    )(
      middleRowInBottomHalf + 1 to lastRowInBottomHalf, columnsToSwapOnLeft
    )

    matrix.swap(
      middleRowInTopHalf to middleRowInTopHalf, columnsToSwapInMiddleRowsOnLeft
    )(
      middleRowInBottomHalf to middleRowInBottomHalf, columnsToSwapInMiddleRowsOnLeft
    )

    val rightSwapSize = leftSwapSize - 1

    if (rightSwapSize > 0) {
      val lastColumnInMatrix = n - 1
      val firstColumnToSwap = lastColumnInMatrix - rightSwapSize + 1
      val columnsToSwapOnRight = firstColumnToSwap to lastColumnInMatrix

      matrix.swap(
        firstRowInTopHalf to lastRowInTopHalf, columnsToSwapOnRight
      )(
        firstRowInBottomHalf to lastRowInBottomHalf, columnsToSwapOnRight
      )
    }
  }
}
