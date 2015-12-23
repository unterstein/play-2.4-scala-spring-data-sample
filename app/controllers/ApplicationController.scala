package controllers

import javax.inject.{Inject, Named}

import neo4j.InitialDataService
import neo4j.models.World
import neo4j.repositories.WorldRepository
import play.api.mvc._
import scala.collection.JavaConversions._

@Named
class ApplicationController @Inject()(worldRepository: WorldRepository, initialDataService: InitialDataService) extends Controller {

  def index = Action {
    if (worldRepository.count() == 0) {
      initialDataService.makeSomeWorldsAndRelations()
    }
    def allWorlds: java.util.List[World] = worldRepository.findAll().toList
    def pathFromFirstToLast: java.util.List[World] = initialDataService.getWorldPath(allWorlds.head, allWorlds.last)
    Ok(views.html.index(allWorlds, pathFromFirstToLast))
  }

}
