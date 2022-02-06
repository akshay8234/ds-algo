package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class BFS {

    static class Node {
        int first;
        int second;

        public Node(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Pair {
        int v;
        int weight;

        public Pair(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    static class Vertex implements Comparator<Vertex> {
        int v;
        int weight;

        public Vertex() {
        }

        public Vertex(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compare(Vertex v1, Vertex v2) {
            if (v1.weight < v2.weight) {
                return -1;
            } else if (v1.weight > v2.weight) {
                return 1;
            }
            return 0;
        }
    }

    public List<Integer> bfs(int v, List<List<Integer>> adjList) {
        List<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[v + 1];

        for (int i = 1; i <= v; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
                    Integer node = queue.poll();
                    bfs.add(node);

                    for (Integer vertex : adjList.get(node)) {
                        if (!visited[vertex]) {
                            visited[vertex] = true;
                            queue.add(vertex);
                        }
                    }
                }
            }
        }
        return bfs;
    }

    public void dfsUtil(int node, boolean[] visited, List<ArrayList<Integer>> adjList, List<Integer> dfs) {
        dfs.add(node);
        visited[node] = true;
        for (Integer integer : adjList.get(node)) {
            if (!visited[integer]) {
                dfsUtil(integer, visited, adjList, dfs);
            }
        }
    }

    public List<Integer> dfs(int v, List<ArrayList<Integer>> adjList) {
        List<Integer> dfs = new ArrayList<>();
        boolean[] visited = new boolean[v + 1];
        for (int i = 1; i <= v; i++) {
            if (!visited[i]) {
                dfsUtil(i, visited, adjList, dfs);
            }
        }
        return dfs;
    }

    public boolean isCycleBFS(int v, List<ArrayList<Integer>> adjList) {
        boolean[] visited = new boolean[v + 1];

        for (int i = 1; i <= v; i++) {
            if (!visited[i]) {
                if (checkForCycle(adjList, i, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkForCycle(List<ArrayList<Integer>> adjList, int s, boolean[] visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(s, -1));
        visited[s] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i : adjList.get(s)) {
                if (!visited[i]) {
                    queue.add(new Node(i, node.first));
                    visited[i] = true;
                } else if (node.second != i) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCycleDFS(int v, List<ArrayList<Integer>> adjList) {
        boolean[] visited = new boolean[v + 1];

        for (int i = 1; i <= v; i++) {
            if (!visited[i]) {
                if (checkForCycle(adjList, i, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkForCycle(int node, int parent, boolean[] visited, List<ArrayList<Integer>> adjList) {
        visited[node] = true;
        for (Integer integer : adjList.get(node)) {
            if (!visited[integer]) {
                if (checkForCycle(integer, node, visited, adjList)) {
                    return true;
                } else if (integer != parent) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkBipartite(int n, List<ArrayList<Integer>> adjList) {
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!bfsCheck(adjList, i, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean bfsCheck(List<ArrayList<Integer>> adjList, int node, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        color[node] = 1;

        while (!queue.isEmpty()) {
            int i = queue.poll();
            for (Integer integer : adjList.get(node)) {
                if (color[integer] == -1) {
                    color[integer] = 1 - color[i];
                    queue.add(integer);
                } else if (color[integer] == color[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkBipartite(List<ArrayList<Integer>> adjList, int n) {
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (bipartiteDFSCheck(adjList, i, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean bipartiteDFSCheck(List<ArrayList<Integer>> adjList, int node, int[] color) {
        if (color[node] == -1) {
            color[node] = 1;
        }
        for (Integer i : adjList.get(node)) {
            if (color[i] == -1) {
                color[i] = 1 - color[node];
                if (!bipartiteDFSCheck(adjList, i, color)) {
                    return false;
                }
            } else if (color[i] == color[node]) {
                return false;
            }
        }
        return true;
    }

    public boolean isCyclicDFS(List<ArrayList<Integer>> adjList, int n) {
        int[] visited = new int[n];
        int[] dfsVisited = new int[n];

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                if (checkCycleDFS(i, adjList, visited, dfsVisited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCycleDFS(int node, List<ArrayList<Integer>> adjList, int[] visited, int[] dfsVisited) {
        visited[node] = 1;
        dfsVisited[node] = 1;

        for (Integer i : adjList.get(node)) {
            if (visited[i] == 0) {
                if (checkCycleDFS(i, adjList, visited, dfsVisited)){
                    return true;
                }
            } else if (dfsVisited[node] == 1) {
                return true;
            }
        }
        dfsVisited[node] = 0;
        return false;
    }

    public void findTopologicalSortDFS(int node, int[] visited, List<ArrayList<Integer>> adjList, Stack<Integer> stack) {
        visited[node] = 1;
        for (int i : adjList.get(node)) {
            if (visited[i] == 0) {
                findTopologicalSortDFS(i, visited, adjList, stack);
            }
        }
        stack.push(node);
    }

    public int[] topologicalSortDFS(int n, List<ArrayList<Integer>> adjList) {
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[n];

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                findTopologicalSortDFS(i, visited, adjList, stack);
            }
        }

        int[] topo = new int[n];
        int index = 0;
        while (!stack.isEmpty()) {
            topo[index++] = stack.pop();
        }

        return topo;
    }

    public static int[] topologicalSortBFS(int n, List<ArrayList<Integer>> adjList) {
        int[] topo = new int[n];
        int[] indexDegree = new int[n];

        for (int i = 0; i < n; i++) {
            for (Integer integer : adjList.get(i)) {
                indexDegree[integer]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indexDegree[i] == 0) {
                queue.add(i);
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            topo[index++] = node;

            for (Integer i : adjList.get(node)) {
                indexDegree[i]--;
                if (indexDegree[i] == 0) {
                    queue.add(i);
                }
            }
        }

        return topo;
    }

    public boolean isCyclicKahn(int n, List<ArrayList<Integer>> adjList) {
        int[] topo = new int[n];
        int[] indexDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (Integer integer : adjList.get(i)) {
                indexDegree[integer]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indexDegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            count++;
            for (Integer i : adjList.get(node)) {
                indexDegree[i]++;
                if (indexDegree[i] == 0) {
                    queue.add(i);
                }
            }
        }
        if (count == n) {
            return false;
        }

        return true;
    }

    public void shortestPathBFS(List<ArrayList<Integer>> adjList, int n, int source) {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);

        Queue<Integer> queue = new LinkedList<>();

        distance[source] = 0;
        queue.add(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer i : adjList.get(node)) {
                if (distance[node] + 1 < distance[i]) {
                    distance[i] = distance[node] + 1;
                    queue.add(i);
                }
            }
        }

        System.out.println(Arrays.toString(distance));
    }

    public void topologocalSortUtil(int node, boolean[] visited, Stack<Integer> stack, List<ArrayList<Pair>> adjList) {
        visited[node] = true;
        for (Pair pair : adjList.get(node)) {
            if (!visited[pair.v]) {
                topologocalSortUtil(pair.v, visited, stack, adjList);
            }
        }
        stack.add(node);
    }

    public void shortestPath(int s, int n, List<ArrayList<Pair>> adjList) {
        Stack<Integer> stack = new Stack<>();
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                topologocalSortUtil(i, visited, stack, adjList);
            }
        }

        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[s] = 0;

        while (!stack.isEmpty()) {
            Integer node = stack.pop();
            if (distance[node] != Integer.MAX_VALUE) {
                for (Pair pair : adjList.get(node)) {
                    if (distance[node] + pair.weight < distance[pair.v]) {
                        distance[pair.v] = distance[node] + pair.weight;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.print("Inf ");
            } else {
                System.out.print(distance[i] + " ");
            }
        }
    }

    public void shortestPathDijkstra(int s, List<ArrayList<Vertex>> adjList, int n) {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.add(new Vertex(s, 0));

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            for (Vertex i : adjList.get(vertex.v)) {
                if (distance[vertex.v] + i.weight < distance[i.v]) {
                    queue.add(new Vertex(i.v, distance[i.v]));
                }
            }
        }

        System.out.println(Arrays.toString(distance));
    }

    public void primsAlgo(List<ArrayList<Vertex>> adjList, int n) {
        int[] key = new int[n];
        int[] parent = new int[n];
        boolean[] mst = new boolean[n];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        key[0] = 0;
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Vertex(0, key[0]));
        for (int i = 0; i < n - 1; i++) {
            int u = Objects.requireNonNull(priorityQueue.poll()).v;
            mst[u] = true;
            for (Vertex vertex : adjList.get(u)) {
                if (!mst[vertex.v] && vertex.weight < key[vertex.v]) {
                    parent[vertex.v] = u;
                    key[vertex.v] = vertex.weight;
                    priorityQueue.add(new Vertex(vertex.v, key[vertex.v]));
                }
            }
        }
//        for (int i = 0; i < n - 1; i++) {
//            int minimum = Integer.MAX_VALUE;
//            int u = 0;
//            for (int v = 0; i < n; i++) {
//                if (!mst[v] && key[v] < minimum) {
//                    minimum = key[u];
//                    u = v;
//                }
//            }
//            mst[u] = true;
//            for (Pair pair : adjList.get(u)) {
//                if (mst[pair.v] && pair.weight < key[pair.v]) {
//                    parent[pair.v] = u;
//                    key[pair.v] = pair.weight;
//                }
//            }
//        }
        for (int i = 1; i < n; i++) {
            System.out.println(parent[i] + "-" + i);
        }
    }
}
