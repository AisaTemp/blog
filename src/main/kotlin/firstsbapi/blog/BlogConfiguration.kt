package firstsbapi.blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository, articleRepository: ArticleRepository) = ApplicationRunner{
        val moi = userRepository.save(User("moi", "Steven", "Random", "weebAF"))
        articleRepository.save(Article(
                "Pretty lolis",
                "Qualitat lolis",
                "Japan sei lolis",
                moi,
                "Faites moi confiance +mabite".toSlug()
        ))
        articleRepository.save(Article(
                "Random title",
                "Lorem ipsum",
                "dolor sit amet",
                author = moi
        ))
    }
}