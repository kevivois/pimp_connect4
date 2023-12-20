import hevs.graphics.FunGraphics

import java.awt.event.MouseListener

class CircleColumn(graphics:FunGraphics,length:Int,x:Int,y:Int,radius:Int) {
  private var circles:Array[Circle] = Array.ofDim[Circle](length)
  def getX:Int = x
  def getY:Int = y
  def getRadius:Int = radius
  init()
  def onClicked(pX:Int,pY:Int):Boolean = {
    for(i <- circles.indices){
      var centerX = circles(i).getCenterX
      var centerY = circles(i).getCenterY
      if(Math.sqrt(Math.pow(pX-centerX,2) + Math.pow(pY-centerY,2)) < circles(i).getRadius){
        circles(i).onClicked()
      }
    }
    false
  }
  def init():Unit = {
    for (i <- circles.indices) {
      val posY = y + (radius*2 * i)
      val posX = x
      circles(i) = new Circle(graphics,posX,posY,radius,false)
    }
  }
  def drawLine():Unit = {
    for(i <- circles.indices){
      circles(i).draw()
    }
  }

}
