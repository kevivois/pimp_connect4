import hevs.graphics.FunGraphics

class Game {
 var graphics = new FunGraphics(500,500,200,200,"test",true)
  val RADIUS = 20
  val LENGTH_X = 7
  val LENGTH_Y = 6
  var data:Array[CircleColumn] = Array.ofDim[CircleColumn](LENGTH_X)
  var mouseListener = new CustomMouseListener(this)
  init()
  def onClicked(x:Int,y:Int):Boolean = {
    for (i <- data.indices) {
      data(i).onClicked(x,y)
    }
    false
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
