package heroes

import java.io.File

// Current algorithm:
// 1. Choose M vertical photos which we know we will combine into slides (we do this
//    naive algorithm in a helper function since we can always swap this algorithm out
//    into an optimised one)
//    Number of tags must be at least 1
// 2. Generate a permutation of combining these photos into slides
// 3. With this list of slides, we split the list up into N small segments, then
//    we permute each segment until we find the maximum interest factor for each
//    segment. Then, we join the segments together.
// 4. Return to step 2 until all permutations of M photos are done
//
//
// Test plan: at first, we doM = 1 or M = 2, and choose N such that our
// algorithm does not time out.

// Input filename into args[0]
fun main(args: Array<String>) {
  val file = File(args[0])
  val parser: PhotoParser = PhotoParser(file.readText())
  var photos = parser.parse()

  // Order photos according to number of tags
  // Filter out photos with 0 tags
  photos = photos
    .filter { it.tags.isNotEmpty() }
    .sortedBy { it.tags.size }  // Split photos into horizontal and vertical
  val (horizontal, vertical) = photos.partition { it.orientation == Orientation.HORIZONTAL}

  // Convert Horizontal photos to slides
horizontalSlides = horizontal.map {  }

  // Convert Vertical photos to slides

  // Test output

  val fstHalfVertvertical = vertical.subList(0, vertical.size / 2)
  val sndHalfvertical = vertical.subList(vertical.size / 2, vertical.size)
  val vertPairSlides = fstHalfVertvertical.zip(sndHalfvertical) {p1, p2 -> DoubleSlide(p1, p2))}

  val slides = mutableListOf<Slide>()
  horizontal.forEach { slides.add(SingleSlide(it)) }
  slides.addAll(vertPairSlides)

  val slideshow = Slideshow(slides)

  val
}

class Slideshow(val slides: List<Slide>) {

  companion object {
    // Algorithm re-orders an arbitrary list of slides.THIS IS WHERE THE MAIN
    // ALGORITHM GOES.
    fun optimizeSegment(slides : List<Slide>) : List<Slide> {
      assert slides.size() > 0
      val permutations = getAllPermutationsOfSegment(slides)
      val max = 0
      val maxSlides  = slides.get(0)
      for (i in 0 until slides.length) {
        val score = Slideshow.score(slides.get(i))
        if score > max {
          max = score
          maxSlides = slides.get(i)
        }
      }

      return maxSlides
    }

    // 1. Choose M vertical photos which we know we will combine into slides (we do this
    // naive algorithm in a helper function since we can always swap this algorithm out
    // into an optimised one)
    fun mergeNVerticalPhotosIntoSlides(photos : List<Photo>, m: Int) : List<List<Slide>> {      // TODO
      // Copy a list of photos so we don't actually modify them as we delete items
      photos = photos.toMutableList()

      // Go through the list in order, choosing the first (at most, if there are not M)
      // M vertical photos to appear
      List<Candidates>

      return arrayListOf(arrayListOf())
    }

    fun getAllPermutationsOfSegment(slides : List<Slide>) : List<List<Slide>> {
      // copied and pasted from https://www.programcreek.com/2013/02/leetcode-permutations-java/
      val result = ArrayList<ArrayList<Slide>>()
      result.add(ArrayList<Slide>())

      for (i in 0 until slides.length) {
        val current = ArrayList<ArrayList<Slide>>()
        for (l in result) {
          for (j in 0 until l.size() + 1) {
            l.add(j, slides[i])
            val temp = ArrayList<Slide>(l)
            current.add(temp)
            l.remove(j)
          }
        }
        result = ArrayList<ArrayList<Slide>>(current)
      }

      return result
    }

    // Calculate total interest of a slideshow
    fun score(slides: List<slides>): Int {
      return slides.zipWithNext().fold(0) {
        i, (x, y) -> minOf(Tags.countTagsInCommon(x, y), Tags.countTagsInP1ButNotInP2(x, y), Tags.countTagsInP2ButNotInP1(x, y))
      }
    }
  }
}
