package filters

import tsp.Solution

/** Represent a filter of TSP solution. */
trait Filter {

  /** Filter a set of TSP solutions and yield the non-dominated ones.
    *
    * @param solutions set of solutions to filter
    * @return set of non-dominated solutions
    */
  def filter(solutions: Set[Solution]): Set[Solution]

}
