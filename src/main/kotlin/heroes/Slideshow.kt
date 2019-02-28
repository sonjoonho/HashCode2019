package heroes

class Slideshow(val slides: List<Slide>) {

  companion object {
    fun optimizePhotos(photos : List<Photo>) : List<Slide> {
      // TODO: CALL THIS FROMMAIN
      return emptyList()
    }

    // Algorithm re-orders an arbitrary list of slides.
    fun optimizeSegment(slides : List<Slide>) : List<Slide> {
      assert(slides.isNotEmpty())
      val permutations = getAllPermutationsOfSegment(slides)

      var max = 0
      var maxSlides = permutations.get(0)
      for (i in 0 until permutations.size) {
        val score = Slideshow.score(permutations[i])
        if (score > max) {
          max = score
          maxSlides = permutations.get(i)
        }
      }

      return maxSlides
    }

    /*

    Need to rethink this.

    // 1. Choose M vertical photos which we know we will combine into slides (we do this
    // naive algorithm in a helper function since we can always swap this algorithm out
    // into an optimised one)
    fun mergeNVerticalPhotosIntoSlides(photos : List<Photo>, m: Int) : List<List<Slide>> {
      assert(m % 2 == 0)

      // Copy a list of photos so we don't actually modify them as we delete items
      val photos = photos.toMutableList()

      // Go through the list in order, choosing the first (at most, if there are not M)
      // M vertical photos to appear
      val verticalCandidates = mutableListOf<Photo>()
      for (i in 0 until photos.size) {
        if (verticalCandidates.size == m) {
          break
        }

        if (photos.get(i).orientation == Orientation.HORIZONTAL) {
          verticalCandidates.add(photos[i])
        }
      }

      // Delete all candidates from the list of photos since they'll be merged
      for (candidate: Photo in verticalCandidates) {
        photos.remove(candidate)
      }

      // Generate permutations of photos and merge them
      // TODO: For every list in lists, pair every two vertical photos as a slide,
      // maybe using map

      return arrayListOf(arrayListOf())
    }

    */

    /* fun getAllPermutationsOfPhotos(photos : List<Photo>) : List<List<Photo>> {
      // copied and pasted from https://www.programcreek.com/2013/02/leetcode-permutations-java/
      var result = ArrayList<ArrayList<Photo>>()
      result.add(ArrayList<Photo>())

      for (i in 0 until photos.size) {
        val current = ArrayList<ArrayList<Photo>>()
        for (l in result) {
          for (j in 0 until l.size + 1) {
            l.add(j, photos[i])
            val temp = ArrayList<Photo>(l)
            current.add(temp)
            l.removeAt(j)
          }
        }
        // @Kelvin
        result = ArrayList<ArrayList<Photo>>(current)
        result = listOf(listOf(current))
      }

      return result
    }
    */

    // Makes no sense
    fun getAllPermutationsOfSegment(slides : List<Slide>) : List<List<Slide>> {
      // copied and pasted from https://www.programcreek.com/2013/02/leetcode-permutations-java/
      var result = mutableListOf(mutableListOf<Slide>())
      result.add(ArrayList<Slide>())

      for (i in 0 until slides.size) {
        val current = mutableListOf(mutableListOf<Slide>())
        for (l in result) {
          for (j in 0 until l.size + 1) {
            l.add(j, slides[i])
            val temp = ArrayList<Slide>(l)
            current.add(temp)
            l.removeAt(j)
          }
        }
        /* result = mutableListOf(mutableListOf<Slide>(current)) */
      }

      return result
    }

    // Calculate total interest of a slideshow
    fun score(slides: List<Slide>): Int {
      return slides.zipWithNext().fold(0) {
        i, (x, y) -> minOf(Tags.countTagsInCommon(x, y), Tags.countTagsInP1ButNotInP2(x, y), Tags.countTagsInP2ButNotInP1(x, y))
      }
    }
  }
}
