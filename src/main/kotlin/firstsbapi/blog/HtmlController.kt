package firstsbapi.blog

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException


@Controller
class HtmlController(private val repository: ArticleRepository,
                     private val userRepository: UserRepository,
                     private val properties: BlogProperties) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = properties.title
        model["banner"] = properties.banner
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map{ it }
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository
                .findBySlug(slug)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
        model["title"]= article.title
        model["article"] = article
        return "article"
    }

    @GetMapping("/{login}")
    fun user(@PathVariable login: String, model: Model): String {
        val user = userRepository
                .findByLogin(login)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")
        model["title"] = user.firstname + user.lastname
        model["user"] = user
        return "user"
    }
}