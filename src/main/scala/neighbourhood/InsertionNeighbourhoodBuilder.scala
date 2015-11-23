package neighbourhood

import scala.util.Random

/** Give all the neighbourhood of a list by insertion. */
object InsertionNeighbourhoodBuilder extends NeighbourhoodBuilder {

  /** @see neighbourhood.NeighbourhoodBuilder.buildNeighbourhood() */
  override def buildNeighbourhood[T](elem: List[T]): Stream[List[T]] = {
    def insert(elem: List[T], i: Int, j: Int): List[T] = {
      val bool = i < j
      val firstElems = elem take math.min(i, j)
      val middleElems =
        if (bool)
          elem.slice(i + 1, j + 1)
        else
          elem.slice(j, i)
      val lastElems = elem drop (math.max(i, j) + 1)

      if (bool)
        firstElems ++ middleElems ++ List(elem(i)) ++ lastElems
      else
        firstElems ++ List(elem(i)) ++ middleElems ++ lastElems
    }

    val n = elem.length

    val is = 0 #:: (1 until n).toStream
    val couples = Random.shuffle((is flatMap (i => (is map (j => (i, j))))) filterNot (c => c._1 == c._2))

    couples map (c => insert(elem, c._1, c._2))
  }

  override def toString: String = "insertion"

}
