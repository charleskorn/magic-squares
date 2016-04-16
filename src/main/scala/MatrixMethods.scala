import breeze.linalg.DenseMatrix

object MatrixMethods {
  def rainbowMatrix(n: Int): DenseMatrix[Int] = {
    DenseMatrix.tabulate(n, n){(i, _) => i + 1}
  }

  implicit class ModulusMatrix(matrix: DenseMatrix[Int]) {
    def mod(n: Int): DenseMatrix[Int] = {
      matrix.map(v => v % n)
    }
  }
}
