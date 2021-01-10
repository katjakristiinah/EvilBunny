package evilBunnyTextGame

class Character(val name: String, val strength: Int) {

  override def toString = this.name

  var isDefeated: Boolean = false


}