import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;

class Path extends JButton {
    private boolean marked;

    public Path() {
        setBackground(Color.WHITE);
    }

    public String toggle() {
        marked = !marked;
        setBackground(marked ? Color.BLACK : Color.WHITE);
        return marked ? "#" : " ";
    }
}

class Window extends JFrame {

    private Labyrinth labyrinth = new Labyrinth(10, 10);

    private void props() {
        setTitle("título");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < labyrinth.h(); i++) {
            for (int j = 0; j < labyrinth.w(); j++) {
                int x = j; int y = i;

                gbc.gridx = x;
                gbc.gridy = y;

                Path path = new Path();
                path.addActionListener(ev -> {
                    labyrinth.set(x, y, path.toggle());
                    labyrinth.show();
                });

                add(path, gbc);
            }
        }

    }

    public Window() {
        props();
        init();
    }
}