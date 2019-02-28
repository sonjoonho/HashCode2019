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
  val photos = parser.parse()
  println(photos)

  photos.filter()
  // Test output
}

enum class Orientation {VERTICAL, HORIZONTAL}

data class Photo(val id: Int, val orientation: Orientation, val tags: Set<String>) {
  // Photo has a set of strings, which represents its tags
}

interface Slide {
  val tags: Set<String>

  companion object {
    fun getInterestFactor(s1 : Photo, s2 : Photo) : Int {
      // Gets the minimum of counttagsins1butnotins2, counttagsins2butnotins1,
      // counttagsincommon

      // TODO
      return 0
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

class Slideshow(val slides: List<Slide>) {
  fun score(): Int {
    return this.slides.zipWithNext().fold(0) {
      i, (x, y) -> minOf(Tags.countTagsInCommon(x, y), Tags.countTagsInP1ButNotInP2(x, y), Tags.countTagsInP2ButNotInP1(x, y))
    }
  }

  companion object {
    // 1. Choose M vertical photos which we know we will combine into slides (we do this
    // naive algorithm in a helper function since we can always swap this algorithm out
    // into an optimised one)
    fun mergeNVerticalPhotosIntoSlides(photos : List<Photo>, m: Int) : List<List<Slide>> {      // TODO
      return emptyList()
      val candidates : ArrayList<Int> = arrayListOf()

      return arrayListOf(arrayListOf())
    }

    // Algorithm re-orders an arbitrary list of slides.THIS IS WHERE THE MAIN
    // ALGORITHM GOES.
    fun optimizeSegment(slides : List<Slide>) : List<Slide> {
      val permutations = getAllPermutationsOfSegment(slides);
            //
      return emptyList()
    }

    fun getAllPermutationsOfSegment(slides : List<Slide>) : List<List<Slide>> {
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
  }
}
