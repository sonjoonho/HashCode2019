package heroes

class SlideshowBuilder(val horizontalPhotos: List<Photo>, val verticalPhotos: List<Photo>) {

  fun build(): Slideshow {
    val slides = SlideshowBuilder.optimizePhotos(verticalPhotos, horizontalPhotos)
    return Slideshow(slides)
  }


  companion object {
    fun optimizePhotos(verticalPhotos: List<Photo>, horizontalPhotos: List<Photo>): List<Slide> {
      // Map photos into slides 1:1
      val inputSlides = mutableListOf<Slide>()
      inputSlides.addAll(horizontalPhotos.map { SingleSlide(it) })
      inputSlides.addAll(verticalPhotosToSlides(verticalPhotos))
      inputSlides.shuffle()

      // Segment the results
      val maxPerSegment = 5

      val inputSegments: List<List<Slide>> = inputSlides.chunked(maxPerSegment)
      // TODO Check if last segment is empty

      // of NON-EMPTY segments with every segment of length n (possibly except for the last segment)
      val outputSegments = mutableListOf<List<Slide>>()
      for (segment: List<Slide> in inputSegments) {
        outputSegments.add(optimizeSegment(segment))
      }

      return outputSegments.flatten()
    }

    var count = 1

    // Algorithm re-orders an arbitrary list of slides.
    fun optimizeSegment(slides: List<Slide>): List<Slide> {
      println("Optimising segment ${count * 7}")
      count++
      assert(slides.isNotEmpty())
      val permutations = getAllPermutationsOfSegment(slides).distinct()

      var max = 0
      var maxSlides = permutations.get(0)
      for (i in 0 until permutations.size) {
        val score = SlideshowBuilder.score(permutations[i])
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

    // Calculate total interest of a slideshow
    fun score(slides: List<Slide>): Int {
      return slides.zipWithNext().fold(0) {
        i, (x, y) -> minOf(Tags.countTagsInCommon(x, y), Tags.countTagsInP1ButNotInP2(x, y), Tags.countTagsInP2ButNotInP1(x, y))
      }
    }

    // Makes no sense
    fun getAllPermutationsOfSegment(list: List<Slide>): List<List<Slide>> {
      // copied and pasted from https://www.programcreek.com/2013/02/leetcode-permutations-java/
      if(list.size==1) return listOf(list)
      val perms=mutableListOf<List <Slide>>()
      val sub=list[0]
      for(perm in getAllPermutationsOfSegment(list.drop(1)))
        for (i in 0..perm.size){
          val newPerm=perm.toMutableList()
          newPerm.add(i,sub)
          perms.add(newPerm)
        }
      return perms
    }
  }

}
