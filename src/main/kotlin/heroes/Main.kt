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
    .sortedBy { it.tags.size }

  // Split photos into horizontal and vertical
  val (horizontal, vertical) = photos.partition { it.orientation == Orientation.HORIZONTAL}

  val slideshow = SlideshowBuilder(horizontal, vertical).build()

  // Do output generation
  val outputGenerator = OutputGenerator(slideshow, args[0])
  outputGenerator.generateOutput()

  println("Finished.")
}
