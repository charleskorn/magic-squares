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

      describe("and that argument is '17'") {
        it("prints the magic square of order 17") {
          Main.run(Array("17"))

          outputLines should contain theSameElementsInOrderAs List(
            "155  174  193  212  231  250  269  288  1    20   39   58   77   96   115  134  153",
            "173  192  211  230  249  268  287  17   19   38   57   76   95   114  133  152  154",
            "191  210  229  248  267  286  16   18   37   56   75   94   113  132  151  170  172",
            "209  228  247  266  285  15   34   36   55   74   93   112  131  150  169  171  190",
            "227  246  265  284  14   33   35   54   73   92   111  130  149  168  187  189  208",
            "245  264  283  13   32   51   53   72   91   110  129  148  167  186  188  207  226",
            "263  282  12   31   50   52   71   90   109  128  147  166  185  204  206  225  244",
            "281  11   30   49   68   70   89   108  127  146  165  184  203  205  224  243  262",
            "10   29   48   67   69   88   107  126  145  164  183  202  221  223  242  261  280",
            "28   47   66   85   87   106  125  144  163  182  201  220  222  241  260  279  9",
            "46   65   84   86   105  124  143  162  181  200  219  238  240  259  278  8    27",
            "64   83   102  104  123  142  161  180  199  218  237  239  258  277  7    26   45",
            "82   101  103  122  141  160  179  198  217  236  255  257  276  6    25   44   63",
            "100  119  121  140  159  178  197  216  235  254  256  275  5    24   43   62   81",
            "118  120  139  158  177  196  215  234  253  272  274  4    23   42   61   80   99",
            "136  138  157  176  195  214  233  252  271  273  3    22   41   60   79   98   117",
            "137  156  175  194  213  232  251  270  289  2    21   40   59   78   97   116  135"
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
