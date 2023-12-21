import hevs.graphics.FunGraphics

import java.awt.Color
import javax.swing.JColorChooser

class Game {
 var graphics = new FunGraphics(500,500,200,200,"test",true)
  val RADIUS = 20
  val LENGTH_X = 7
  val LENGTH_Y = 6
  var data:Array[CircleColumn] = Array.ofDim[CircleColumn](LENGTH_X)
  var color1:Color = Color.red
  var color2:Color = Color.yellow
  var index:Int = 0
  var mouseListener = new CustomMouseListener(this)
  init()

  def setColors():Unit = {
    var clr1:Color = JColorChooser.showDialog(null,"test",Color.black)
    var clr2:Color = JColorChooser.showDialog(null,"test",Color.black)
    color1 = clr1
    color2=clr2
    for(i <- data.indices){
      data(i).changeAllColors(0,clr1)
      data(i).changeAllColors(1,clr2)
    }
  }
  setColors()
  def onClicked(x:Int,y:Int):Boolean = {
    for (i <- data.indices) {
      if(x >= data(i).getX && x <= data(i).getX + (data(i).getRadius*2) && y >= data(i).getY && y <= (data(i).getY + (data(i).getLength*2*data(i).getRadius))){
        if(index == 0){
          index=1
        }else{
          index=0
        }
        val colored = data(i).onClicked(x,y,if (index %2 == 0) color1 else color2,index)
        return colored
      }
    }
    false
  }
  def changeAllColors(idx:Int,clr:Color):Unit = {
    for(i <- data.indices){
      data(i).changeAllColors(idx,clr)
    }
  }

  private def init():Unit = {
    graphics.addMouseListener(mouseListener)
    for (i <- data.indices) {
      data(i) = new CircleColumn(graphics,LENGTH_Y,i*(2*(RADIUS)+20),100,RADIUS)
    }
  }
  def update():Unit = {
    graphics.clear()
    draw()
  }
  def draw():Unit = {
    for(i <- data.indices){
      data(i).drawLine()
    }
  }
}
