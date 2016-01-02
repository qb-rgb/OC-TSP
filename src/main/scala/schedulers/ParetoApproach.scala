package schedulers

import tsp.{Instance, Solution, RandomSolutionGenerator}
import neighbourhood.{NeighbourhoodBuilder, FirstImprovementSelector}
import filters.OffLineFilter
import java.io.{File, BufferedWriter, FileWriter}
import scala.collection.immutable.HashSet

class ParetoApproach(
  val instance: Instance,
  val neighbourhoodBuilder: NeighbourhoodBuilder,
  val nbGeneration: Int,
  val nbNeighbors: Int
) extends Scheduler {

  private val filter = new OffLineFilter(this.instance)

  // Find a pareto front approximation and possibly print avancement
  private def findParetoFrontApproximation(doPrint: Boolean): Set[Solution] = {
    def innerFind(
      solutions: Set[Solution],
      flags: HashSet[Solution],
      nbGen: Int
    ): Set[Solution] =
      if (nbGen == this.nbGeneration)
        solutions
      else {
        if (doPrint) print("\t[" + nbGen * 100 / this.nbGeneration + "%]\r")
        val focusSol = solutions filterNot flags.contains
        val nghd = (for {
          solution <- focusSol
        } yield (
          this.neighbourhoodBuilder buildNeighbourhood solution
        ).take(this.nbNeighbors).toSet).flatten
        val toKeep = this.filter filter (solutions ++ nghd)

        innerFind(toKeep, flags ++ focusSol, nbGen + 1)
      }

    if (doPrint) println("\n\tSearching the pareto front approximation...")

    val initSol = RandomSolutionGenerator generate this.instance.nbElem
    val approx = innerFind(Set(initSol), HashSet(), 1)

    if (doPrint) println("\tDone.")

    approx
  }

  /** Find a pareto front approximation with the weights vectors.
    *
    * @return set of solution which compose the pareto front approximation
    */
  def findParetoFrontApproximation: Set[Solution] =
    this.findParetoFrontApproximation(false)

  /** Create a file with the HillClimbingApproximator pareto front approximation. */
  def writeGnuplotFile: Unit = {
    def processSolution(sCosts: Vector[Int]): String =
      sCosts mkString "\t"

    def writeFile(filename: String, content: Set[Solution]): Unit = {
      val file = new File(filename)
      val bw = new BufferedWriter(new FileWriter(file))
      val text = content map {
        solution => processSolution(this.instance solutionCosts solution)
      }

      bw write (text mkString "\n")
      bw.close
    }

    // Write the gnuplot script
    this.writeGnuplotScript(this.instance.toString)

    val startTime = System.currentTimeMillis
    writeFile("pareto.dat", this.findParetoFrontApproximation(true))
    val endTime = System.currentTimeMillis
    println("\texecution time: " + (endTime - startTime) + " ms")
  }

}
