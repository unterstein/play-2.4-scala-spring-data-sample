package neo4j.repositories;

import neo4j.models.World;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Taken form https://github.com/tuxBurner/play-neo4jplugin/blob/master/examples/play-2.4-neo4j-template/app/neo4j/repositories/WorldRepository.java
 *
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
public interface WorldRepository extends GraphRepository<World> {

}
