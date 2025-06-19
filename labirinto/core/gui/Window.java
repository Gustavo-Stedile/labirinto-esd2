package labirinto.core.gui;

import javax.swing.*;

import labirinto.core.Labyrinth;

import java.awt.*;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    public final int WIDTH, HEIGHT;

    private Footer footer;
    private Labyrinth labyrinth;

    public Window(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        props();
        init();
    }

    private void props() {
        setTitle("título");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void use(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
        add(labyrinth, BorderLayout.CENTER);
    }

    public void onclickProfundidade(ActionListener listener) {
        footer.profundidadeButton.addActionListener(listener);

    }

    public void onclickLargura(ActionListener listener) {
        footer.larguraButton.addActionListener(listener);
    }

    private void init() {
        footer = new Footer();
        add(footer, BorderLayout.SOUTH);

        footer.resetButton
                .addActionListener(_ -> labyrinth.reset());
    }
}
