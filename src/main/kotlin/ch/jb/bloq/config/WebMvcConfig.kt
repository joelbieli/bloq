package ch.jb.bloq.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.RedirectView

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    private val MAX_AGE_SECS: Long = 3600

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS)
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handle404(exception: Exception): RedirectView {
        return RedirectView("/")
    }
}