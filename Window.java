import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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

    public void disable() {
        marked = false;
        setBackground(Color.WHITE);
    }
}

class Window extends JFrame {
    private Footer footer;
    
    private Labyrinth labyrinth = new Labyrinth(10, 10);
    private Path[][] paths = new Path[10][10];
    
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
        add(createLabyrint());        

        footer = new Footer();
        add(footer, BorderLayout.SOUTH);

        footer
            .resetButton
            .addActionListener(this::resetLabyrinth);
        
        footer
            .profundidadeButton
            .addActionListener(_ -> {
                buscarEmProfundidade();
            });

        footer
            .larguraButton
            .addActionListener(_ -> {
                buscarEmLargura();
            });
    }
    
    private JPanel createLabyrint() {
        JPanel gridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        
        for (int i = 0; i < labyrinth.h(); i++) {
            for (int j = 0; j < labyrinth.w(); j++) {
                int x = j; int y = i;
                
                gbc.gridx = x;
                gbc.gridy = y;
                
                Path path = new Path();
                paths[y][x] = path;
                
                path.addActionListener(_ -> {
                    labyrinth.set(x, y, path.toggle());
                    labyrinth.show();
                });
    
                gridPanel.add(path, gbc);
            }
        }
        return gridPanel;
    }

    private void resetLabyrinth(ActionEvent ev) {
        for (int y = 0; y < labyrinth.h(); y++) {
            for (int x = 0; x < labyrinth.w(); x++) {
                paths[y][x].disable();
                labyrinth.set(x, y, " ");
            }
        }
    }
}
