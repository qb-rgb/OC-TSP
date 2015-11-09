package tsp

/** Represent an instance of the TSP problem.
  *
  * @constructor create a new Instance.
  * @param costs cost of the several objectives of the instance
  */
class Instance(val costs: List[Vector[Vector[Int]]])
