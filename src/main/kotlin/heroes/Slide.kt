package heroes

interface Slide {
  val tags: Set<String>
}

data class SingleSlide(val photo: Photo) : Slide {
  override val tags: Set<String> = photo.tags
}

data class DoubleSlide(val photo1: Photo, val photo2: Photo) : Slide {
  override val tags: Set<String> = photo1.tags + photo2.tags
}

fun verticalPhotosToSlides(vertical: List<Photo>) : List<DoubleSlide> {
  // Map vertical photos to double slides
  var sortedVertical = vertical.sortedByDescending { it.tags.size }
  if (sortedVertical.size % 2 != 0) {
    sortedVertical = sortedVertical.dropLast(1)
  }

  val vertPairList = sortedVertical.chunked(2)

  val doubleSlides = vertPairList.map { DoubleSlide(it.first(), it.last()) }

  return doubleSlides
}
