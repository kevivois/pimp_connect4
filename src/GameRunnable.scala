class GameRunnable extends Runnable {
  override def run(): Unit = {
    var game:Game = new Game();
    game.draw();
  }
}
