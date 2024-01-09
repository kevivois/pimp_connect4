import java.awt.Color
import hevs.graphics.FunGraphics


class Circle(graphics:FunGraphics,x:Int,y:Int,radius:Int,private var colored:Boolean,var color:Color=Color.red) {

  def getX:Int = x
  def getY:Int = y
  var colorIndex:Int = -1

  def getRadius:Int = radius
  def getCenterX:Int = getX+radius
  def getCenterY:Int = getY+radius

  def getColor():Color = color

  def setColorIndex(idx:Int):Unit = {
    colorIndex = idx
  }
  def getColorIndex:Int = {
    colorIndex
  }
  def setColor(clr:Color):Unit = {
    color = clr
    draw();
  }
  def draw():Unit = {
    drawBorders();
    if(colored) {
      graphics.setColor(color)
      graphics.drawFilledCircle(getX+1, getY+1, (radius*2)-1)
    }else
    {
      graphics.setColor(Color.white)
      graphics.drawFilledCircle(getX + 1, getY + 1, (radius * 2) - 1)
    }
  }
  private def drawBorders():Unit = {
    graphics.setColor(new Color(0,0,0))
    graphics.drawCircle(getX,getY,radius*2)
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
