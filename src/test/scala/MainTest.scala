import java.io.ByteArrayOutputStream

import org.scalatest.{FunSpec, Matchers}

class MainTest extends FunSpec with Matchers {
  val outputStream = new ByteArrayOutputStream()

  override def withFixture(test: NoArgTest) = {
    outputStream.reset()

    Console.withOut(outputStream) {
      super.withFixture(test)
    }
  }

  private def outputLines(): Seq[String] = {
    outputStream.toString().split("\n").map(l => l.trim)
  }

  describe("The magic number application") {
    describe("when invoked with no arguments") {
      it("prints some useful help information") {
        Main.run(Array())

        outputStream.toString() should include ("Usage: ")
      }
    }

    describe("when invoked with more than one argument") {
      it("prints some useful help information") {
        Main.run(Array("2", "3"))

        outputStream.toString() should include ("Usage: ")
      }
    }

    describe("when invoked with one argument") {
      describe("and that argument is '1'") {
        it("prints the magic square of order 1") {
          Main.run(Array("1"))

          outputStream.toString().trim should be ("1")
        }
      }

      describe("and that argument is '2'") {
        it("prints an error message saying there is no magic square of order 2") {
          Main.run(Array("2"))

          outputStream.toString().trim should be ("Error: There is no magic square of order 2.")
        }
      }

      describe("and that argument is '4'") {
        it("prints the magic square of order 4") {
          Main.run(Array("4"))

          outputLines should contain theSameElementsInOrderAs List(
            "16  2   3   13",
            "5   11  10  8",
            "9   7   6   12",
            "4   14  15  1"
          )
        }
      }

      describe("and that argument is '-1'") {
        it("prints an error message saying there is no magic square of order -1") {
          Main.run(Array("-1"))

          outputStream.toString().trim should be ("Error: There is no magic square of order -1.")
        }
      }

      describe("and that argument is a decimal number") {
        it("prints an error message saying that it is not an integer") {
          Main.run(Array("1.5"))

          outputStream.toString().trim should be ("Please provide a valid integer.")
        }
      }

      describe("and that argument is not a number") {
        it("prints an error message saying that it is not an integer") {
          Main.run(Array("asdlkfj"))

          outputStream.toString().trim should be ("Please provide a valid integer.")
        }
      }
    }
  }
}
