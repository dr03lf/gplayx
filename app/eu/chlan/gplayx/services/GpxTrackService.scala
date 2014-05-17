package eu.chlan.gplayx.services

import java.io.File
import scala.xml.XML
import com.droelf.gpx.gpxtype.GPXDecoder

class GpxTrackService(fileStorageService: FileStorageService) {
  
  def saveTracks(file: File) {

    val x = GPXDecoder.decodeFromFile(file)

    x.tracks.foreach(track => {
      println("track -->" + track.trackSegments.map(_.trackPoints.map(_.latitude)))
    })
  }
  
  def getAllTracks() = {
    fileStorageService.getAllSavedTracks
  }
}
