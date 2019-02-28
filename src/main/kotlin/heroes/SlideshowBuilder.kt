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
