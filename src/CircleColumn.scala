import hevs.graphics.FunGraphics

import java.awt.Color

/**
 * Fonction représentant une colonne du puissance 4
 * @param graphics gère l'affichage des informations / cercles
 * @param length nombre de cercle pour chaque colonne
 * @param x Cordonnée x du point de départ de la colonne
 * @param y Cordonnée y du point de départ de la colonne
 * @param radius rayon des cercles de la colonne
 */
class CircleColumn(graphics:FunGraphics,length:Int,x:Int,y:Int,radius:Int) {
  private val circles:Array[Circle] = Array.ofDim[Circle](length)
  private var filledCirles:Int = 0
  def getX:Int = x
  def getY:Int = y
  def getRadius:Int = radius

  def getLength:Int = length
  def getCircles:Array[Circle] = circles
  init()

  /**
   * Fonction qui gère le clique de l'utilisateur sur la colonne
   * @param pX position x du clique de l'utilisateur
   * @param pY position y du clique de l'utilisateur
   * @param color Couleur actuel à remplir dans les cercles
   * @param clrIndex index de couleur de la couleur actuelle 
   * @return
   */
  def onClicked(pX:Int,pY:Int,color:Color,clrIndex:Int):Int = {
    val mov:Int = if(!this.is_full()) 1 else 0
    for(i <- circles.indices){
      if(!circles(i).is_colored()){
        circles(i).fill(color)
        circles(i).setColorIndex(clrIndex)
        if(i >= 1 && circles(i-1).is_colored()){
          circles(i-1).empty()
          circles(i-1).setColorIndex(-1)
        }
      }else{
        return mov
      }
      Thread.sleep(60)
    }
    filledCirles+=1
    mov
  }
  def changeAllColors(idx:Int,clr:Color): Unit = {
    for(i:Int <- circles.indices){
      if(idx == circles(i).getColorIndex){circles(i).setColor(clr)}
    }
  }
  def is_full():Boolean = {
    var result:Boolean = true
    for(circle:Circle <- circles){
      if(!circle.is_colored()){
        result=false
      }
    }
    result
  }
  def init():Unit = {
    for (i:Int <- circles.indices) {
      val posY:Int = y + (radius*2 * i)
      val posX:Int = x
      circles(i) = new Circle(graphics,posX,posY,radius,false)
    }
  }
  def drawLine():Unit = {
    for(i:Int <- circles.indices){
      circles(i).draw()
    }
  }

}
