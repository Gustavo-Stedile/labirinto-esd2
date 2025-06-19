package labirinto.app;

import labirinto.core.Labyrinth;
import labirinto.core.Point;
import labirinto.core.gui.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window(640, 480);

        Labyrinth labyrinth = new Labyrinth(12, 7, new Point(0, 0), new Point(11, 6));
        window.use(labyrinth);

        window.onclickLargura(labyrinth::solveLargura);
        window.onclickProfundidade(labyrinth::solveProfundidade);

        window.setVisible(true);
    }
}