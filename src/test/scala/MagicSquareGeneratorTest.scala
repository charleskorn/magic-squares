import org.scalatest.{FunSpec, Matchers}
import MagicSquareGenerator._

class MagicSquareGeneratorTest extends FunSpec with Matchers {
  describe("Magic square generation") {
    describe("when n = 0") {
      it("should return an empty square") {
        generateMagicSquare(0) shouldBe empty
      }
    }

    describe("when n = 1") {
      it("should return a square with just the number 1") {
        generateMagicSquare(1) should be (Array(Array(1)))
      }
    }
  }
}
