package neighbourhood

/** Class to represent objects that build the neighbourhood of a list using
  * a specific algorithm.
  */
abstract class NeighbourhoodBuilder {

  /** Build the neighbourhood of an list.
    *
    * @param elem list for which build the neighbourhood
    * @return neighbourhood of the list
    */
  def buildNeighbourhood[T](elem: List[T]): Stream[List[T]]

}
