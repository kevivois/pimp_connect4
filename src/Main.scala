import hevs.utils.Input
object Main extends App {
  /*
  *  Play with user interface
  *
   */
  println("Select gamemode , 0 for game with user interface, 1 for console game")
  var mode:Int = Input.readInt();

  if(mode == 0){
    val game:WithUi.Game = new WithUi.Game();
    game.draw()
  }else{
    val game:Console.Game = new Console.Game();
    game.play()
  }
}
