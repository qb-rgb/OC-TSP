package neighbourhood

import scala.util.Random

/** Give all the neighbourhood of a list by exchange.
  */
object ExchangeNeighbourhoodBuilder extends NeighbourhoodBuilder {

  /** @see neighbourhood.NeighbourhoodBuilder.buildNeighbourhood() */
  override def buildNeighbourhood[T](elem: List[T]): Stream[List[T]] = {
    def swap(elem: List[T], i: Int, j: Int): List[T] =
      elem.updated(i, elem(j)).updated(j, elem(i))

    val n = elem.length

    // For this implementation, the instance must have more than one job
    assert(n > 1)

    val is = 0 #:: (1 until n - 1).toStream
    val js = 1 #:: (2 until n).toStream
    val couples = Random.shuffle(is zip js)

    couples map (c => swap(elem, c._1, c._2))
  }

  override def toString: String = "exchange"

}
