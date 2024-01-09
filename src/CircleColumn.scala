import hevs.graphics.FunGraphics

import java.awt.Color
import java.util.Date
import java.util.concurrent.{ScheduledExecutorService, ThreadPoolExecutor, TimeUnit}

class CircleColumn(graphics:FunGraphics,length:Int,x:Int,y:Int,radius:Int) {
  private var circles:Array[Circle] = Array.ofDim[Circle](length)
  private var filledCirles:Int = 0
  def getX:Int = x
  def getY:Int = y
  def getRadius:Int = radius

  def getLength:Int = length
  def getCircles:Array[Circle] = circles
  init()
  def onClicked(pX:Int,pY:Int,color:Color,clrIndex:Int):Unit = {
    for(i <- circles.indices){
      if(!circles(i).is_colored()){
        circles(i).fill(color)
        circles(i).setColorIndex(clrIndex)
        if(i >= 1 && circles(i-1).is_colored()){
          circles(i-1).empty()
          circles(i-1).setColorIndex(-1)
        }
      }else{
        return
      }
      Thread.sleep(60)
    }
    filledCirles+=1
  }
  def changeAllColors(idx:Int,clr:Color): Unit = {
    for(i <- circles.indices){
      if(idx == circles(i).getColorIndex){circles(i).setColor(clr)}
    }
  }
  def is_full():Boolean = {
    var result:Boolean = true
    for(circle <- circles){
      if(!circle.is_colored()){
        result=false
      }
    }
    result
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
