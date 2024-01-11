import hevs.graphics.FunGraphics

import java.awt.Color
import java.awt.geom.Point2D
import java.util.Date
import javax.swing.JColorChooser
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock


class Game {
 var graphics = new FunGraphics(500,500,200,200,"game",false)
  val RADIUS = 20
  val LENGTH_X = 7
  val LENGTH_Y = 6
  var data:Array[CircleColumn] = Array.ofDim[CircleColumn](LENGTH_X)
  var color1:Color = Color.red
  var color2:Color = Color.yellow
  var index:Int = 0
  var mouseListener = new CustomMouseListener(this)
  graphics.addMouseListener(mouseListener)
  var x:Int = 2*RADIUS+20
  var y:Int = 100

  init()

  def updateInfoGraphics():Unit = {
    graphics.setColor(Color.white)
    graphics.drawFillRect(50,0,100,100)
    graphics.drawString(30,45,"Next :",Color.black,20)
    graphics.setColor(if(index == 1) color1 else color2)
    graphics.drawFilledCircle(100,30,40)
  }
  def setColors():Unit = {
    val clr1:Color = JColorChooser.showDialog(null,"player 1 color ",color1)
    val clr2:Color = JColorChooser.showDialog(null,"player 2 color",color2)
    if (clr1 == clr2 || clr1 == Color.white || clr2 == Color.black) {
      return
    }
    color1 = clr1
    color2=clr2
    for(i <- data.indices){
      data(i).changeAllColors(0,clr1)
      data(i).changeAllColors(1,clr2)
    }
    updateInfoGraphics();
  }
  def onClicked(x:Int,y:Int):Unit = {
    for (i <- data.indices) {
      if(x >= data(i).getX && x <= data(i).getX + (data(i).getRadius*2) && y >= data(i).getY && y <= (data(i).getY + (data(i).getLength*2*data(i).getRadius)) && !data(i).is_full()){
        if(index == 0){
          index=1
        }else{
          index=0
        }
        data(i).onClicked(x,y, if (index==0) color1 else color2, index)
        val has_won: Boolean = checkWin()
        /*if(has_won){
          Thread.sleep(500)
          reset();
        }*/
        updateInfoGraphics()
      }
    }
  }
  def reset():Unit = {
    init();
    update();
  }
  def exit():Unit ={
      System.exit(0)
  }
  def changeAllColors(idx:Int,clr:Color):Unit = {
    for(i <- data.indices){
      data(i).changeAllColors(idx,clr)
    }
  }

  def checkWin(): Boolean = {
    for (i: Int <- data.indices) {
      var count: Int = 0
      var actualColor: Color = Color.white
      for (j: Int <- data(i).getCircles.indices) {
        val circles: Array[Circle] = data(i).getCircles
        val c = circles(j)
        if(c.is_colored()){
          if(actualColor == Color.white){
            actualColor = c.getColor()
          }
          if(actualColor == c.getColor()){
            count+=1
          }else{
            actualColor=c.getColor()
          }
        }else{
          actualColor = Color.white
        }
        if(count>=4){
          print("won vertically")
          return true
        }
      }
    }
    for (i <- data.indices) {
      for (j <- data(i).getCircles.indices) {
        var count: Int = 0
        var actualColor: Color = Color.white
        for (k <- 0 to 3) {
          if(data.indices.contains(i+k)) {
            val a = data(i + k).getCircles(j)
            if (a.is_colored()) {
              if(actualColor == Color.white){
                actualColor = a.getColor()
              }
              if (actualColor == a.getColor()) {
                count += 1
              } else {
                count=0
                actualColor = a.getColor()
              }
            }else{
              actualColor = Color.white
            }
            if (count >= 4) {
              println(s"win horizontaly ")
              return true
            }
          }
        }
        count = 0
        actualColor = Color.white
        if (data(i).getCircles(j).is_colored()) {
          for (k <- 0 to 3) {
            if (data.indices.contains(i + k) && data(i + k).getCircles.indices.contains(j + k)) {
              val c: Circle = data(i + k).getCircles(j + k)
              if (k == 0) {
                actualColor = c.getColor()
              } else {
                if (actualColor == c.getColor() && c.is_colored()) {
                  count += 1
                }
              }
              if (count == 3) {
                print("win diagonaly down")
                return true
              }
            }
          }
          count = 0
          actualColor = Color.white
          for (k: Int <- 0 to 3) {
            if (data.indices.contains(i + k) && data(i + k).getCircles.indices.contains(j - k)) {
              val c: Circle = data(i + k).getCircles(j - k)
              if (k == 0) {
                actualColor = c.getColor()
              } else {
                if (actualColor == c.getColor() && c.is_colored()) {
                  count += 1
                }
              }
              if (count == 3) {
                print("win diagonaly up")
                return true
              }
            }
          }
        }
      }
    }
    false

  }

  private def init():Unit = {
    for (i <- data.indices) {
      data(i) = new CircleColumn(graphics,LENGTH_Y,(i+1)*x,y,RADIUS)
    }
  }
  def update():Unit = {
    graphics.clear()
    draw()
  }
  def draw():Unit = {
    graphics.setColor(Color.blue)
    graphics.drawFillRect(x,y,(LENGTH_X)*x, LENGTH_Y*2*RADIUS)
    for(i <- data.indices){
      data(i).drawLine()
    }
    updateInfoGraphics()
  }
}
