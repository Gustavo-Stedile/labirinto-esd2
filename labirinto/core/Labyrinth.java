package labirinto.core;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import labirinto.core.pathfinding.Pathfinding;

import java.util.List;

public class Labyrinth extends JPanel {

    private String[][] labyrinth;
    private Tile[][] tiles;

    private int w, h;

    public Labyrinth(int w, int h, Point start, Point end) {
        this.labyrinth = new String[h][w];
        this.tiles = new Tile[h][w];

        this.w = w;
        this.h = h;

        init();

        labyrinth[start.y][start.x] = Tile.START;
        labyrinth[end.y][end.x] = Tile.END;

        create();
    }

    private void removePreviousPath() {
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                if (labyrinth[y][x].equals(Tile.TRAVELED))
                    labyrinth[y][x] = tiles[y][x].set(Tile.EMPTY);
    }

    private void init() {
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                labyrinth[y][x] = Tile.EMPTY;
    }

    public void solveProfundidade(ActionEvent ev) {
        LabyrinthGraph graph = new LabyrinthGraph(labyrinth);
        List<Point> path = Pathfinding.withDepthFirstSearch(graph);
        drawPath(path);
    }

    public void solveLargura(ActionEvent ev) {
        LabyrinthGraph graph = new LabyrinthGraph(labyrinth);
        List<Point> path = Pathfinding.withBreadthFirstSearch(graph);
        drawPath(path);
    }

    public void drawPath(List<Point> points) {
        if (points.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Labirinto sem solução", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        removePreviousPath();

        for (Point p : points) {
            int y = p.x;
            int x = p.y;

            if (labyrinth[y][x].equals(Tile.START) || labyrinth[y][x].equals(Tile.END))
                continue;

            this.labyrinth[y][x] = tiles[y][x].set(Tile.EMPTY);
            labyrinth[y][x] = tiles[y][x].set(Tile.TRAVELED);
        }
    }

    public void reset() {
        for (int y = 0; y < this.h; y++) {
            for (int x = 0; x < this.w; x++) {
                if (labyrinth[y][x].equals(Tile.START) || labyrinth[y][x].equals(Tile.END))
                    continue;

                this.labyrinth[y][x] = tiles[y][x].set(Tile.EMPTY);
            }
        }
    }

    public void create() {
        super.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < this.h; i++) {
            for (int j = 0; j < this.w; j++) {
                int x = j;
                int y = i;

                gbc.gridx = x;
                gbc.gridy = y;

                Tile tile = new Tile(labyrinth[y][x]);
                tiles[y][x] = tile;

                tile.addActionListener(_ -> {
                    labyrinth[y][x] = tile.toggle(Tile.EMPTY, Tile.WALL);
                });

                super.add(tile, gbc);
            }
        }
    }

    public void show() {
        for (String[] row : this.labyrinth) {
            for (String col : row)
                System.out.print(col);

            System.out.println();
        }
    }
}