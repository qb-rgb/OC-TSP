/** Contain all the classes to represent TSP problem elements. */
package object tsp {

  /** Solution of an instance. */
  type Solution = List[Int]

  /** Instance with objectives A and B. */
  val randomAB100 = InstanceBuilder.buildFrom(
    List("data/randomA100.tsp", "data/randomB100.tsp")
  )

  /** Instance with objectives C and D. */
  val randomCD100 = InstanceBuilder.buildFrom(
    List("data/randomC100.tsp", "data/randomD100.tsp")
  )

  /** Instance with objectives E and F. */
  val randomEF100 = InstanceBuilder.buildFrom(
    List("data/randomE100.tsp", "data/randomF100.tsp")
  )

}
