enum class Orientation {VERTICAL, HORIZONTAL}

data class Photo(val id: Int, val orientation: Orientation, val tags: Set<String>)

object Tags {
  fun countTagsInCommon(p1 : Photo, p2 : Photo) : Int {
    return p1.tags.intersect(p2.tags).size
  }

  fun countTagsInCommon(p1 : Slide, p2 : Slide) : Int {
    return p1.tags.intersect(p2.tags).size
  }

  fun countTagsInP1ButNotInP2(p1 : Photo, p2 : Photo) : Int {
    return (p1.tags - p2.tags).size
  }

  fun countTagsInP1ButNotInP2(p1 : Slide, p2 : Slide) : Int {
    return (p1.tags - p2.tags).size
  }

  fun countTagsInP2ButNotInP1(p1 : Photo, p2 : Photo) : Int {
    return (p2.tags - p1.tags).size
  }

  fun countTagsInP2ButNotInP1(p1 : Slide, p2 : Slide) : Int {
    return (p2.tags - p1.tags).size
  }
}
