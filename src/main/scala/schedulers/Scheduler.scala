package schedulers

import tsp.Solution
import java.io.{File, BufferedWriter, FileWriter}

trait Scheduler {

  def findParetoFrontApproximation: Set[Solution]

  def writeGnuplotFile: Unit

  def writeGnuplotScript(instanceName: String): Unit = {
    val file = new File("makeApproxPlot.gnuplot")
    val bw = new BufferedWriter(new FileWriter(file))

    bw write ("set terminal 'png'\n")
    bw write ("set output 'pareto.png'\n")
    bw write (
      "plot \"pareto.dat\" with points, \"data/best." + instanceName + ".tsp\" with points"
    )
    bw.close
  }

}
