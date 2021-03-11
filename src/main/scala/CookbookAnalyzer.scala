import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object CookbookAnalyzer extends App {

  println(System.getProperty("user.dir"))

  val relative_path = "src/resources/cookbook.txt"

//  def getLinesFromFile(srcPath: String) = {
//    val bufferedSource = Source.fromFile(srcPath)
//    val lines = bufferedSource.getLines.toSeq
//    bufferedSource.close
//    lines
//  }

  val lines = Utilities.getLinesFromFile(relative_path)


//  def getRecipeTitles(text: Array[String]): Array[String] = {
//    val recipeTitles = slicedText.filter(line => line.nonEmpty && line.toUpperCase == line && !line.startsWith("   "))
//    recipeTitles
//  }
////
////  val recipeTitles = getRecipeTitles(slicedText)
////  recipeTitles.foreach(println)
////
//  def getIngredients(text: Array[String]): Array[String] = {
//    slicedText.filter(line => line.startsWith("   ") && line.nonEmpty)
//  }

//  val ingredients = getIngredients(slicedText)
//

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


//  def getRecipeAndTitles(lines: Map[String, Seq[String]]) : Map[String, Seq[String]] = {
//    for { (k, v) <- recipeAnditlesMap if v.contains(value) } yield k
//  }

  val relative_save_path = "src/resources/cookbook_cleaned.txt"

  import java.io.{PrintWriter, File}
  val pw = new PrintWriter(new File(relative_save_path))

  val finalRes1 = getResult(slicedLines, recipeTitles)

//  for ((key, value) <- finalRes) {
  for (key <- recipeTitles; value <- finalRes1 get key) {

   if(!value.isEmpty){


   // println(s"$key")
//    value.foreach(println)
    pw.write(s"$key "+"\n")

    value.foreach{(ingredient:String) => pw.write(ingredient +"\n" )}

    pw.write("\n")
   }
//    for (key <- value.ingredients ; value2 <- finalRes1 get key) {}
   // value.foreach(ingredients => ingredients.split("\\n+"))
    }


//  pw.write(saveTxt)
  pw.close()

//val finalRes2 = finalRes1 foreach (x => println (x._1 + " - " + x._2))
//
//  val finalRes

//  val finished = finalRes.filter(_._2.contains(value)).map(_._1)

//  for { (k, v) <- finalRes if v.contains(value)} yield k
//
//      println(s"$key")
//      value.foreach(ingredients => ingredients.split("\\n+").foreach(println))
//
//  finalRes.foreach(println)
//    for ((key, value) <- finalRes)
//  for { (k, v) <- finalRes if v.contains(value) } yield k
//  {

//  finalRes.foreach(println)

// val finalRes =  res1.flatMap{ case (key,value) => if (value.contains(s"$ingredients")) Seq(key,value) else Seq() }
//  println(finalRes)

//  val finalRes = getResult(slicedLines, recipeTitles)
//     for ((key, value) <- finalRes) {
//       println(s"$key")
//       value.foreach(println)
//       value.foreach(ingredients => ingredients.split(" \\s+"))
//     }

//    for (key <- recipeTitles; value <- finalRes get key) yield (key,value) {
//      println(s"$key")
//      value.foreach(println)
//      value.foreach(ingredients => ingredients.split(" \\s+"))
//    }

////  finalRes.foreach(println)

//  recipeTitles collect finalRes
  //  for (key <- recipeTitles; value <- finalRes get key)
  //  val filteredResult = finalRes.filter


  //  val rec = finalRes.filter(_._2.contains(ingredients)).map(_._1)
  //  result.foreach(println)

//
//  val resultSeq = getResult(slicedLines, recipeTitles)
//  for ((key, value) <- resultSeq) {
//   value.foreach(ingredients => ingredients.split("\\n+"))
//  }


//    def getRelevantText(slicedLines: Array[String]) : Boolean = {
//      getRecipeTitles(slicedLines) || getIngredients(slicedLines)
//    }
//
//  val relevantText = slicedLines.filter(getRecipeTitles && getIngredients).map



//  val relevantLines = slicedLines.filter(line => (line == line.toUpperCase) || (line => line.startsWith("    ")))
//val filteredRecipes = slicedLines.filter((line => line.length > 0 && line)




//  val recipeTitlesMap = recipeTitles.map(recipe => (recipeTitles, ArrayBuffer[String]())).toMap

//  val saveTxt = result

//   val saveTxt = finalRes1.mkString("\n")
//  saveTxt.foreach(println)

//  val relative_save_path = "src/resources/cookbook_cleaned.txt"
//
//   import java.io.{PrintWriter, File}
//    val pw = new PrintWriter(new File(relative_save_path))
//    pw.write(saveTxt)
//    pw.close()

//  Utilities.saveLines(saveTxt, destPath = relative_save_path)
}