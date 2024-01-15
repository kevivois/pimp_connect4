class Grid(x: Int, y: Int) {

  private var res: String = "\nConnect4 on console by FS and KV\n\n"
  private var grid_2d: Array[Array[String]] = Array.fill(y, x)(" ")
  private var player: Int = 0
  private var p: String = ""
  private var next_p: String = ""

  def getX(): Int = x

  def getY(): Int = y

  def getGrid(): Array[Array[String]] = grid_2d

  def getNextPlayer(): String = next_p

  def getPlayer(): String = p

  // Clears the grid for replay
  def clearGrid(): Unit = {
    grid_2d = Array.fill(y, x)(" ")
    player = 0
  }

  // Checks if a column is full, the method will not permit the move counter to increment
  def isColumnFull(column: Int): Boolean = {
    for (i <- 0 until y) {
      if (grid_2d(i)(column - 1) == " ") {
        return false
      }
    }
    true
  }

  // Draws the grid with the values from the Array grid_2d
  def drawGrid(): String = {
    for (i <- 0 to 2 * y) {
      for (j <- 0 until x) {
        if (i % 2 == 0) {
          res += "+----"
          if (j == x - 1) {
            res += "+"
          }
        } else {
          res += s"|  ${grid_2d(i / 2)(j)} "
          if (j == x - 1) {
            res += "|"
          }
        }
      }
      res += "\n"
    }
    res += "  "
    for (i <- 1 to x) {
      res += " " + i + "   "
    }
    res += "\n"
    res
  }

  // Selects the player (O or X)
  def setNextPlayer(): String = {
    if (player % 2 == 0) {
      p = Console.RED + "X" + Console.RESET
      next_p = Console.YELLOW + "O" + Console.RESET
      player = 1
    } else {
      p = Console.YELLOW + "O" + Console.RESET
      next_p = Console.RED + "X" + Console.RESET
      player = 0
    }
    p
  }

  // Sets the symbol of a player on the grid
  def setSymbol(column: Int): Unit = {
    var doing: Boolean = true
    for (i <- y - 1 to 0 by -1) {
      for (j <- 0 until x) {
        if (doing) {
          if (j == column - 1 && grid_2d(i)(column - 1) == " ") {
            grid_2d(i)(j) = setNextPlayer()
            doing = false
            res = ""
          }
        }
      }
    }
  }
}
