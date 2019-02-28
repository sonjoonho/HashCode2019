package heroes

import java.io.File

class OutputGenerator(val slideshow: Slideshow, filename: String) {

    val targetFilename = filename.removeSuffix(".txt") + "_out.txt"
    val outputFile = File(targetFilename)

    fun generateOutput() {
        outputFile.writeText(slideshow.slides.size.toString(10) + "\n")
        slideshow.slides.forEach { outputFile.writeText(generateLineForSlide(it)) }
    }

    private fun generateLineForSlide(slide: Slide) : String {
        return when(slide) {
            is SingleSlide -> slide.photo.id.toString(10) + "\n"
            is DoubleSlide -> slide.photo1.id.toString(10) + " " + slide.photo2.id.toString(10) + "\n"
            else -> "\n"
        }
    }
}
