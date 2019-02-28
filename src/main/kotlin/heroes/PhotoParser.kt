package heroes

import heroes.Photo
import heroes.Orientation

class PhotoParser(val input: String) {

  var id = 0

  fun parse(): List<Photo> {
    val lines = this.input.lines()
    // N
    // [H|V] <n_tags> <space separated tags>
    val numberOfPhotos = lines[0].toInt()

    println("Number of photos: $numberOfPhotos")

    return lines
      .slice(1..lines.size)
      .map { lineToPhoto(it) }
  }

  private fun lineToPhoto(line: String): Photo {
    val splitLine = line.split(" ")
    val orientation = if (splitLine[0] == "H") Orientation.HORIZONTAL else Orientation.VERTICAL
    val numberOfTags = splitLine[1].toInt()
    val tags = splitLine.slice(2..numberOfTags).toSet()
    return Photo(id++, orientation, tags)
  }
}
