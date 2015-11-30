/** Contain the scheduler for the TSP problem. */
package object schedulers {

  /** Generate two dimensions weights vectors.
    *
    * @return set of generated vectors
    */
  def generateTwoDimensionsWeightsVectors: Set[Vector[Double]] =
    (for (i <- 0.00 to 1.00 by 0.01) yield Vector(i, 1.00 - i)).toSet

}
