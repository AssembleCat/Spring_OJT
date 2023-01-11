package application.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    fun hello(): String {
        return "Hello World"
    }

    @PostMapping("/hello-name")
    @ResponseBody
    fun helloName(@RequestBody body: Map<String, Any>): String {
        return "Hello, ${body.get("name")}"
    }
}

class HelloNameRequest(
    val name: String
)