class Game(g: Grid) {

  private var moveCount: Int = 1

  /*
  * Play method
  */
  def play(): Unit = {
    println(g.drawGrid())
    do{
      val columnChoice:Int = chooseColumn()
      if(!g.isColumnFull(columnChoice)){
        moveCount += 1
      }
      g.showSymbol(columnChoice)
    } while (!checkWin())
  }

  /*
  * Gives the possibility to choose a Column using the Input Class
  */
  def chooseColumn(): Int = {
    var i: Int = 0
    do {
      print("Please enter a column (1-7): ")
      i = Input.readInt()
    } while(i < 1 || i > 7)
    i
  }

  /*
  * Checks after every move of a player if he/she won.
  */
  private def checkWin(): Boolean = {
    var end: Boolean = false
    val currentPlayer: String = if (moveCount % 2 == 0) "X" else "O"

    // Horizontal check
    for (i <- 0 until g.getY()) {
      var playerCount: Int = 0
      for (j <- 0 until g.getX()) {
        if (g.getGrid()(i)(j) == currentPlayer) {
          playerCount += 1
          if (playerCount == 4) {
            end = true
            println(s"$currentPlayer has won!")
            replay()
          }
        } else {
          playerCount = 0
        }
      }
    }

    // Check for a draw
    if (!end && moveCount == g.getX() * g.getY()) {
      println("Nobody has won...")
      replay()
      end = true
    }
    end
  }

  def replay(): Unit = {
    g.clearGrid()
    print("Do you want to replay ? (y/n): ")
    if(Input.readChar().toLower == 'y'){
      play()
    } else {
      println("Thanks for playing")
    }
  }
}
