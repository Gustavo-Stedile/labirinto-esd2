import javax.swing.*;
import java.awt.*;

class Window extends JFrame {
    private Footer footer;
    private Labyrinth labyrinth;

    public Window() {
        props();
        init();
    }

    private void buscarEmProfundidade() {
        System.out.println("buscando em profundidade...");
    }

    private void buscarEmLargura() {
        System.out.println("buscando em largura...");
    }

    private void props() {
        setTitle("título");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void init() {
        labyrinth = new Labyrinth(10, 10, new Point(0, 0), new Point(9, 9));

        add(labyrinth, BorderLayout.CENTER);

        footer = new Footer();
        add(footer, BorderLayout.SOUTH);

        footer.resetButton
                .addActionListener(_ -> labyrinth.reset());

        footer.profundidadeButton
                .addActionListener(_ -> {
                    buscarEmProfundidade();
                });

        footer.larguraButton
                .addActionListener(_ -> {
                    buscarEmLargura();
                });
    }
}
