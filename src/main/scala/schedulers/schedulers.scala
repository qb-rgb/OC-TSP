/** Contain the scheduler for the TSP problem. */
package object schedulers {

  /** Generate two dimensions weights vectors.
    *
    * @return set of generated vectors
    */
  def generateTwoDimensionsWeightsVectors(n: Int): Set[Vector[Double]] = {
    val step = 1.0 / n
    (for (i <- 0.0 to 1.0 by step) yield Vector(i, 1.0 - i)).toSet
  }

}
