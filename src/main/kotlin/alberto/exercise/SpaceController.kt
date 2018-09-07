package alberto.exercise

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SpaceController {

    val space = mutableListOf<Point>()

    @PostMapping("/point")
    fun addPointToSpace(@RequestBody newPoint: Point) {
        if (newPoint !in space) {
            space.add(newPoint)
        }
    }

    @GetMapping("/space")
    fun space() = space

    @DeleteMapping("/space")
    fun emptySpace() = space.clear()

    @GetMapping("/lines/{n}")
    fun collinearPoints(@PathVariable(value = "n") minNumberOfPoints: Int): String {
        return computeLines(space, minNumberOfPoints).joinToString(",", "[", "]")
    }
}
