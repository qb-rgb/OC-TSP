package filters

import tsp.{Solution, Instance, domine}

/** Off-line filter of TSP solutions.
  *
  * @constructor create a new OffLineFilter.
  * @param instance instance to evaluate the solutions
  */
class OffLineFilter(override val instance: Instance) extends Filter(instance) {

  /** @see filters.Filter.filter() */
  def filter(solutions: Set[Solution]): Set[Solution] = {
    val solutionsAndCosts = solutions.toList map {
      case solution => (solution, this.instance solutionCosts solution)
    }

    def innerFilter(
      toTreat: List[(Solution, Vector[Int])],
      res: Set[Solution]
    ): Set[Solution] =
      if (toTreat.isEmpty)
        res
      else {
        val couple@(solution, costs) = toTreat.head
        val othersCosts = (solutionsAndCosts diff List(couple)) map { _._2 }
        val toCompare = othersCosts map { otherCosts => (costs, otherCosts) }
        val isDominated = toCompare exists {
          case (costs, otherCosts) => domine(otherCosts, costs)
        }

        if (!isDominated)
          innerFilter(toTreat.tail, res + solution)
        else
          innerFilter(toTreat.tail, res)
      }

    innerFilter(solutionsAndCosts, Set())
  }

}
