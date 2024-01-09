class Grid(x: Int, y: Int) {

  private var res: String = "\nConnect4 on console by FS and KV\n\n"
  private var grid_2d: Array[Array[String]] = Array.fill(y,x)(" ")
  private var player: Int = 0

  /*
  * Draws the grid with the values from the Array grid_2d
  */
  def drawGrid(): String = {
    for(i<- 0 to 2*y){
      for(j<- 0 until x){
        if(i % 2 == 0){
          res += "+----"
          if (j == x - 1) {
            res += "+"
          }
        } else {
          res += s"|  ${grid_2d(i/2)(j)} "
          if (j == x - 1) {
            res += "|"
          }
        }
      }
      res += "\n"
    }
    res += "  "
    for(i<- 1 to x){
      res += " " + i + "   "
    }
    res += "\n"
    res
  }

  /*
  * Select the player (O or X)
  */
  def selectPlayer(): String = {
    var p:String = ""
    if(player % 2 == 0){
      p = "X"
      player = 1
    } else {
      p = "O"
      player = 0
    }
    p
  }

  /*
  * Draw the symbol of a player on the grid
  */
  def showSymbol(column: Int): Unit = {
    var doing:Boolean = true
    for (i <- y-1 to 0 by -1) {
      for (j <- 0 until x) {
        if(doing) {
          if (j == column - 1 && grid_2d(i)(column - 1) == " ") {
            grid_2d(i)(j) = selectPlayer()
            doing = false
            res = ""
            println(drawGrid())
          }
        }
      }
    }
  }
}
