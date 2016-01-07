package fr.xebia.scala.basics

/*
 * This is a class definition
 */
class DistanceConverter(rate: Double, value: Double) {
  /*
   * This is a constant defined in the body of the class
   */
  val result = rate * value
}

object DistanceConverter {

  private val MILES_CONVERSION_RATE = 1.609

  /*
   * This is call a partial application and allow us to avoid specifying one or
   * several parameter from a function
   */
  private val mi2KmConverter = new DistanceConverter(MILES_CONVERSION_RATE, _: Double)

  /*
   * Transform km to miles
   * Notes:
   *  - Call the DistanceConverter
   */
  def fromKmToMile(km: Double): Double = new DistanceConverter(MILES_CONVERSION_RATE, km).result

  /*
   * Apply the mi2KmConverter function to each one of the values of the 'varags' specified.
   * You can consider the 'values' parameter simply as a sequence of elements
   */
  def fromKmToMiles(values: Double*): Seq[Double] =
    values.map(mi2KmConverter).map(_.result)

}
