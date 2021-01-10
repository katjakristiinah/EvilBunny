package evilBunnyTextGame

import scala.collection.mutable.Map

class Place(var name: String, var description: String) {

  override def toString = this.name

  private val neighbors = Map[String, Place]()
  var characters = Map[String, Character]()
  var carrots = Map[String, Carrot]()

  def neighbor(direction: String) = this.neighbors.get(direction)

  def setNeighbor(direction: String, neighbor: Place) = {
    this.neighbors += direction -> neighbor
  }

  def setNeighbors(exits: Vector[(String, Place)]) = {
    this.neighbors ++= exits
  }


  def fullDescription = {
    val exitList = "\n\nYou can go to: " + this.neighbors.keys.mkString(" ")
    val personList = "\nYou see somebody: " + this.characters.keys.mkString(" ")
    val carrotsHere = "\nYou see carrots: " + this.carrots.keys.mkString(" ")
    if (characters.nonEmpty && carrots.nonEmpty)
      this.description + personList + carrotsHere + exitList
    else if (characters.nonEmpty && carrots.isEmpty)
      this.description + personList + exitList
    else if (characters.isEmpty && carrots.nonEmpty)
      this.description + carrotsHere + exitList
    else
      this.description +  exitList
  }



  def addCarrot(item: Carrot): Unit = {
    this.carrots += item.name -> item
  }

  def removeCarrot(carrotName: String): Option[Carrot] = {
    this.carrots.remove(carrotName)
  }

  def addPerson(person: Character): Unit = {
    this.characters += person.name -> person
  }


}

