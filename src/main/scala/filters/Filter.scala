package filters

import tsp.{Solution, Instance}

/** Represent a filter of TSP solution.
  *
  * @param instance instance to evaluate the solutions
  */
abstract class Filter(val instance: Instance) {

  /** Filter a set of TSP solutions and yield the non-dominated ones.
    *
    * @param solutions set of solutions to filter
    * @return set of non-dominated solutions
    */
  def filter(solutions: Set[Solution]): Set[Solution]

}
