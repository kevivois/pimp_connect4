import java.awt.event.{MouseEvent, MouseListener}
import java.util.Date

class CustomMouseListener(game:Game) extends MouseListener{
  var click:Date = null;
  var delta:Long = 10000
  override def mouseClicked(e: MouseEvent): Unit = {
  }
  override def mousePressed(e: MouseEvent): Unit = {
    if(click == null){
      click = new Date();
    }else{
      delta = (new Date()).getTime - click.getTime;
      click = new Date();
    }
    if(delta <= 200){
      game.onClicked(e.getX, e.getY)
    }else{
      game.onClicked(e.getX, e.getY)
    }
  }

  override def mouseReleased(e: MouseEvent): Unit = {}

  override def mouseEntered(e: MouseEvent): Unit = {}

  override def mouseExited(e: MouseEvent): Unit = {}
}
