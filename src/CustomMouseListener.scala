import java.awt.event.{MouseEvent, MouseListener}

class CustomMouseListener(game:Game) extends MouseListener{
  override def mouseClicked(e: MouseEvent): Unit = {
    print(e.getX,e.getY)
    game.onClicked(e.getX,e.getY)
  }

  override def mousePressed(e: MouseEvent): Unit = {

  }

  override def mouseReleased(e: MouseEvent): Unit = {}

  override def mouseEntered(e: MouseEvent): Unit = {}

  override def mouseExited(e: MouseEvent): Unit = {}
}
