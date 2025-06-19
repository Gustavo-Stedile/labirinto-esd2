package labirinto.core.gui;

import java.awt.*;
import javax.swing.*;

public class Footer extends JPanel {
    public JButton resetButton, profundidadeButton, larguraButton;

    public Footer() {
        super(
                new FlowLayout(
                        FlowLayout.CENTER,
                        10, 10));

        setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10,
                        10, 10));

        resetButton = new JButton("reiniciar");
        profundidadeButton = new JButton("buscar em profundidade");
        larguraButton = new JButton("buscar em largura");

        add(resetButton);
        add(profundidadeButton);
        add(larguraButton);
    }
}
