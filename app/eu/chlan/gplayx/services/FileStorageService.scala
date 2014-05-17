package eu.chlan.gplayx.services

import java.io.{FileOutputStream, File}
import scalax.io.support.FileUtils
import java.nio.file._
import java.io.File

class FileStorageService {

  val tracksDir = Paths.get("tracks")

  def getAllSavedTracks = {
    recursiveListFiles(tracksDir.toFile)
  }

  private def recursiveListFiles(f: File): Array[File] = {
    val these = f.listFiles
    these ++ these.filter(_.isDirectory).flatMap(recursiveListFiles)
  }


  def saveGpxTrackFile(file: File): Path = {
    Files.createDirectories(tracksDir)

    val savedFilePath = tracksDir.resolve(file.getName)

    Files.move(file.toPath, savedFilePath, StandardCopyOption.REPLACE_EXISTING)

    savedFilePath
  }

}
