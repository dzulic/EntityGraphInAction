package quin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication(
    scanBasePackageClasses = [
        Application::class
    ],
    scanBasePackages = [
        "quin"
    ]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
