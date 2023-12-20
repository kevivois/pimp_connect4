import java.awt.Color
import  hevs.graphics.FunGraphics
class Circle(graphics:FunGraphics,centerX:Int,centerY:Int,radius:Int,private var colored:Boolean,var color:Color=Color.red) {

  def getX:Int = centerX
  def getY:Int = centerY

  def getRadius:Int = radius

  def onClicked():Unit = {
    print("colored",colored)

      fill()

  }
  def draw():Unit = {
    drawBorders()
    if(colored) {
      graphics.setColor(color)
      graphics.drawFilledCircle(centerX-radius, centerY-radius, radius)
    }
  }
  private def drawBorders():Unit = {
    graphics.setColor(new Color(0,0,0))
    graphics.drawCircle(centerX-radius,centerY-radius,radius)
  }
  def fill(clr:Color=color): Unit = {
    colored = true
    print("colored 2",colored)
    color = clr
    draw()
  }
  def empty():Unit = {
    color = new Color(255,255,255)
    colored = false
    draw()
  }
  def is_colored():Boolean = colored
}
