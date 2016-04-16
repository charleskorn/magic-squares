import MatrixMethods._
import breeze.linalg._
import org.scalatest.{FunSpec, Matchers}


class MatrixMethodsTest extends FunSpec with Matchers {
  describe("Rainbow matrix generation") {
    describe("when n = 0") {
      it("returns an empty matrix") {
        val result = rainbowMatrix(0)
        result.rows shouldBe 0
        result.cols shouldBe 0
      }
    }

    describe("when n = 1") {
      it("returns a 1x1 matrix with a single element") {
        val result = rainbowMatrix(1)

        result shouldBe DenseMatrix(1)
      }
    }

    describe("when n = 2") {
      it("returns a 2x2 matrix with the top row all being the number 1 and the bottom row all being the number 2") {
        val result = rainbowMatrix(2)

        result shouldBe DenseMatrix((1, 1), (2, 2))
      }
    }

    describe("when n = 3") {
      it("returns a 3x3 matrix with the top row all being the number 1, the middle row all being 2 and the bottom row all being 3") {
        val result = rainbowMatrix(3)

        result shouldBe DenseMatrix((1, 1, 1), (2, 2, 2), (3, 3, 3))
      }
    }
  }
}
