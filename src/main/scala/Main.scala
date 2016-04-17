object Main extends App {
  run(args)

  def run(args: Array[String]): Unit = {
    if (args.length != 1) {
      printHelp()
      return
    }

    try {
      val firstArgument = toInt(args(0))

      firstArgument match {
        case Some(n) =>
          println(MagicSquareGenerator.generateMagicSquare(n))
        case None =>
          println("Please provide a valid integer.")
      }
    } catch {
      case e: Throwable => println(s"Error: ${e.getMessage}")
    }
  }

  private def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: NumberFormatException => None
    }
  }

  private def printHelp(): Unit = {
    println("Usage: magic-squares <n>")
    println()
    println("Example: 'magic-squares 4' will produce a magic square of order 4")
  }
}
