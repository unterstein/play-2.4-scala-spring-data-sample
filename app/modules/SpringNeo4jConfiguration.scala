package modules

import com.typesafe.config.ConfigFactory
import org.neo4j.graphdb.GraphDatabaseService
import org.springframework.beans.factory.DisposableBean
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.data.neo4j.config.{Neo4jConfiguration, EnableNeo4jRepositories}
import org.springframework.data.neo4j.rest.SpringCypherRestGraphDatabase
import play.api.Logger

/**
 * @author Johannes Unterstein (unterstein@me.com)
 */
@Configuration
@EnableNeo4jRepositories(basePackages = Array("neo4j.repositories"))
@ComponentScan(Array("neo4j.models", "neo4j.repositories"))
class SpringNeo4jConfiguration extends Neo4jConfiguration with DisposableBean {

  private val host = ConfigFactory.load().getString("neo4j.host")
  private val user = ConfigFactory.load().getString("neo4j.user")
  private val password = ConfigFactory.load().getString("neo4j.password")

  if (Logger.isDebugEnabled == true) {
    Logger.debug("Connecting to remote database: " + user + "@" + host)
  }

  private val database = new SpringCypherRestGraphDatabase(host, user, password)

  setBasePackage("neo4j")

  @Bean
  def graphDatabaseService(): GraphDatabaseService = database

  @throws(classOf[Exception])
  override def destroy = database.shutdown()

}
