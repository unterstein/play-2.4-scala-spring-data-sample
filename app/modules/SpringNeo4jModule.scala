package modules

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.guice.module.SpringModule

/**
 * The spring module, which will be passed to spring-guice
 *
 * @author Johannes Unterstein (unterstein@me.com)
 */
class SpringNeo4jModule extends SpringModule(new AnnotationConfigApplicationContext(classOf[SpringNeo4jConfiguration]))
