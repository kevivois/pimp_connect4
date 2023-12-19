import java.awt.Color

class Game {
  var p1: Boolean = false
  var p2: Boolean = false
  var circle_list: Array[Array[Circle]] = Array.ofDim(7,6)

  def changeCircleColor(p: Int,c: Circle) : Unit = {
    if(p == 0){
      c.color = chooseColor(p)
    } else {
      c.color = chooseColor(p)
    }
  }

  def chooseColor(p: Int) : Color = {
    var color: Color = new Color(0,0,0)
    return color
  }

  def checkWin() : Unit = {

  }

}
