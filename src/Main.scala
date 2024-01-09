object Main {
  def main(args: Array[String]): Unit = {
    val g: Game = new Game(new Grid(7,6))
    g.play()
  }
}