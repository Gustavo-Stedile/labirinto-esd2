package labirinto.core.pathfinding;

import labirinto.core.LabyrinthGraph;
import labirinto.core.Point;
import java.util.*;

public class Pathfinding {
    public static List<Point> withDepthFirstSearch(LabyrinthGraph graph) {
        // Get the start and end nodes from the graph
        String start = graph.getStartNode();
        String end = graph.getEndNode();

        // Convert string node IDs to Point objects
        Point startPoint = parseNodeId(start);
        Point endPoint = parseNodeId(end);

        // If start or end is missing, return empty path
        if (start == null || end == null) {
            return new ArrayList<>();
        }

        // Data structures for DFS
        Map<String, String> parentMap = new HashMap<>(); // To reconstruct the path
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        // Initialize DFS
        stack.push(start);
        visited.add(start);
        parentMap.put(start, null);

        boolean found = false;

        // Perform DFS
        while (!stack.isEmpty() && !found) {
            String current = stack.pop();

            // Check if we've reached the end
            if (current.equals(end)) {
                found = true;
                break;
            }

            // Explore neighbors
            for (String neighbor : graph.getAdjacencyList().get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    stack.push(neighbor);
                }
            }
        }

        // Reconstruct the path if found
        if (found) {
            return reconstructPath(parentMap, start, end);
        } else {
            return new ArrayList<>(); // No path found
        }
    }

    public static List<Point> withBreadthFirstSearch(LabyrinthGraph graph) {
        // Get the start and end nodes from the graph
        String start = graph.getStartNode();
        String end = graph.getEndNode();

        // If start or end is missing, return empty path
        if (start == null || end == null) {
            return new ArrayList<>();
        }

        // Data structures for BFS
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // Initialize BFS
        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);

        boolean found = false;

        // Perform BFS
        while (!queue.isEmpty() && !found) {
            String current = queue.remove();

            // Check if we've reached the end
            if (current.equals(end)) {
                found = true;
                break;
            }

            // Explore neighbors
            for (String neighbor : graph.getAdjacencyList().get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // Reconstruct the path if found
        if (found) {
            return reconstructPath(parentMap, start, end);
        } else {
            return new ArrayList<>(); // No path found
        }
    }

    private static List<Point> reconstructPath(Map<String, String> parentMap, String start, String end) {
        LinkedList<Point> path = new LinkedList<>();
        String current = end;

        // Work backwards from end to start
        while (current != null) {
            path.addFirst(parseNodeId(current));
            current = parentMap.get(current);
        }

        return new ArrayList<>(path);
    }

    private static Point parseNodeId(String nodeId) {
        String[] parts = nodeId.split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        return new Point(x, y);
    }
}