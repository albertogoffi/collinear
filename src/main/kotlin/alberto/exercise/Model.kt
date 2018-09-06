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

fun areCollinear(points: List<Point>): Boolean {
    if (points.size <= 2) return true

    val (a, b, c) = points.slice(0..2)
    return if (triangleArea(a, b, c) == .0) {
        areCollinear(points.drop(1))
    } else {
        false
    }
}

fun triangleArea(a: Point, b: Point, c: Point) = a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)
