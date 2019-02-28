package heroes

enum class Orientation { VERTICAL, HORIZONTAL }

data class Photo(val orientation: Orientation, val tags: List<String>)
