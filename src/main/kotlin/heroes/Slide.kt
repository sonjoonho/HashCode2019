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

  val fstHalfVertvertical = vertical.subList(0, vertical.size / 2)
  val sndHalfvertical = vertical.subList(vertical.size / 2, vertical.size)
  val vertPairSlides = fstHalfVertvertical.zip(sndHalfvertical) {p1, p2 -> DoubleSlide(p1, p2)}

  return vertPairSlides
}
