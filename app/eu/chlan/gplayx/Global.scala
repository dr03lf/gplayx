package eu.chlan.gplayx

import play.api.GlobalSettings
import eu.chlan.gplayx.services.{FileStorageService, GpxTrackService}

object Global extends GlobalSettings {


  val fileStorageService = new FileStorageService
  val gpxTrackService = new GpxTrackService(fileStorageService)

  val controllerSingletons = Map[Class[_], AnyRef](
    (classOf[controllers.Application] -> new controllers.Application(gpxTrackService)),
    (classOf[controllers.TracksController] -> new controllers.TracksController(gpxTrackService, fileStorageService)),
    (classOf[controllers.Auth] -> new controllers.Auth)

  )

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
      controllerSingletons(controllerClass).asInstanceOf[A]
  }
}
