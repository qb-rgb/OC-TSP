package neighbourhood

/** Class to represent a selector which pick an element from the neighbourhood
  * of a list to maximize a cost function.
  *
  * @constructor create a new selector.
  * @param costFunction cost function to maximize
  */
abstract class Selector[T](val costFunction: List[T] => Double) {

  /** Selection strategy of a neighbour from a list.
    *
    * @param refElem reference list (the one from which the neighbourhood is
    *                created)
    * @param neighbourhood neighbourhood of the reference list
    * @return the neighbour which maximize the cost function following the
    *         strategy of selection
    */
  def select(elem: List[T], neighbourhood: Stream[List[T]]): List[T]

}
