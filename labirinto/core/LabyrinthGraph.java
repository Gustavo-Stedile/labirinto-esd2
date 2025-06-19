package labirinto.core;

import java.util.*;

public class LabyrinthGraph {
    private Map<String, List<String>> adjacencyList;
    private String startNode;
    private String endNode;

    public LabyrinthGraph(String[][] labyrinth) {
        adjacencyList = new HashMap<>();
        buildGraph(labyrinth);
    }

    private void buildGraph(String[][] labyrinth) {
        int rows = labyrinth.length;
        if (rows == 0)
            return;
        int cols = labyrinth[0].length;

        // First pass: identify all valid nodes (non-wall cells)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String cell = labyrinth[i][j];
                if (!cell.equals("#")) {
                    String nodeId = i + "," + j;
                    adjacencyList.put(nodeId, new ArrayList<>());

                    if (cell.equals("0")) {
                        startNode = nodeId;
                    } else if (cell.equals("1")) {
                        endNode = nodeId;
                    }
                }
            }
        }

        // Second pass: build edges between adjacent nodes
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String cell = labyrinth[i][j];
                if (!cell.equals("#")) {
                    String currentNode = i + "," + j;

                    // Check adjacent cells (up, down, left, right)
                    int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

                    for (int[] dir : directions) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];

                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols &&
                                !labyrinth[ni][nj].equals("#")) {
                            String neighborNode = ni + "," + nj;
                            adjacencyList.get(currentNode).add(neighborNode);
                        }
                    }
                }
            }
        }
    }

    public Map<String, List<String>> getAdjacencyList() {
        return adjacencyList;
    }

    public String getStartNode() {
        return startNode;
    }

    public String getEndNode() {
        return endNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Start: ").append(startNode).append("\n");
        sb.append("End: ").append(endNode).append("\n");
        sb.append("Graph:\n");

        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }
}