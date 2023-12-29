import java.awt.Color

class CustomRunnable(game:Game,column:CircleColumn,x:Int,y:Int,color:Color,index:Int) extends Runnable {
  override def run(): Unit = {
    column.onClicked(x,y,color,index)
    val has_won:Boolean = game.checkWin()
    game.exit_won(has_won)
  }
}
