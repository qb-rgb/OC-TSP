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

  /** Write two gnuplot file with the results of the filter.
    * The two files are dominated.dat which contains the data of the dominated
    * solutions and non-dominated.dat which contains the data of the non-dominated
    * solutions.
    *
    * @param solutions set of solutions from which build the files
    */
  def exportToGnuplotWith(solutions: Set[Solution]): Unit = {
    val nonDominatedSolutions = this filter solutions
    val dominatedSolutions = solutions -- nonDominatedSolutions

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

    // non-dominated.dat
    writeFile("non-dominated.dat", nonDominatedSolutions)

    // dominated.dat
    writeFile("dominated.dat", dominatedSolutions)
  }

}
