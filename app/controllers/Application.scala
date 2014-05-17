package controllers

import play.api._
import play.api.mvc._
import eu.chlan.gplayx.services.{FileStorageService, GpxTrackService}
import scala.collection.JavaConversions._

class Application(gpxTrackService: GpxTrackService) extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.index("message", gpxTrackService.getAllTracks().map(_.getName).toList))
  }

}