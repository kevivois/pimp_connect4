import java.awt.Color
import  hevs.graphics.FunGraphics
class Circle(graphics:FunGraphics,centerX:Int,centerY:Int,radius:Int,private var colored:Boolean,var color:Color=new Color(255,255,255)) {
  private def draw():Unit = {
    drawBorders()
    graphics.setColor(color)
    graphics.drawFilledCircle(centerX,centerY,radius)
  }
  private def drawBorders():Unit = {
    graphics.setColor(new Color(0,0,0))
    graphics.drawCircle(centerX,centerY,radius+10)
  }
  def fill(clr:Color=color): Unit = {
    colored = true
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
