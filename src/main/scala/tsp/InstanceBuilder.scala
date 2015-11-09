package tsp

/** Build instance from files. */
object InstanceBuilder {

  import scala.collection.immutable.VectorBuilder
  import scala.io.Source

  // Number of elements by objective
  private val nbElem = 100

  // Number of documentation lines at the begining of a objective file
  private val nbDocLines = 7

  // Extract the content of a file
  private def getFileContent(filePath: String): List[String] = {
    val source = Source.fromFile(filePath)
    val lines = try
      source.getLines.toList
    catch
      case e: Exception => List("")

    source.close()

    lines drop this.nbDocLines
  }

  // Group lines by elements
  private def groupLines(
    lines: List[String],
    actual: List[String],
    res: List[List[String]],
    nbElem: Int,
    nbLine: Int
  ): List[List[String]] =
    if (lines.isEmpty)
      res
    else if (nbLine == this.nbElem - 1)
      res :+ lines
    else if (nbElem == this.nbElem - nbLine)
      groupLines(lines, Nil, res :+ actual, 0, nbLine + 1)
    else
      groupLines(lines.tail, actual :+ lines.head, res, nbElem + 1, nbLine)

  // Process the content of a file
  private def processContent(content: List[List[String]]): Vector[Vector[Int]] = {
    // Process the lines for one elements
    def processLines(lines: List[String]): Vector[Int] = {
      val builder = new VectorBuilder[Int]()

      for (line <- lines) builder += line.toInt

      builder.result
    }

    (content map processLines).toVector
  }

  /** Build an instance from files.
    *
    * @param filesNames list of files which contain the objectives costs of the
    *                   instance
    */
  def buildFrom(filesPaths: List[String]): Instance = {
    // Content of the files
    val contents = filesPaths map getFileContent
    // Content of the files grouped by elem
    val groupedContent = contents map {
      content => this.groupLines(content, Nil, Nil, 0, 0)
    }

    new Instance(groupedContent map processContent)
  }

}
