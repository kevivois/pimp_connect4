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
  var x:Int = 2*RADIUS+20
  var y:Int = 100
  init()

  def updateInfoGraphics():Unit = {
    graphics.setColor(Color.white)
    graphics.drawFillRect(50,0,100,100)
    graphics.setColor(Color.black)
    graphics.drawString(30,45,"Next :")
    graphics.setColor(if(index == 1) color1 else color2)
    graphics.drawFilledCircle(100,30,40)
  }
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
  def onClicked(x:Int,y:Int):Unit = {
    for (i <- data.indices) {
      if(x >= data(i).getX && x <= data(i).getX + (data(i).getRadius*2) && y >= data(i).getY && y <= (data(i).getY + (data(i).getLength*2*data(i).getRadius))){
        if(index == 0){
          index=1
        }else{
          index=0
        }
        val runnable = new CustomRunnable(this,data(i),x,y,if (index==0) color1 else color2,index)
        val t:Thread = new Thread(runnable)
        t.start()
        updateInfoGraphics()
      }
    }
  }
  def exit_won(b:Boolean):Unit ={
    if (b) {
      System.exit(0)
    }
  }
  def changeAllColors(idx:Int,clr:Color):Unit = {
    for(i <- data.indices){
      data(i).changeAllColors(idx,clr)
    }
  }
  def checkWin():Boolean = {
    // check 4 horizontal

    for(i <- data.indices){
      var follow:Int = 0
      var actualColor:Color = new Color(0,0,0)
      for(j <- data(i).getCircles.indices){
        val circles = data(i).getCircles
        if(j == 0){
          actualColor = circles(j).getColor()
          follow+=1
        }
        else{
          if(actualColor == circles(j).getColor() && circles(j).is_colored()){
            follow+=1
          }else{
            follow=0
            actualColor = circles(j).getColor()
          }
          if(follow >= 3){
            print("won 4 verticaly")
            return true
          }
        }
      }
    }
    for(i <- data.indices){
      for(j <- data(i).getCircles.indices){
        var circles = data(i).getCircles
        var count:Int = 0
        var actualColor:Color = new Color(255,255,255)
        for(k <- i until circles.length){
          var a = data(k).getCircles(j)
          if(a.is_colored() && actualColor == a.getColor()){
            count+=1
          }else{
            count =0
            if(a.is_colored()){
              actualColor = a.getColor()
            }
          }
          if(count == 3){
            println("win horizontaly")
            return true
          }

        }
      }
    }
    for(i <- data.indices){
      for(j <- data(i).getCircles.indices) {
        var circles = data(i).getCircles
        var count = 0
        var actualColor = Color.white
        if (data(i).getCircles(j).is_colored()) {
          for (k <- 0 to 3) {
            if (data.indices.contains(i + k) && data(i+k).getCircles.indices.contains(j+k)) {
              val c = data(i + k).getCircles(j + k)
              if (k == 0) {
                actualColor = c.getColor()
                count += 1
              } else {
                if (actualColor == c.getColor() && c.is_colored()) {
                  count += 1
                }
              }
              if (count == 4) {
                print("win diagonaly down")
                return true
              }
            }
          }
          count = 0
          actualColor = Color.white
          for (k <- 0 to 3) {
            if (data.indices.contains(i + k) && data(i+k).getCircles.indices.contains(j-k)) {
              val c = data(i + k).getCircles(j-k)
              if (k == 0) {
                actualColor = c.getColor()
                count += 1
              } else {
                if (actualColor == c.getColor() && c.is_colored()) {
                  count += 1
                }
              }
              if (count == 4) {
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
    graphics.addMouseListener(mouseListener)
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
    graphics.drawFillRect(x,y,LENGTH_X*x,LENGTH_Y*2*RADIUS)
    for(i <- data.indices){
      data(i).drawLine()
    }
    updateInfoGraphics()
  }
}
