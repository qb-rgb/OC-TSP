package tsp

/** Represent an instance of the TSP problem.
  *
  * @constructor create a new Instance.
  * @param costs cost of the several objectives of the instance
  */
class Instance(val costs: List[Vector[Vector[Int]]]) {

  /** Give the cost between two elements for a given objective.
    *
    * @param obj objective
    * @param i first element
    * @param j second element
    * @return cost between i and j for the obj objective
    */
  def getCostFor(obj: Int, i: Int, j: Int): Int =
    if (i <= j)
      this.costs(obj)(i)(j)
    else
      this.costs(obj)(j)(i)

}
