import hevs.graphics.FunGraphics

import java.awt.Color

class CircleColumn(graphics:FunGraphics,length:Int,x:Int,y:Int,radius:Int) {
  private var circles:Array[Circle] = Array.ofDim[Circle](length)
  def getX:Int = x
  def getY:Int = y
  def getRadius:Int = radius

  def getLength:Int = length
  init()
  def onClicked(pX:Int,pY:Int,color:Color,clrIndex:Int):Boolean = {
    for(i <- circles.length-1 to 0 by -1){
     if(!circles(i).is_colored()){
       circles(i).setColorIndex(clrIndex)
       circles(i).fill(color)
       return true
     }
    }
    false
  }
  def changeAllColors(idx:Int,clr:Color): Unit = {
    for(i <- circles.indices){
      if(idx == circles(i).getColorIndex){circles(i).fill(clr)}
    }
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
