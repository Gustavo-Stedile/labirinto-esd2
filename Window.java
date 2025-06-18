import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class Window extends JFrame {
    private Footer footer;
    private Labyrinth labyrinth;

    public Window() {
        props();
        init();
    }

    private void props() {
        setTitle("título");
        setSize(500, 500);
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
