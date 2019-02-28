package heroes

// Input filename into args[0]
fun main(args: Array<String>) {
  val file = File(args[0])
  val parser = Parser(file.readText())

  
}

enum class Orientation {VERTICAL, HORIZONTAL}

data class Photo(val orientation: Orientation, val tags: Set<String>) {
  // Photo has a set of strings, which represents its tags
}

interface Slide {
  val tags: Set<String>

  companion object {
    fun getInterestFactor(s1 : Photo, s2 : Photo) : Int {
      // Gets the minimum of counttagsins1butnotins2, counttagsins2butnotins1,
      // counttagsincommon
    }
  }
}

data class SingleSlide(val photo: Photo) : Slide {
  override val tags: Set<String> = photo.tags
}

data class DoubleSlide(val photo1: Photo, val photo2: Photo) : Slide {
  override val tags: Set<String> = photo1.tags + photo2.tags
}

object Tags {
  fun countTagsInCommon(p1 : Photo, p2 : Photo) : Int {
    return p1.tags.intersect(p2.tags).count
  }

  fun countTagsInP1ButNotInP2(p1 : Photo, p2 : Photo) : Int {
    return p1.tags - countTagsInCommon(p1, p2)
  }

  fun countTagsInP2ButNotInP1(p1 : Photo, p2 : Photo) : Int {
    return p2.tags - countTagsInCommon(p1, p2)
  }
}

class Slideshow(val slides: List<Slide>) {
  companion object {
    // Algorithm re-orders an arbitrary list of slides.THIS IS WHERE THE MAIN
    // ALGORITHM GOES.
    fun optimizePhotos(val )
  }
}
