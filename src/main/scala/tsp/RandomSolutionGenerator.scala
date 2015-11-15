package tsp

/** Generate random TSP solutions. */
object RandomSolutionGenerator {

  import scala.util.Random

  /** Generate a random TSP solution.
    *
    * @param length length of the solution to Generate
    * @return random solution with the given length
    */
  def generate(length: Int): Solution =
    Random shuffle (0 until length).toList

}
