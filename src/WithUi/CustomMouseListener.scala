package WithUi

import java.awt.event.{MouseEvent, MouseListener}
import java.util.Date

/**
 * Classe permettant de gérer les clics de l'utilisateur sur l'interface graphique
 *
 * @param game
 */
class CustomMouseListener(game: Game) extends MouseListener {
  var click: Date = null;
  var delta: Long = 10000

  override def mouseClicked(e: MouseEvent): Unit = {
  }

  /**
   * Fonction qui est appelé à chaque clic de l'utilisateur , il appèle la fonction qui gère le clic de l'utilisateur dans l'instance du jeu ou
   * le changement de couleur lorsque c'est un double clic
   *
   * @param e
   */
  override def mousePressed(e: MouseEvent): Unit = {
    if (click == null) {
      click = new Date();
    } else {
      delta = (new Date()).getTime - click.getTime;
      click = new Date();
    }
    if (delta <= 200) {
      game.setColors()
    } else {
      game.onClicked(e.getX, e.getY)
    }
  }

  override def mouseReleased(e: MouseEvent): Unit = {}

  override def mouseEntered(e: MouseEvent): Unit = {}

  override def mouseExited(e: MouseEvent): Unit = {}
}
