package heroes

import java.io.File

class outputGenerator(val slideshow: Slideshow, val fileName: String) {

    val targetFileName = "$fileName.out"
    val outputFile = File(targetFileName)

    fun generateOutput() {
        outputFile.appendText(slideshow.slides.size.toString(10) + "\n")
        slideshow.slides.forEach { outputFile.appendText(generateLineForSlide(it)) }
    }

    private fun generateLineForSlide(slide: Slide) : String {
        return when(slide) {
            is SingleSlide -> slide.photo.id.toString(10) + "\n"
            is DoubleSlide -> slide.photo1.id.toString(10) + " " + slide.photo2.id.toString(10) + "\n"
            else -> "\n"
        }
    }
}