package heroes

class GreedySlideshowBuilder(private val horizontalPhotos: List<Photo>, private val verticalPhotos: List<Photo>) {

    fun build(): Slideshow {
        val slides = mutableListOf<Slide>()

        // Dummy Value for first slide
        slides.add(SingleSlide(Photo(-1, Orientation.HORIZONTAL, emptySet())))

        val horizontalSlides = horizontalPhotos
                                 .map { SingleSlide(it) }
                                 .sortedBy { it.tags.size }
                                 .toMutableList()

        val verticalPhotosCopy = verticalPhotos
                                   .sortedBy { it.tags.size }
                                   .toMutableList()

        while (horizontalSlides.isNotEmpty() || verticalPhotosCopy.size > 1) {

            print("Looping -- ")

            val verticalSlides = verticalPhotosToSlides(verticalPhotosCopy)

            val bestMatch = findBestMatch(slides.last(), horizontalSlides + verticalSlides)

            slides.add(bestMatch)

            when (bestMatch) {
                is SingleSlide -> horizontalSlides.remove(bestMatch)
                is DoubleSlide -> {
                    verticalPhotosCopy.remove(bestMatch.photo1)
                    verticalPhotosCopy.remove(bestMatch.photo2)
                }
            }

            println("Remaining pictures : ${horizontalSlides.size + verticalPhotosCopy.size}")
        }

        slides.remove(slides.first())

        return Slideshow(slides)
    }
}

fun allPossibleVerticalSlides(verticalPhotos: List<Photo>): List<DoubleSlide> {

    val slides = mutableListOf<DoubleSlide>()

    val photos = verticalPhotos.toMutableList()

    while (photos.isNotEmpty()) {

        val photo = photos.removeAt(0)

        if (photos.isNotEmpty()) {
            photos.forEach { slides.add(DoubleSlide(photo, it)) }
        }

    }

    return slides
}

fun findBestMatch(slide: Slide, choices: List<Slide>): Slide {

    var highestInterestFactor = Integer.MIN_VALUE
    var chosenSlide = choices.first()

    for (choice in choices.take(50)) {
        val interestFactor = getInterestFactor(slide, choice)

        if (interestFactor > highestInterestFactor) {
            highestInterestFactor = interestFactor
            chosenSlide = choice
        }
    }

    return chosenSlide
}
