package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphBasics {

    static class Node {
        String node;
        String parent;

        Node(String node, String parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    static class BipartiteNode {
        String node;
        String color;

        BipartiteNode(String node, String color) {
            this.node = node;
            this.color = color;
        }
    }

    public static void dfsItr(Map<String, List<String>> adjListMap, String start) {
        Map<String, Boolean> visitedMap = new HashMap<>();
        Stack<String> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            String node = stack.pop();
            if (visitedMap.containsKey(node) && visitedMap.get(node)) {
                continue;
            } else {
                visitedMap.put(node, true);
            }
            System.out.print(node + " ");
            if (adjListMap.containsKey(node)) {
                for (String neighbour : adjListMap.get(node)) {
                    stack.push(neighbour);
                }
            }
        }
        System.out.println();
    }

    public static void dfsRec(Map<String, List<String>> adjListMap, String start) {
        System.out.print(start + " ");
        if (adjListMap.containsKey(start)) {
            for (String neighbour : adjListMap.get(start)) {
                dfsRec(adjListMap, neighbour);
            }
        }
    }

    public static boolean hasPathDfsItr(Map<String, List<String>> adjListMap, String start, String end) {
        boolean hasPath = false;
        Stack<String> stack = new Stack<>();
        System.out.println();
        stack.push(start);
        while (!stack.isEmpty()) {
            String node = stack.pop();
            System.out.print(node + " ");
            if (node.equals(end)) {
                return true;
            }
            if (adjListMap.containsKey(node)) {
                for (String neighbour : adjListMap.get(node)) {
                    stack.push(neighbour);
                }
            }
        }
        System.out.println();
        return hasPath;
    }

    public static boolean hasPathDfsRec(Map<String, List<String>> adjListMap, String start, String end) {
        if (start.equals(end)) {
            return true;
        }
        if (adjListMap.containsKey(start)) {
            for (String neighbour : adjListMap.get(start)) {
                if (hasPathDfsRec(adjListMap, neighbour, end)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void bfs(Map<String, List<String>> adjListMap, String start) {
        System.out.println();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            System.out.print(node + " ");
            if (adjListMap.containsKey(node)) {
                queue.addAll(adjListMap.get(node));
            }
        }
    }

    public static boolean hasPathBfs(Map<String, List<String>> adjListMap, String start, String end) {
        System.out.println();
        Set<String> visitedSet = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            if (visitedSet.contains(node)) {
                continue;
            } else {
                visitedSet.add(node);
            }
            System.out.print(node + " ");
            if (node.equals(end)) {
                return true;
            }
            if (adjListMap.containsKey(node)) {
                queue.addAll(adjListMap.get(node));
            }
        }
        return false;
    }

    public static boolean isCycleBFS(Map<String, List<String>> adjListMap) {
        Set<String> visited = new HashSet<>();
        for (String s : adjListMap.keySet()) {
            if (!visited.contains(s)) {
                if (isCycle(s, visited, adjListMap)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCycle(String start, Set<String> visited, Map<String, List<String>> adjListMap) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, ""));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited.add(node.node);
            if (adjListMap.containsKey(node.node)) {
                for (String s : adjListMap.get(node.node)) {
                    if (!visited.contains(s)) {
                        queue.add(new Node(s, node.node));
                    } else if (!node.parent.equals(s)) { // as adjacent node is mark as visited but this is not parent of current node so there is already a path exist hence there is a cycle
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isCycleDFS(Map<String, List<String>> adjListMap) {
        Set<String> visited = new HashSet<>();
        for (String s : adjListMap.keySet()) {
            if (!visited.contains(s)) {
                if (isCycle1(s, visited, adjListMap)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCycle1(String start, Set<String> visited, Map<String, List<String>> adjListMap) {
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(start, ""));
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            visited.add(node.node);
            for (String s : adjListMap.get(node.node)) {
                if (!visited.contains(s)) {
                    stack.add(new Node(s, node.node));
                } else if (!node.parent.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isBipartiteBFS(Map<String, List<String>> adjListMap) {
        Map<String, Integer> visited = new HashMap<>();
        for (String s : adjListMap.keySet()) {
            if (!visited.containsKey(s)) {
                return isBipartiteBFSUtil(s, visited, adjListMap);
            }
        }
        return true;
    }

    public static boolean isBipartiteBFSUtil(String node, Map<String, Integer> visited, Map<String, List<String>> adjListMap) {
        Queue<String> queue = new LinkedList<>();
        queue.add(node);
        visited.put(node, 1);
        while (!queue.isEmpty()) {
            String n = queue.poll();
            if (adjListMap.containsKey(n)) {
                for (String s : adjListMap.get(n)) {
                    if (!visited.containsKey(s)) {
                        queue.add(s);
                        visited.put(s, 1 - visited.get(n));
                    } else {
                        if (visited.get(s).equals(visited.get(n))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Map<String, List<String>> adjListMap1 = new HashMap<>();
        adjListMap1.put("a", Arrays.asList("b", "c"));
        adjListMap1.put("b", Arrays.asList("d"));
        adjListMap1.put("c", Arrays.asList("e"));
        adjListMap1.put("d", Arrays.asList("f"));

        dfsItr(adjListMap1, "a");
        dfsRec(adjListMap1, "a");
        bfs(adjListMap1, "a");

        Map<String, List<String>> adjListMap2 = new HashMap<>();
        adjListMap2.put("f", Arrays.asList("g", "i"));
        adjListMap2.put("g", Arrays.asList("h"));
        adjListMap2.put("h", new ArrayList<>());
        adjListMap2.put("i", Arrays.asList("g", "k"));
        adjListMap2.put("j", Arrays.asList("i"));
        adjListMap2.put("k", new ArrayList<>());

        Map<String, List<String>> adjListMapUnDia3 = new HashMap<>();
        adjListMapUnDia3.put("a", Arrays.asList("c", "b"));
        adjListMapUnDia3.put("b", Arrays.asList("a", "c", "d"));
        adjListMapUnDia3.put("c", Arrays.asList("a", "b"));
        adjListMapUnDia3.put("d", Arrays.asList("b"));

        System.out.println(hasPathDfsItr(adjListMap2, "f", "k"));
        System.out.println(hasPathDfsRec(adjListMap2, "f", "k"));
        System.out.println(hasPathBfs(adjListMap2, "f", "k"));
        System.out.println(isCycleBFS(adjListMapUnDia3));
        System.out.println(isCycleDFS(adjListMapUnDia3));
        System.out.println(isBipartiteBFS(adjListMapUnDia3));
    }
}
