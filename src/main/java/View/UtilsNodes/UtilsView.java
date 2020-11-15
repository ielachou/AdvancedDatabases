package View.UtilsNodes;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

/**
 * Interface implémentant les méthodes utiles pour la vue
 */
public interface UtilsView {

    /**
     * Retourne une ligne graphique
     *
     * @param x1    Début de ligne ordonnées
     * @param x2    Fin de ligne ordonnées
     * @param y1    Début de la ligne
     * @param y2    Fin de la ligne
     * @param width largeur de ligne
     * @param paint zone de dessin
     * @return une ligne
     */
    default Line createLine(double x1, double y1, double x2, double y2, int width, Paint paint) {
        Line line = new Line();
        line.setStartX(x1);
        line.setStartY(y1);
        line.setEndX(x2);
        line.setEndY(y2);
        line.setStrokeWidth(width);
        line.setStroke(paint);
        return line;
    }

    /**
     * Fonction permettant de créer un objet MenuBar
     *
     * @param menu       le menu a y ajouté
     * @param preflength la longueur préféré
     * @param prefwidth  la largeur préféré
     * @param backcolor  la couleur de fond
     * @param backradius l'arrondis du menubar
     * @param fontsize   la taille de police
     * @return un objet menubar
     */
    default MenuBar createMenuBar(Menu menu, int preflength, int prefwidth, String backcolor, int backradius, int fontsize) {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color:" + backcolor + ";" +
                "-fx-background-radius:" + backradius + "px;" +
                "-fx-font-size:" + fontsize + ";");
        menuBar.setPrefSize(preflength, prefwidth);
        menuBar.getMenus().add(menu);

        return menuBar;
    }

    /**
     * Fonction permettant de créer un objet scrollPane
     *
     * @param vbar      règle d'extension vertical de la scrollPane
     * @param hbar      règle d'extension horizontal de la scrollPane
     * @param fitwidth  largeur de la scrollPane
     * @param fitheight longueur de la scrollPane
     * @param content   contenu a ajouter au scrollPane
     * @return objet Scrollpane obtenu
     */
    default ScrollPane createScrollPane(ScrollPane.ScrollBarPolicy vbar, ScrollPane.ScrollBarPolicy hbar,
                                        boolean fitwidth, boolean fitheight, Node content) {
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setVbarPolicy(vbar);
        scrollPane.setHbarPolicy(hbar);
        scrollPane.fitToWidthProperty().set(fitwidth);
        scrollPane.fitToHeightProperty().set(fitheight);
        scrollPane.setContent(content);

        return scrollPane;
    }

}
