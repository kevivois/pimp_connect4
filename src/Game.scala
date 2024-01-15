class Game(g: Grid = new Grid(7, 6)) {

  private var moveCount: Int = 0
  private var numToWin: Int = 4

  // Main method, used to play the game
  def play(): Unit = {
    var reset: Boolean = false
    println(g.drawGrid())
    while (true) {
      if (reset) {
        moveCount = 0
        g.clearGrid()
        println(g.drawGrid())
        reset = false
      }

      // Doesn't show the Next move at the beginning
      if (moveCount != 0) {
        println(s"Next move : ${g.getNextPlayer()}")
      }
      val columnChoice: Int = chooseColumn()
      g.setSymbol(columnChoice)
      println(g.drawGrid())

      // Used to increment the moveCount only if a column is not full
      if (!g.isColumnFull(columnChoice)) {
        moveCount += 1
      }

      if (checkWin()) {
        if (replay()) {
          reset = true
        } else {
          System.exit(0);
        }
      }
    }
  }


  // Gives the possibility to choose a Column using the Input Class
  def chooseColumn(): Int = {
    var i: Int = 0
    do {
      print(s"\rPlease enter a column (1-${g.getX}): ")
      i = Input.readInt()
    } while (i < 1 || i > g.getX())
    i
  }


  // Checks after every move of a player if he/she won.
  private def checkWin(): Boolean = {
    val currentPlayer = g.getPlayer()

    // Horizontal check
    for (i <- 0 until g.getY()) {
      var playerCount: Int = 0
      for (j <- 0 until g.getX()) {
        if (g.getGrid()(i)(j) == currentPlayer) {
          playerCount += 1
          if (playerCount == numToWin) {
            println(s"$currentPlayer has won! *Horizontal")
            return true
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
            println(s"$currentPlayer has won! *Vertical")
            return true
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
              println(s"$currentPlayer has won! *Diagonal Top-Left")
              playerCount = 0
              return true
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
              println(s"$currentPlayer has won! *Diagonal Top-Right")
              return true
            }
          } else {
            playerCount = 0
          }
        }
      }
    }

    // Check for a draw
    if (moveCount == g.getX() * g.getY()) {
      println("Nobody has won...")
      return true
    }
    false
  }

  def replay(): Boolean = {
    print(Console.RESET + "Do you want to replay ? (y/n): ")
    if (Input.readChar().toLower == 'y') {
      true
    } else {
      println("Thanks for playing")
      false
    }
  }
}
