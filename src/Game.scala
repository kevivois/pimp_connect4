class Game(g: Grid) {

  private var idx: Int = 0
  private var end: Boolean = false

  /*
  * Main class
  */
  def play(): Unit = {
    idx = 0
    println(g.drawGrid())
    do{
      g.showSymbol(chooseColumn())
      idx += 1
      checkWin()
    } while (!end)
  }

  /*
  * Gives the possibility to choose a Column using the Input Class
  * Big thanks for ISC Team!
  */
  def chooseColumn(): Int = {
    var i: Int = 0
    do {
      print("Please enter a column (1-7): ")
      i = Input.readInt()
    } while(i < 1 || i > 7)
    i
  }

  def checkWin(): Unit = {
    if(idx == 42){
      end = true
    }
  }
}
