package evilBunnyTextGame

import scala.io.StdIn.readLine

object EvilBunny extends App {

  private val game = new Adventure
  private val player = game.player
  this.run()

  private def run() = {
    println(game.welcomeMessage)
    while (!game.isOver) {
      placeDescription()
      playTurn()
    }
    println("\n" + this.game.goodbyeMessage)
  }

  private def placeDescription() = {
    val place = player.location
    println("\n\n" + place.name.toUpperCase)
    println(place.fullDescription + "\n")
  }

  private def playTurn() = {
    println()
    val command = readLine("What do you want to do next?: ")
    val report = game.playTurn(command)
    if (!report.isEmpty) {
      println(report)
    }
  }


}
