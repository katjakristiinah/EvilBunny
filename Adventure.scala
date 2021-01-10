package evilBunnyTextGame

class Adventure {

  val title = "Evil Bunny"

  private val yard = new Place("Yard", "You are in the yard of the Bunny House.")
  private val forest = new Place("Forest", "Yikes. You stepped into the forest. It seems really scary here.")
  private val hill = new Place("Hill", "You climbed up a steep hill. You can see the whole Bunny World from up here.")
  private val littleCottage = new Place("Little Cottage", "Knock knock. You knocked on the door of the Cottage, but no one answered. \nYou opened the door yourself. This little cottage looks cozy. ")
  private val entranceHall = new Place("Entrance Hall", "You are in the Entrance Hall of the Bunny House.")
  private val livingRoom = new Place("Living Room", "You stepped into the living room.")
  private val mysteriousRoom = new Place("Mysterious Room", "It's pitch black here. ")
  private val library = new Place("the Bunny Library", "The library is silent. There are lots of books on shelfs.")

  yard.setNeighbors(Vector("north" -> entranceHall, "east" -> littleCottage, "south" -> forest, "west" -> hill))
  forest.setNeighbors(Vector("north" -> yard, "west" -> hill ))
  hill.setNeighbors(Vector("east" -> yard, "south" -> forest ))
  littleCottage.setNeighbors(Vector( "west" -> yard ))
  entranceHall.setNeighbors(Vector("north" -> livingRoom, "east" -> mysteriousRoom, "south" -> yard, "west" -> library ))
  livingRoom.setNeighbors(Vector("south" -> entranceHall ))
  mysteriousRoom.setNeighbors(Vector("west" -> entranceHall ))
  library.setNeighbors(Vector("east" -> entranceHall ))

  livingRoom.addCarrot(new Carrot("carrot1", false))
  mysteriousRoom.addCarrot(new Carrot("carrot2", false))
  littleCottage.addCarrot(new Carrot("carrot3", false))
  livingRoom.addCarrot(new Carrot("carrot4", false))
  hill.addCarrot(new Carrot("carrot5", false))
  mysteriousRoom.addCarrot(new Carrot("carrot6", true))

  entranceHall.addPerson(new Character("FriendlyBunny", 60))
  forest.addPerson(new Character("EvilBunny", 150))
  library.addPerson(new Character("WiseBunny", 40))


  val player = new Player(yard)

  var turnCount = 0

  val timeLimit = 50


  def isWon: Boolean = forest.characters("EvilBunny").isDefeated

  def isLost: Boolean = this.player.isDefeated

  def isOver: Boolean = this.isWon || this.player.hasQuit || this.turnCount == this.timeLimit || this.isLost

  def welcomeMessage = "You are now in the Bunny World and other bunnies are relying on you to finally defeat the Evil Bunny.\nTalk to somebody to get more instructions! And hurry!\n" +
    "If you need help during the game, use command help."

  def goodbyeMessage = {
    if (isWon)
      "Well done!"
    else if (turnCount == timeLimit)
      "Oh no! Time's up. You didn't get to the forest quick enough to defeat Evil Bunny! \nGame over!"
    else if (isLost)
      "You lost the game!"
    else
      "Quitter! Come back and save Bunny World!"
  }


  def playTurn(command: String) = {
    val action = new Command(command)
    val outcomeReport = action.act(player)
    if (outcomeReport.isDefined) {
      this.turnCount += 1
    }
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }


}
