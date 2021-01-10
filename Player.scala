package evilBunnyTextGame

class Player(startingLocation: Place) {

  private var currentLocation = startingLocation
  private var playerHasQuit = false
  private var strength = 0


  var isDefeated = false

  def hasQuit = this.playerHasQuit

  def location = this.currentLocation

  def checkstrength = {
    "Your current strength is: " + this.strength + ". You need at least 150 to defeat Evil Bunny. "
  }


  def go(direction: String) = {
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if (destination.isDefined) "You go " + direction + "." else "You can't go " + direction + "."
  }

  def sniff(itemName: String): String = {
    if (location.carrots.contains(itemName))
      if (location.carrots(itemName).isRotten)
        "That smells awful! It is definitely rotten and cannot be eaten."
      else
        "This carrot smells good!"
    else
      "There is no carrots to sniff here."

  }

  def eat(itemName: String): String = {
    if (location.carrots.contains(itemName)) {
      if (location.carrots(itemName).isRotten) {
        this.isDefeated = true
        "Argh! This rotten carrot poisoned you!"
      } else {
        this.strength += 50
        location.removeCarrot(itemName)
        "This carrot was tasty! You gained a bit more strength."
      }
    } else
      "There is no carrots to eat here."
  }



  def talk(personName: String): String = {
    if (personName == "friendlybunny" && this.currentLocation.characters.contains("FriendlyBunny"))
      "Friendly Bunny: Please help us to save Bunny World and defeat Evil Bunny! I think you should find Wise Bunny and ask him what to do. You can find him at the library."
    else if (personName == "wisebunny" && this.currentLocation.characters.contains("WiseBunny"))
      "Wise Bunny: Are you the Hero Bunny who saves us from Evil Bunny? Eat carrots to gain enough strength and then go find him and defeat him!"
    else if (personName == "evilbunny" && this.currentLocation.characters.contains("EvilBunny"))
      "Evil Bunny: Who do you think you are to challenge me?"
    else
      "There is no such person to talk to."
  }


  def defeat(personName: String): String = {
    if (personName == "evilbunny") {
      if (this.currentLocation.characters.contains("EvilBunny")) {
        if (this.strength >= currentLocation.characters("EvilBunny").strength) {
          this.currentLocation.characters("EvilBunny").isDefeated = true
          "Yay! You defeated Evil Bunny! Bunnies may now live in peace."
        } else {
          this.isDefeated = true
          "Ouch! Evil Bunny was stronger than you and therefore defeated you.."
        }
      } else
        "You cannot defeat Evil Bunny if you cannot even see him!"
    } else
      "That's rude! Why would you want to kill your friend?!"
  }


  def help: String = {
    "You must find Friendly Bunny to get further instructions. \n" +
    "Commands available during the game: \n " +
    "- go NORTH/EAST/SOUTH/WEST \n " +
    "- sniff CARROT \n" +
    "- eat CARROT \n" +
    "- talk CHARACTER \n" +
    "- defeat CHARACTER \n" +
    "- strength"
  }

  def quit() = {
    this.playerHasQuit = true
    ""
  }

  override def toString = "Your location: " + this.location.name


}

