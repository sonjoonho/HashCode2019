package heroes

import java.io.File
import heroes.PhotoParser

// Current algorithm:
// 1. Choose M vertical photos which we know we will combine into slides (we do this
// naive algorithm in a helper function since we can always swap this algorithm out
// into an optimised one)
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

  fun countTagsInP1ButNotInP2(p1 : Photo, p2 : Photo) : Int {
    return (p1.tags - p2.tags).size
  }

  fun countTagsInP2ButNotInP1(p1 : Photo, p2 : Photo) : Int {
    return (p2.tags - p2.tags).size
  }
}

class Slideshow(val slides: List<Slide>) {
  companion object {
    // This is the step we do before doing any permutation
    fun mergeNVerticalPhotosIntoSlides(photos : List<Photo>) : List<Slide> {
      // TODO
      return emptyList()
    }

    // Algorithm re-orders an arbitrary list of slides.THIS IS WHERE THE MAIN
    // ALGORITHM GOES.
    fun optimizePhotos(photos : List<Photo>) : List<Slide> {
      //
      return emptyList()
    }
  }
}
