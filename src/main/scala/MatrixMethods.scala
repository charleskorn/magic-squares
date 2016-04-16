import breeze.linalg.DenseMatrix

object MatrixMethods {
  def rainbowMatrix(n: Int): DenseMatrix[Int] = {
    DenseMatrix.tabulate(n, n){(i, _) => i + 1}
  }

  implicit class MatrixExtensions(matrix: DenseMatrix[Int]) {
    def mod(n: Int): DenseMatrix[Int] = {
      matrix.map(v => v % n)
    }

    def swap(rowSlice1: Seq[Int], colSlice1: Seq[Int])(rowSlice2: Seq[Int], colSlice2: Seq[Int]): Unit = {
      val temp = matrix(rowSlice1, colSlice1).copy

      matrix(rowSlice1, colSlice1) := matrix(rowSlice2, colSlice2)
      matrix(rowSlice2, colSlice2) := temp
    }
  }
}
