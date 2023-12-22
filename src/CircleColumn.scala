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
  def wait(time:Int):Unit = {
    var timestampStart = new Date()

    while((new Date()).getTime - timestampStart.getTime < time){

    }
  }
  def onClicked(pX:Int,pY:Int,color:Color,clrIndex:Int):Boolean = {
    for(i <- circles.indices){
      if(!circles(i).is_colored()){
        circles(i).fill(color)
        if(i >= 1 && circles(i-1).is_colored()){
          circles(i-1).empty()
        }
      }else{
        return false
      }
      TimeUnit.MILLISECONDS.sleep(60)
    }
    filledCirles+=1
    true
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
