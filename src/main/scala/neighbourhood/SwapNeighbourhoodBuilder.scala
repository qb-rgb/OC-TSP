package neighbourhood

import scala.util.Random

/** Give all the neighbourhood of a list by swap. */
object SwapNeighbourhoodBuilder extends NeighbourhoodBuilder {

  /** @see neighbourhood.NeighbourhoodBuilder.buildNeighbourhood() */
  override def buildNeighbourhood[T](elem: List[T]): Stream[List[T]] = {
    def swap(elem: List[T], i: Int, j: Int): List[T] =
      elem.updated(i, elem(j)).updated(j, elem(i))

    val n = elem.length

    val indices = elem.indices.toStream
    val tails = indices.tail map (i => indices drop i)
    val couples = Random.shuffle((indices zip tails) flatMap (c => c._2 map (i => (c._1, i))))

    couples map (c => swap(elem, c._1, c._2))
  }

  override def toString: String = "swap"

}
