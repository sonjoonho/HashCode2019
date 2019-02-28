package heroes

class GreedySlideshowBuilder(private val horizontalPhotos: List<Photo>, private val verticalPhotos: List<Photo>) {

    fun build(): Slideshow {
        val slides = mutableListOf<Slide>()

        // Dummy Value for first slide
        slides.add(SingleSlide(Photo(-1, Orientation.HORIZONTAL, emptySet())))

        val horizontalSlides = horizontalPhotos
                                 .map { SingleSlide(it) }
                                 .sortedBy { it.tags.size }

        val verticalPhotosCopy = verticalPhotos
                                   .toMutableList()
                                   .sortedBy { it.tags.size }

        while (horizontalSlides.isNotEmpty() || verticalPhotosCopy.size > 1) {

            val verticalSlides = allPossibleVerticalSlides(verticalPhotosCopy).sortedBy { it.tags.size }


        }

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