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

    describe("when n = 1") {
      it("returns a square with just the number 1") {
        generateMagicSquare(1) should be(DenseMatrix.ones[Int](1, 1))
      }
    }

    describe("when n = 2") {
      it("throws an exception") {
        a[NoMagicSquareExistsException] should be thrownBy generateMagicSquare(2)
      }
    }

    describe("when n = 3") {
      it("returns a valid magic square of order 3") {
        val result = generateMagicSquare(3)

        result.rows shouldBe 3
        result.cols shouldBe 3

        columnSums(result) shouldBe vectorOf(15, size = 3)
        rowSums(result) shouldBe vectorOf(15, size = 3)
        firstDiagonalSum(result) shouldBe 15
        secondDiagonalSum(result) shouldBe 15
        allElements(result) should contain theSameElementsAs (1 to 9)
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
