package evilBunnyTextGame

class Command(input: String) {

  private val wholeText = input.trim.toLowerCase
  private val verb = wholeText.takeWhile(_ != ' ')
  private val target = wholeText.drop(verb.length).trim

  def act(player: Player) = verb match {
    case "go"    => Some(player.go(target))
    case "quit"  => Some(player.quit())
    case "talk" => Some(player.talk(target))
    case "defeat" => Some(player.defeat(target))
    case "eat" => Some(player.eat(target))
    case "sniff" => Some(player.sniff(target))
    case "strength" => Some(player.checkstrength)
    case "help" => Some(player.help)
    case other   => None
  }


  override def toString = this.verb + " (target word: " + target + ")"


}

