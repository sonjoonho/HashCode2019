package heroes

import java.io.File

class OutputGenerator(val slideshow: Slideshow, val fileName: String) {

    val targetFilename = fileName.removeSuffix(".txt") + "_out.txt"
    val outputFile = File(targetFileName)

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
