import IntegerExtensions._
import MagicSquareGenerator._
import breeze.linalg._
import org.scalatest.{FunSpec, Matchers}

class MagicSquareGeneratorTest extends FunSpec with Matchers {
  describe("Magic square generation") {
    describe("when n = 0") {
      it("returns an empty square") {
        val result = generateMagicSquare(0)
        result.rows shouldBe 0
        result.cols shouldBe 0
      }
    }

    describe("when n = 2") {
      it("throws an exception") {
        a[NoMagicSquareExistsException] should be thrownBy generateMagicSquare(2)
      }
    }

    List(1, 3, 4, 5, 6, 7, 8, 10, 12, 14).foreach { n =>
      describe(s"when n = $n") {
        it(s"returns a valid magic square of order $n") {
          val result = generateMagicSquare(n)

          result.rows shouldBe n
          result.cols shouldBe n

          val expectedSum = (n ** 3 + n) / 2

          columnSums(result) shouldBe vectorOf(expectedSum, size = n)
          rowSums(result) shouldBe vectorOf(expectedSum, size = n)
          firstDiagonalSum(result) shouldBe expectedSum
          secondDiagonalSum(result) shouldBe expectedSum
          allElements(result) should contain theSameElementsAs (1 to n ** 2)
        }
      }
    }
  }

  private def rowSums(square: DenseMatrix[Int]): DenseVector[Int] = {
    sum(square, Axis._0).t
  }

  private def columnSums(square: DenseMatrix[Int]): DenseVector[Int] = {
    sum(square, Axis._1)
  }

  private def firstDiagonalSum(square: DenseMatrix[Int]): Int = {
    trace(square)
  }

  private def secondDiagonalSum(square: DenseMatrix[Int]): Int = {
    trace(square.t)
  }

  private def vectorOf(value: Int, size: Int): DenseVector[Int] = {
    DenseVector.ones[Int](size) * value
  }

  private def allElements(square: DenseMatrix[Int]): Seq[Int] = {
    square.toDenseVector.data
  }
}
