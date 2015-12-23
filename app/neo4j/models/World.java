package neo4j.models;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.RelationshipType;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Set;

/**
 * Taken (and modified) from https://github.com/tuxBurner/play-neo4jplugin/blob/master/examples/play-2.4-neo4j-template/app/neo4j/models/World.java
 *
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
@NodeEntity
@TypeAlias("World")
public class World {

  @GraphId
  public Long id;

  @Indexed
  public String name;

  @Indexed
  public int moons;

  @Fetch
  @RelatedTo(type = "REACHABLE_BY_ROCKET", direction = Direction.OUTGOING)
  public Set<World> reachableByRocket;

  public World(String name, int moons) {
    this.name = name;
    this.moons = moons;
  }

  public World() {
  }

  public void addRocketRouteTo(World otherWorld) {
    reachableByRocket.add(otherWorld);
  }

  @Override
  public String toString() {
    return String.format("World{name='%s', moons=%d}", name, moons);
  }

  public enum RelTypes implements RelationshipType {
    REACHABLE_BY_ROCKET
  }

}