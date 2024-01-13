class Game(g: Grid = new Grid(7, 6)) {

  private var moveCount: Int = 0
  private var numToWin: Int = 4

  /*
  * Play method
  */
  def play(): Unit = {
    println(g.drawGrid())
    do {
      val columnChoice: Int = chooseColumn()
      if (!g.isColumnFull(columnChoice)) {
        moveCount += 1
      }
      g.showSymbol(columnChoice)
      if (!checkWin()) {
        println(s"Next move : ${g.getNextPlayer()}")
      }
    } while (!checkWin())
  }

  /*
  * Gives the possibility to choose a Column using the Input Class
  */
  def chooseColumn(): Int = {
    var i: Int = 0
    do {
      print(s"\rPlease enter a column (1-${g.getX}): ")
      i = Input.readInt()
    } while (i < 1 || i > g.getX())
    i
  }

  /*
  * Checks after every move of a player if he/she won.
  */
  private def checkWin(): Boolean = {
    var end: Boolean = false
    val currentPlayer: String = if (moveCount % 2 == 0) "O" else "X"

    // Horizontal check
    for (i <- 0 until g.getY()) {
      var playerCount: Int = 0
      for (j <- 0 until g.getX()) {
        if (g.getGrid()(i)(j) == currentPlayer) {
          playerCount += 1
          if (playerCount == numToWin) {
            end = true
            println(Console.GREEN + s"$currentPlayer has won! *Horizontal")
            replay()
          }
        } else {
          playerCount = 0
        }
      }
    }

    // Vertical check
    for (i <- 0 until g.getX()) {
      var playerCount: Int = 0
      for (j <- 0 until g.getY()) {
        if (g.getGrid()(j)(i) == currentPlayer) {
          playerCount += 1
          if (playerCount == numToWin) {
            end = true
            println(Console.GREEN + s"$currentPlayer has won! *Vertical")
            replay()
          }
        } else {
          playerCount = 0
        }
      }
    }

    // Diagonal from top-left
    for (i <- 0 until g.getY() - numToWin + 1) {
      var playerCount: Int = 0
      for (j <- 0 until g.getX() - numToWin + 1) {
        for (k <- 0 until numToWin) {
          if (g.getGrid()(i + k)(j + k) == currentPlayer) {
            playerCount += 1
            if (playerCount == numToWin) {
              end = true
              println(Console.GREEN + s"$currentPlayer has won! *Diagonal Top-Left")
              playerCount = 0
              replay()
            }
          } else {
            playerCount = 0
          }
        }
        playerCount = 0
      }
    }

    // Diagonal from top-right
    for (i <- 0 until g.getY() - numToWin + 1) {
      var playerCount: Int = 0
      for (j <- g.getX() - 1 until g.getX() - numToWin - 1 by -1) {
        for (k <- 0 until numToWin) {
          if (g.getGrid()(i + k)(j - k) == currentPlayer) {
            playerCount += 1
            if (playerCount == numToWin) {
              end = true
              println(Console.GREEN + s"$currentPlayer has won! *Diagonal Top-Right")
              replay()
            }
          } else {
            playerCount = 0
          }
        }
      }
    }

    // Check for a draw
    if (!end && moveCount == g.getX() * g.getY()) {
      end = true
      println("Nobody has won...")
      replay()
    }
    end
  }

  def clearScreen(): Unit = {
    for (i <- 0 until 50) {
      println()
    }
  }

  def replay(): Unit = {
    g.clearGrid()
    moveCount = 0
    print(Console.RESET + "Do you want to replay ? (y/n): ")
    if (Input.readChar().toLower == 'y') {
      clearScreen()
      play()
    } else {
      println("Thanks for playing")
      System.exit(0)
    }
  }
}
