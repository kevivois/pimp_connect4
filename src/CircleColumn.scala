import hevs.graphics.FunGraphics

import java.awt.event.MouseListener

class CircleColumn(graphics:FunGraphics,length:Int,x:Int,y:Int,radius:Int) {
  private var circles:Array[Circle] = Array.ofDim[Circle](length)
  def getX:Int = x
  def getY:Int = y
  def getRadius:Int = radius
  init()
  def onClicked(x:Int,y:Int):Boolean = {
    for(i <- circles.indices){
      if(Math.pow(x-circles(i).getX,2) + Math.pow(y-circles(i).getY,2) < Math.pow(circles(i).getRadius,2)){
        circles(i).onClicked()
        return true
      }
    }
    false
  }
  def init():Unit = {
    for (i <- circles.indices) {
      val posY = y + radius * i
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
