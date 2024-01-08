import hevs.graphics.FunGraphics

import java.awt.Color
import javax.swing.JColorChooser

class Game {
 var graphics = new FunGraphics(500,500,200,200,"game",true)
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
      if(x >= data(i).getX && x <= data(i).getX + (data(i).getRadius*2) && y >= data(i).getY && y <= (data(i).getY + (data(i).getLength*2*data(i).getRadius)) && !data(i).is_full()){
        if(index == 0){
          index=1
        }else{
          index=0
        }
        /*
        val runnable = new CheckWinRunnable(this,data(i),x,y,if (index==0) color1 else color2,index)
        val t:Thread = new Thread(runnable)
        t.start()
        */
        data(i).onClicked(x, y, if (index==0) color1 else color2, index)
        val has_won: Boolean = checkWin()
        if(has_won){
          Thread.sleep(1000)
          reset();
        }
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
    def checkLine(line: Seq[Circle]): Boolean = {
      var count = 0
      var color: Color = null

      for (circle <- line) {
        if (circle.is_colored()) {
          if (color == circle.getColor()) {
            count += 1
          } else {
            count = 1
            color = circle.getColor()
          }

          if (count >= 4) {
            return true
          }
        } else {
          count = 0
        }
      }

      false
    }

    def checkRows(): Boolean = {
      for (row <- data) {
        if (checkLine(row.getCircles)) {
          println("Win horizontally")
          return true
        }
      }
      false
    }

    def checkColumns(): Boolean = {
      for (i <- data.head.getCircles.indices) {
        val column = data.map(row => row.getCircles(i))
        if (checkLine(column)) {
          println("Win vertically")
          return true
        }
      }
      false
    }

    def checkDiagonals(): Boolean = {
      for {
        i <- data.indices
        j <- data(i).getCircles.indices
        if i + 3 < data.length && j + 3 < data(i).getCircles.length
      } {
        val diagonal = for (k <- 0 to 3) yield data(i + k).getCircles(j + k)

        if (checkLine(diagonal)) {
          println("Win diagonally down")
          return true
        }
      }

      for {
        i <- data.indices
        j <- data(i).getCircles.indices
        if i + 3 < data.length && j - 3 >= 0
      } {
        val diagonal = for (k <- 0 to 3) yield data(i + k).getCircles(j - k)

        if (checkLine(diagonal)) {
          println("Win diagonally up")
          return true
        }
      }

      false
    }

    checkRows() || checkColumns() || checkDiagonals()
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
    graphics.drawFillRect(x,y,LENGTH_X*x,LENGTH_Y*2*RADIUS)
    for(i <- data.indices){
      data(i).drawLine()
    }
    updateInfoGraphics()
  }
}
