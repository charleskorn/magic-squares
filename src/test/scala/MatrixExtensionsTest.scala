import MatrixExtensions._
import breeze.linalg._
import org.scalatest.{FunSpec, Matchers}

class MatrixExtensionsTest extends FunSpec with Matchers {
  describe("Matrix modulus") {
    describe("when given an empty matrix") {
      it("returns an empty matrix") {
        val result = DenseMatrix.zeros[Int](0, 0).mod(10)
        result.rows shouldBe 0
        result.cols shouldBe 0
      }
    }

    describe("when given a matrix of values and a divisor") {
      it("returns that matrix with each element modulo that divisor") {
        val input = DenseMatrix(
          (1, 2, 3),
          (4, 5, 6),
          (7, 8, 9)
        )


        val result = input.mod(5)

        result shouldBe DenseMatrix(
          (1, 2, 3),
          (4, 0, 1),
          (2, 3, 4)
        )
      }
    }
  }

  describe("Submatrix swap") {
    describe("when given a matrix and two ranges to swap") {
      it("swaps the two ranges in place") {
        val matrix = DenseMatrix(
          (1, 2, 3),
          (4, 5, 6),
          (7, 8, 9)
        )

        matrix.swap(0 to 1, 0 to 0)(1 to 2, 2 to 2)

        matrix shouldBe DenseMatrix(
          (6, 2, 3),
          (9, 5, 1),
          (7, 8, 4)
        )
      }
    }
  }
}
