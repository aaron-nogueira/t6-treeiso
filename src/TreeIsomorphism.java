import java.util.*;

public class TreeIsomorphism {
    private final Graph graph;

    public TreeIsomorphism(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph nao pode ser nulo");
        }
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public boolean isTree() {
        if (graph.V() == 0) return false;
        if (graph.E() != graph.V() - 1) return false;

        boolean[] visited = new boolean[graph.V()];
        dfs(0, visited);

        for (boolean v : visited) {
            if (!v) return false;
        }

        return true;
    }

    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, visited);
            }
        }
    }

    public String getValidationMessage() {
        if (isTree()) {
            return "Entrada valida: eh uma arvore.";
        } else {
            return "Entrada invalida: nao eh uma arvore.";
        }
    }

    public int[] getCenters() {
        int n = graph.V();
        int[] degree = new int[n];
        Queue<Integer> leaves = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            degree[i] = graph.degree(i);
            if (degree[i] <= 1) {
                leaves.add(i);
            }
        }

        int processed = leaves.size();

        while (processed < n) {
            Queue<Integer> newLeaves = new LinkedList<>();

            while (!leaves.isEmpty()) {
                int u = leaves.poll();
                for (int v : graph.adj(u)) {
                    degree[v]--;
                    if (degree[v] == 1) {
                        newLeaves.add(v);
                    }
                }
            }

            processed += newLeaves.size();
            leaves = newLeaves;
        }

        int[] centers = new int[leaves.size()];
        int i = 0;
        for (int c : leaves) {
            centers[i++] = c;
        }

        return centers;
    }

    public String getCanonicalEncoding() {
        if (!isTree()) return "";

        int[] centers = getCenters();

        if (centers.length == 1) {
            return encode(centers[0], -1);
        } else {
            String e1 = encode(centers[0], centers[1]);
            String e2 = encode(centers[1], centers[0]);
            return (e1.compareTo(e2) < 0) ? e1 : e2;
        }
    }

    private String encode(int root, int parent) {
        List<String> labels = new ArrayList<>();

        for (int neighbor : graph.adj(root)) {
            if (neighbor != parent) {
                labels.add(encode(neighbor, root));
            }
        }

        Collections.sort(labels);

        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (String s : labels) {
            sb.append(s);
        }
        sb.append(")");

        return sb.toString();
    }
}
