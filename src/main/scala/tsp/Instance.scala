package tsp

/** Represent an instance of the TSP problem.
  *
  * @constructor create a new Instance.
  * @param costs cost of the several objectives of the instance
  */
class Instance(val nbElem: Int, val costs: List[Vector[Vector[Int]]]) {

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

  /** Get the cost of a given element order for the instance.
    *
    * @param order order of the element of the instance
    * @return cost of the given order
    */
  def costForOrder(order: List[Int]): Int = {
    require(order.length == this.nbElem)

    def getSum(i: Int, j: Int): Int =
      (for (cost <- this.costs) yield cost(i)(j)).sum

    def sumOrder(order: List[Int], res: Int): Int = order match {
      case i :: j :: tail => sumOrder(order.tail, getSum(i, j))
      case _              => res
    }

    sumOrder(order :+ order.head, 0)
  }

}