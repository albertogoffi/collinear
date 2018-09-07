package alberto.exercise

import com.marcinmoskala.math.combinations

data class Point(val x: Double, val y: Double) {
    override fun toString(): String {
        return "{\"x\": $x, \"y\": $y}"
    }
}

data class Line(val points: List<Point>) {
    override fun toString(): String {
        return points.joinToString(",", "[", "]")
    }
}

typealias Space = List<Point>

/**
 * Computes all the line segments passing through *at least* [minNumberOfPoints].
 *
 * @param space set of points in the space.
 * @param minNumberOfPoints minimal number of points that a line has to be composed of.
 * @return empty list if [minNumberOfPoints] is 1 or less. The list of line segments otherwise.
 */
fun computeLines(space: Space, minNumberOfPoints: Int): List<Line> {
    val lines = mutableListOf<Line>()
    if (minNumberOfPoints <= 1) return lines

    for (numberOfPoints in minNumberOfPoints..space.size) {
        val pointCombinations = space.toSet().combinations(numberOfPoints)
        for (pointCombination in pointCombinations) {
            val points = pointCombination.toList()
            if (areCollinear(points)) {
                lines += Line(points)
            }
        }
    }
    return lines
}


/**
 * Checks whether all the points in the given [points] list are collinear.
 *
 * @return `true` if the given [points] are all collinear, `false` otherwise.
 */
fun areCollinear(points: List<Point>): Boolean {
    if (points.size <= 2) return true

    val (a, b, c) = points.slice(0..2)
    return if (triangleArea(a, b, c) == .0) {
        areCollinear(points.drop(1))
    } else {
        false
    }
}

/**
 * Computes the area of the triangle defined by the given three points.
 */
fun triangleArea(a: Point, b: Point, c: Point) = a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)
