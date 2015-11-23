package neighbourhood

/** Give all the neighbourhood of a list using 2-opt. */
object TwoOptNeighbourhoodBuilder extends NeighbourhoodBuilder {

  import scala.util.Random

  /** @see neighbourhood.NeighbourhoodBuilder.buildNeighbourhood() */
  override def buildNeighbourhood[T](elem: List[T]): Stream[List[T]] = {
    def twoOpt(elem: List[T], i: Int, j: Int): List[T] = {
      require(i < j)

      val before = elem take i
      val toReverse = elem.slice(i, j + 1)
      val after = elem drop (j + 1)

      before ++ toReverse.reverse ++ after
    }

    val n = elem.length

    val is = 0 #:: (1 until n - 1).toStream
    val js = is flatMap { i => (i + 1) #:: (i + 2 until n).toStream }
    val couples = Random shuffle (
      is flatMap {
        i => (i + 1) #:: (i + 2 until n).toStream map {
          j => (i, j)
        }
      }
    )

    couples map (c => twoOpt(elem, c._1, c._2))
  }

  override def toString: String = "2-opt"

}
