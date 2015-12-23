package neo4j;

import neo4j.models.World;
import neo4j.repositories.WorldRepository;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.kernel.Traversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import play.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Taken (and modified) from https://github.com/tuxBurner/play-neo4jplugin/blob/master/examples/play-2.4-neo4j-template/app/neo4j/services/GalaxyService.java
 *
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
@Service
@Named
public class InitialDataService {

  @Inject
  private WorldRepository worldRepository;

  @Inject
  private Neo4jTemplate template;

  public List<World> makeSomeWorldsAndRelations() {

    Logger.debug("Creating test data set in database.");

    List<World> worlds = new ArrayList<>();

    // Solar worlds
    worlds.add(createWorld("Mercury", 0));
    worlds.add(createWorld("Venus", 0));
    worlds.add(createWorld("Earth", 1));
    worlds.add(createWorld("Mars", 2));
    worlds.add(createWorld("Jupiter", 63));
    worlds.add(createWorld("Saturn", 62));
    worlds.add(createWorld("Uranus", 27));
    worlds.add(createWorld("Neptune", 13));

    // Norse worlds
    worlds.add(createWorld("Alfheimr", 0));
    worlds.add(createWorld("Midgard", 1));
    worlds.add(createWorld("Muspellheim", 2));
    worlds.add(createWorld("Asgard", 63));
    worlds.add(createWorld("Hel", 62));


    // Add relations
    for (int i = 0; i < worlds.size() - 1; i++) {
      World world = worlds.get(i);
      world.addRocketRouteTo(worlds.get(i + 1));
      worldRepository.save(world);
    }

    Logger.debug("Creating test data done, have fun with it :).");

    return worlds;
  }

  public List<World> getWorldPath(final World worldA, final World worldB) {
    Path path = GraphAlgoFactory.shortestPath(Traversal.expanderForTypes(World.RelTypes.REACHABLE_BY_ROCKET, Direction.OUTGOING).add(World.RelTypes.REACHABLE_BY_ROCKET), 100)
        .findSinglePath(template.getNode(worldA.id), template.getNode(worldB.id));
    if (path == null) {
      return Collections.emptyList();
    }
    return convertNodesToWorlds(path);
  }

  private List<World> convertNodesToWorlds(final Path list) {
    final List<World> result = new LinkedList<>();
    for (Node node : list.nodes()) {
      result.add(template.load(node, World.class));
    }
    return result;
  }

  private World createWorld(String name, int moons) {
    return worldRepository.save(new World(name, moons));
  }
}
