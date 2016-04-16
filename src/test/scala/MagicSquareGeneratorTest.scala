import org.scalatest.{FunSpec, Matchers}
import MagicSquareGenerator._

class MagicSquareGeneratorTest extends FunSpec with Matchers {
  describe("Magic square generation") {
    describe("when n = 0") {
      it("should return an empty square") {
        generateMagicSquare(0) shouldBe empty
      }
    }
  }
}
