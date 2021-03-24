import scala.io.Source
import java.io.FileOutputStream
import java.io.PrintStream
import scala.io.StdIn.readLine
import java.io.File
import java.io.PrintWriter
import io.circe.parser._
import io.circe.generic.auto._
import io.circe.{Encoder, Json}
import io.circe.syntax._

object main extends App{

  case class CountryName(official: String)
  case class Country(name: CountryName, capital: List[String], region: String, area: Float)
  case class CountryPrintOut(name: String, capital: String, area: Float)

  def source = Source.fromURL("https://raw.githubusercontent.com/mledoze/countries/master/countries.json").mkString


  var countriesList: List[Country] = Nil

  val result = decode[List[Country]](source) match {
    case Right(countries) => countriesList = countries.filter(_.region == "Africa").sortBy(_.area).reverse.take(10)
    case Left(error) => print("Oups" + error)
  }

  if (countriesList.nonEmpty) {
    val countriesListOut: List[CountryPrintOut] = for(country <- countriesList) yield CountryPrintOut(country.name.official, country.capital.head, country.area)

    val out = countriesListOut.asJson

    val outputFile = args(0)
    val fos = new FileOutputStream(outputFile)
    val printer = new PrintStream(fos)
    printer.println(out)
  }


}
