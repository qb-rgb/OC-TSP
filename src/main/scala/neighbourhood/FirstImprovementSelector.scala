package neighbourhood

/** Class to represent selector which pick the first neighbour of a list which
  * have a better score.
  *
  * @constructor create a new first improvement selector.
  * @param costFunction cost function to maximize
  */
class FirstImprovementSelector[T](
  override val costFunction: List[T] => Double
) extends Selector(costFunction) {

  /** @see neighbourhood.Selector.select() */
  def select(refElem: List[T], neighbourhood: Stream[List[T]]): List[T] = {
    def innerSelect(refScore: Double, refE: List[T], n: Stream[List[T]]): List[T] = {
      if (n.isEmpty)
        refE
      else {
        val head = n.head

        if ((this costFunction head) < refScore) {
          head
        }
        else
          innerSelect(refScore, refE, n.tail)
      }
    }

    val refScore = this costFunction refElem

    innerSelect(refScore, refElem, neighbourhood)
  }

  override def toString: String = "first improvement"

}
