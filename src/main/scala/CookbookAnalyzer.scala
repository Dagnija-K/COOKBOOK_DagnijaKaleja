import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object CookbookAnalyzer extends App {

  println(System.getProperty("user.dir"))

  val relative_path = "src/resources/cookbook.txt"

  val lines = Utilities.getLinesFromFile(relative_path)

  def findNeedle(lines: Seq[String], needle: String): Int = {
    lines.indexWhere(line => line.toLowerCase.contains(needle.toLowerCase))
  }
  val startLine = findNeedle(lines, needle = "FORMULA FOR MAKING THREE GALLONS OF BREAKFAST COCOA")
  val endLine = findNeedle(lines, needle = "Put all the ingredients, save the salt, chocolate and flavoring, over")
  val slicedLines = lines.slice(startLine, endLine)

  val recipeTitles = slicedLines.filter(line => line.toUpperCase == line && !line.startsWith("   ") && line.nonEmpty)
  val ingredients = slicedLines.filter(line => line.startsWith("   ") && line.nonEmpty)


  def getResult(lines: Seq[String], recipeTitles: Seq[String]): Map[String, Seq[String]] = {
    val recipeMap = recipeTitles.map(recipeTitle => (recipeTitle, ArrayBuffer[String]())).toMap
//get rid of empty lines
    var curRecipe = ""
    for (line <- lines) {
      if (recipeMap.contains(line)) {
        curRecipe = line
      } else if (line.startsWith("   ") && line.nonEmpty) {
        val ingredients = recipeMap(curRecipe)
        ingredients += line
      }
    }
    val initialResult = for ((recipeTitle, ingredients) <- recipeMap) yield (recipeTitle, ingredients.toSeq)
    initialResult
    }

  val relative_save_path = "src/resources/cookbook_cleaned.txt"

  import java.io.{PrintWriter, File}
  val pw = new PrintWriter(new File(relative_save_path))

  val finalRes1 = getResult(slicedLines, recipeTitles)

//  for ((key, value) <- finalRes) {
  for (key <- recipeTitles; value <- finalRes1 get key) {
   if(!value.isEmpty) {
    pw.write(s"$key "+"\n")
    value.foreach{(ingredient:String) => pw.write(ingredient +"\n" )}
    pw.write("\n")
    }
   }

  pw.close()

}