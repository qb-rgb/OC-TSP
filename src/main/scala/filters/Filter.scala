package filters

import tsp.{Solution, Instance}

/** Represent a filter of TSP solution.
  *
  * @param instance instance to evaluate the solutions
  */
abstract class Filter(val instance: Instance) {

  import java.io.{File, BufferedWriter, FileWriter}

  /** Filter a set of TSP solutions and yield the non-dominated ones.
    *
    * @param solutions set of solutions to filter
    * @return set of non-dominated solutions
    */
  def filter(solutions: Set[Solution]): Set[Solution]

  def exportToGnuplotWith(solutions: Set[Solution]): Unit = {
    val nonDominatedSolutions = this filter solutions
    val dominatedSolutions = solutions -- nonDominatedSolutions

    def processSolution(sCosts: Vector[Int]): String =
      sCosts mkString "\t"

    // non-dominated.dat

    val nonDominatedFile = new File("non-dominated.dat")
    val bw1 = new BufferedWriter(new FileWriter(nonDominatedFile))
    val nonDominatedText = nonDominatedSolutions map {
      solution => processSolution(this.instance solutionCosts solution)
    }

    bw1 write (nonDominatedText mkString "\n")
    bw1.close

    // dominated.dat

    val dominatedFile = new File("non-dominated.dat")
    val bw2 = new BufferedWriter(new FileWriter(dominatedFile))
    val dominatedText = dominatedSolutions map {
      solution => processSolution(this.instance solutionCosts solution)
    }

    bw2 write (dominatedText mkString "\n")
    bw2.close
  }

}
