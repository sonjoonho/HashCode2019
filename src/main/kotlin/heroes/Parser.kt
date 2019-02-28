import heroes.Photo
import heroes.Orientation

class Parser(val input: String) {
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

  fun lineToPhoto(line: String): Photo {
    val splitLine = line.split(" ")
    val orientation = if (line[0] == "H") Orientation.HORIZONTAL else Orientation.VERTICAL
    val numberOfTags = line[1].toInt()
    val tags = line.slice(2..numberOfTags)
    return Photo(orientation, tags)
  }
}
