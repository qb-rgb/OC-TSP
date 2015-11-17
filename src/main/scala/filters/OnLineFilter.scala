package filters

import tsp.{Instance, Solution, domine}

class OnLineFilter(override val instance: Instance) extends Filter(instance) {

  /** @see filters.Filter.filter() */
  def filter(solutions: Set[Solution]): Set[Solution] = {
    type Archive = List[(Vector[Int], Solution)]

    // Treat one solution by comparing it to the archive
    def treatSolution(
      archive: Archive,
      newArchive: Archive,
      solution: Solution,
      costs: Vector[Int],
      toInsert: Boolean
    ): Archive = {
      if (archive.isEmpty)
        if (toInsert)
          (costs, solution) :: newArchive
        else
          newArchive
      else {
        // Solution of the archive
        val compare@(toCompare, _) = archive.head

        // If the on-line solution domine the archive one
        if (domine(costs, toCompare))
          // The archive solution will not be in the new archive
          treatSolution(archive.tail, newArchive, solution, costs, toInsert)
        // If the one-line solution is dominated by the archive one
        else if (domine(toCompare, costs))
          // The archive solution will be in the new archive contrary to the on-line one
          treatSolution(archive.tail, compare :: newArchive, solution, costs, false)
        else
          treatSolution(
            archive.tail,
            compare :: newArchive,
            solution,
            costs,
            toInsert
          )
      }
    }

    // Filter all the solution using an archive
    def innerFilter(solutions: Set[Solution], archive: Archive): Archive = {
      if (solutions.isEmpty)
        archive
      else {
        val current = solutions.head

        innerFilter(
          solutions.tail,
          treatSolution(
            archive,
            Nil,
            current,
            this.instance solutionCosts current,
            true
          )
        )
      }
    }

    if (solutions.isEmpty)
      Set()
    else {
      val head = solutions.head
      val archive = innerFilter(
        solutions.tail,
        List((this.instance solutionCosts head, head))
      )

      (archive map { case (_, solution) => solution }).toSet
    }
  }

}
