package main

import schedulers._
import neighbourhood._
import tsp._

object Approximation {

  type OptionMap = Map[String, String]

  private val errorLog = "ERROR"

  private def catchOptions(args: Array[String]): OptionMap = {
    def nextOption(map: OptionMap, args: List[String]): OptionMap = args match {
      case "-instance" :: value :: tail => nextOption(map ++ Map("-instance" -> value), tail)

      case "-ng" :: value :: tail => nextOption(map ++ Map("-ng" -> value), tail)

      case "-nn" :: value :: tail => nextOption(map ++ Map("-nn" -> value), tail)

      case "-sc" :: value :: tail => nextOption(map ++ Map("-sc" -> value), tail)

      case Nil => map

      case _ => Map("unknownOption" -> this.errorLog)
    }

    nextOption(Map(), args.toList)
  }

  private def getInstance(s: String): Instance = s match {
    case "AB" => randomAB100
    case "CD" => randomCD100
    case "EF" => randomEF100
  }

  // Check if all the arguments are correct
  private def checkOptions(map: OptionMap): Boolean =
    !map.isEmpty && !(map.values.toList contains this.errorLog)

  def main(args: Array[String]): Unit = {
    val options = this.catchOptions(args)
    val stringSc = options("-sc")
    val instance = this getInstance options("-instance")
    val ng = options("-ng").toInt
    val sc =
      if (stringSc == "vector")
        new HillClimbingApproximator(
          instance,
          TwoOptNeighbourhoodBuilder,
          generateTwoDimensionsWeightsVectors(ng)
        )
      else
        new ParetoApproach(
          instance,
          TwoOptNeighbourhoodBuilder,
          ng,
          options("-nn").toInt
        )

    sc.writeGnuplotFile
  }

}
