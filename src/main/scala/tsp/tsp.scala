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

  /** Determine if a solution domine another.
    *
    * @param s1Cost costs of the first solution
    * @param s2Cost costs of the second solution
    * @return true if the first solution domine the second, false otherwise
    */
  def domine(s1Cost: Vector[Int], s2Cost: Vector[Int]): Boolean =
    (s1Cost zip s2Cost) forall {
      case (s1Cost, s2Cost) => s1Cost <= s2Cost
    }

}
