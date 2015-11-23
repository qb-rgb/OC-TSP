package neighbourhood

/** Class to represent selector which pick the best neighbour of a list.
  *
  * @constructor create a new best improvement selector.
  * @param costFunction cost function to maximize
  */
class BestImprovementSelector[T](override val costFunction: List[T] => Int) extends Selector(costFunction) {

  /** @see neighbourhood.Selector.select() */
  def select(refElem: List[T], neighbourhood: Stream[List[T]]): List[T] = {
    def innerSelect(refScore: Int, refE: List[T], n: Stream[List[T]]): List[T] = {
      if (n.isEmpty)
        refE
      else {
        val head = n.head
        val newScore = this costFunction head

        if (newScore < refScore) {
          innerSelect(newScore, head, n.tail)
        }
        else
          innerSelect(refScore, refE, n.tail)
      }
    }

    val refScore = this costFunction refElem

    innerSelect(refScore, refElem, neighbourhood)
  }

  override def toString: String = "best improvement"

}
