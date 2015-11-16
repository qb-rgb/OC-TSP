package tsp

/** Represent an instance of the TSP problem.
  *
  * @constructor create a new Instance.
  * @param nbElem amount of elements a solution of this instance must have
  * @param costs cost of the several objectives of the instance
  */
class Instance(val nbElem: Int, val costs: List[Vector[Vector[Int]]]) {

  /** Number of objectifs the instance have. */
  val nbObjectives: Int = this.costs.length

  /** Give the cost between two elements for a given objective.
    *
    * @param obj objective
    * @param i first element
    * @param j second element
    * @return cost between i and j for the obj objective
    */
  def getCostFor(obj: Int, i: Int, j: Int): Int =
    if (i <= j)
      this.costs(obj)(i)(j - i)
    else
      this.costs(obj)(j)(i - j)

  /** Get the cost of a given solution for the instance.
    *
    * @param solution solution of the instance
    * @return cost of the given solution for each objectives
    */
  def solutionCosts(solution: Solution): Vector[Int] = {
    require(solution.length == this.nbElem)

    def sumOrder(solution: Solution, obj: Int, res: Int): Int = solution match {
      case i :: j :: tail => sumOrder(j :: tail, obj, res + this.getCostFor(obj, i, j))
      case _              => res
    }

    (for {
      cost <- 0 until this.costs.length
    } yield sumOrder(solution :+ solution.head, cost, 0)).toVector
  }

}
