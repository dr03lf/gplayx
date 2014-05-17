package controllers

import eu.chlan.gplayx.services.{FileStorageService, GpxTrackService}
import play.api.mvc.{Action, Controller}
import java.io.File
import scalax.io.support.FileUtils
import scala.collection.JavaConversions._
import play.api.libs.json.Json
import scala.xml.XML

class TracksController(gpxTrackService: GpxTrackService, fileStorageService: FileStorageService) extends Controller {



  def uploadTracks(name: String) = Action(parse.raw) { implicit request =>
    val file = request.body.asFile
    val savedFilePath = fileStorageService.saveGpxTrackFile(file)
    
    gpxTrackService.saveTracks(savedFilePath.toFile)

    Ok(s"upload ok $name ${savedFilePath.toString}\n\n")
  }

  def getAllTracks() = Action {
    Ok(Json.toJson(fileStorageService.getAllSavedTracks.map(_.getName)))
  }
}
