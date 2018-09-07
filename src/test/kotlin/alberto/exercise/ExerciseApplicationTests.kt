package alberto.exercise

import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ExerciseApplicationTests {

    @Autowired
    private lateinit var mvc: MockMvc

    @After
    fun clearSpace() {
        mvc.perform(delete("/space")).andExpect(status().isOk)
    }

    @Test
    fun addPointToSpace() {
        addPointToSpace(Point(.0, .0)).andExpect(status().isOk)
    }

    @Test
    fun retrieveSpace() {
        addPointToSpace(Point(-1.0, -1.0))
        addPointToSpace(Point(.0, .0))
        mvc.perform(get("/space"))
                .andExpect(status().isOk)
                .andExpect(content().json("[{\"x\":-1.0,\"y\":-1.0}, {\"x\":0.0,\"y\":0.0}]"))
    }

    @Test
    fun deleteSpace() {
        mvc.perform(delete("/space")).andExpect(status().isOk)
        mvc.perform(get("/space"))
                .andExpect(status().isOk)
                .andExpect(content().json("[]"))
    }

    @Test
    fun noLinesInEmptySpace() {
        mvc.perform(get("/lines/2"))
                .andExpect(status().isOk)
                .andExpect(content().json("[]"))
    }

    @Test
    fun oneLineInSpaceWithThreeCollinearPoints() {
        addPointToSpace(Point(-1.0, -1.0))
        addPointToSpace(Point(.0, .0))
        addPointToSpace(Point(1.0, 1.0))
        mvc.perform(get("/lines/3"))
                .andExpect(status().isOk)
                .andExpect(content().json("[[{\"x\":-1.0,\"y\":-1.0}, {\"x\":0.0,\"y\":0.0}, {\"x\":1.0,\"y\":1.0}]]"))
    }

    private fun addPointToSpace(point: Point): ResultActions {
        val requestContent = "{ \"x\": ${point.x}, \"y\": ${point.y} }"
        val request = post("/point").contentType(MediaType.APPLICATION_JSON).content(requestContent)
        return mvc.perform(request)
    }
}
