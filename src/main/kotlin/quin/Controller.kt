package quin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class Controller(private val mainService: MainService) {

    @GetMapping(value = ["/organizations"])
    fun getOrganization() {
        mainService.getOrganizations()
    }

    @GetMapping(value = ["/locations"])
    fun mainTest() {
        mainService.getLocations()
    }
}
