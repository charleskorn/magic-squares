import breeze.linalg.DenseMatrix

object MatrixMethods {
  def rainbowMatrix(n: Int): DenseMatrix[Int] = {
    DenseMatrix.tabulate(n, n) { (i, _) => i + 1 }
  }
}
