import hevs.graphics.FunGraphics

import java.awt.Color
import javax.swing.JColorChooser


/**
 * Classe permettant de lancer et de gérer une ou plusieurs parties de Puissance 4
 */
class Game {
  var graphics = new FunGraphics(500,500,200,200,"Connect Four",true)
  val RADIUS:Int = 20
  val LENGTH_X:Int = 7
  val LENGTH_Y:Int = 6
  val OFFSET_BETWEEN_CIRCLE:Int = RADIUS/2
  var player_won:String = ""
  var data:Array[CircleColumn] = Array.ofDim[CircleColumn](LENGTH_X)
  var color1:Color = Color.red
  var color2:Color = Color.yellow
  var count:Int = 0
  var index:Int = 0
  var mouseListener:CustomMouseListener = new CustomMouseListener(this)
  graphics.addMouseListener(mouseListener)
  var x:Int = 50
  var y:Int = 100

  init()


  /**
   * Fonction permettant d'actualiser les informations des utilisateurs sur l'interface graphique
   */
  def updateInfoGraphics:Unit = {
    graphics.setColor(Color.white)
    graphics.drawFillRect(50,0,100,100)
    graphics.drawString(30,45,"Next :",Color.black,20)
    graphics.setColor(if (index == 1) color1 else color2)
    graphics.drawFilledCircle(90, 20, 40)

    graphics.drawString(275,40,str="Player 1 :",Color.black,size=20)
    graphics.setColor(color1);
    graphics.drawFilledCircle(370,15,40)
    graphics.drawString(275,80,str="Player 2 :",Color.black,size=20)
    graphics.setColor(color2);
    graphics.drawFilledCircle(370,55,40)
  }

  /**
   * Fonction permettant de choisir les couleurs des deux utilisateurs
   */
  def setColors():Unit = {
    val clr1:Color = JColorChooser.showDialog(null,"player 1 color ",color1)
    val clr2:Color = JColorChooser.showDialog(null,"player 2 color",color2)
    if (clr1 == clr2 || clr1 == Color.white  || clr1 == Color.black || clr2 == Color.white  || clr2 == Color.black) {
      return
    }
    color1 = clr1
    color2 = clr2
    for(i <- data.indices){
      data(i).changeAllColors(0,clr1)
      data(i).changeAllColors(1,clr2)
    }
    updateInfoGraphics;
  }

  /**
   * Fonction permettant de gérer le clique de l'utilisateur sur l'interface graphique
   * @param x
   * @param y
   */
  def onClicked(x:Int,y:Int):Unit = {
    for (i:Int <- data.indices) {
      if(x >= data(i).getX && x <= data(i).getX + (data(i).getRadius*2) && y >= data(i).getY && y <= (data(i).getY + (data(i).getLength*2*data(i).getRadius)) && !data(i).is_full()){
        if(index == 0){
          index=1
        }else{
          index=0
        }
        count += + data(i).onClicked(x,y, if (index==0) color1 else color2, index)
        val has_won: Boolean = checkWin()
        if(has_won){
          Thread.sleep(500)
          reset();
        }
        updateInfoGraphics
      }
    }
  }

  /**
   * Fonction permettant de reset l'affichage
   */
  def reset():Unit = {
    graphics.clear();
    graphics.drawString(200,200,s"${player_won} has won !",color=Color.black,size=20)
    Thread.sleep(5000)
    count=0
    index = if(index == 1) 0 else 1
    init();
    update();
  }
  /**
   * Fonction permettant de vérifier si un des utilisateur à gagné
   * @return vrai si un des utilisateur à gagné
   */
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
          player_won = if(color1 == actualColor) "player 1" else "player2"
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
              player_won = if(color1 == actualColor) "player 1" else "player2"
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
                player_won = if(color1 == actualColor) "player 1" else "player2"
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
                player_won = if(color1 == actualColor) "player 1" else "player2"
                print("win diagonaly up")
                return true
              }
            }
          }
        }
      }
    }
    if(count == LENGTH_Y*LENGTH_X){
      player_won = "no one"
      return true
    }
    false

  }

  private def init():Unit = {
    for (i <- data.indices) {
      data(i) = new CircleColumn(graphics,LENGTH_Y,(i*2*(RADIUS+OFFSET_BETWEEN_CIRCLE))+x,y,RADIUS)
    }
  }
  def update():Unit = {
    graphics.clear()
    draw()
  }

  def draw(): Unit = {
    graphics.setColor(Color.blue)
    val boardWidth = 2 * LENGTH_X * (RADIUS + OFFSET_BETWEEN_CIRCLE)
    val boardHeight = 2 * RADIUS * LENGTH_Y
    graphics.drawFillRect(x, y, boardWidth - RADIUS, boardHeight)
    for (i <- data.indices) {
      data(i).drawLine()
    }
    updateInfoGraphics
  }

}
