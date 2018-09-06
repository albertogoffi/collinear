package alberto.exercise

import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test

class AlgorithmTests {

    @Test fun triangleAreaNonCollinearPoints() {
        val pointA = Point(1.0, 1.0)
        val pointB = Point(-1.0, -1.0)
        val pointC = Point(-.5, .0)
        assertThat(triangleArea(pointA, pointB, pointC), `is`(not(.0)))
    }

    @Test fun triangleAreaCollinearPoints() {
        val pointA = Point(1.0, 1.0)
        val pointB = Point(-1.0, -1.0)
        val pointC = Point(.0, .0)
        assertThat(triangleArea(pointA, pointB, pointC), `is`(.0))
    }

    @Test fun oneLine() {
        val a = Point(-1.0, -1.0)
        val b = Point(.0, .0)
        val c = Point(1.0, 1.0)
        val d = Point(2.0, 2.0)
        val e = Point(-1.0, 1.0)
        val lines = computeLines(listOf(a, b, c, d, e), 4)
        assertThat(lines.size, `is`(1))
    }

    @Test fun noLine() {
        val a = Point(-1.0, -1.0)
        val b = Point(.0, .0)
        val c = Point(.0, 1.0)
        val lines = computeLines(listOf(a, b, c), 3)
        assertTrue(lines.isEmpty())
    }

    @Test fun moreLines() {
        val a = Point(-1.0, -1.0)
        val b = Point(.0, .0)
        val c = Point(1.0, 1.0)
        val d = Point(2.0, 2.0)
        val e = Point(-1.0, 1.0)
        val lines = computeLines(listOf(a, b, c, d, e), 3)
        assertThat(lines.size, `is`(5))
    }
}