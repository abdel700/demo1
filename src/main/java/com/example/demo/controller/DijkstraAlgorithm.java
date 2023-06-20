package com.example.demo.controller;
import java.util.*;

public class DijkstraAlgorithm {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> visitedNodes;
    private Set<Vertex> unvisitedNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Double> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on it
        this.nodes = new ArrayList<>(graph.getVertices());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public void execute(Vertex source) {
        visitedNodes = new HashSet<>();
        unvisitedNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0.0);
        unvisitedNodes.add(source);

        while (unvisitedNodes.size() > 0) {
            Vertex node = getMinimum(unvisitedNodes);
            visitedNodes.add(node);
            unvisitedNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unvisitedNodes.add(target);
            }
        }

    }

    private double getDistance(Vertex node, Vertex target) {
        double lat1 = node.getLatitude();
        double lon1 = node.getLongitude();
        double lat2 = target.getLatitude();
        double lon2 = target.getLongitude();

        double R = 6371; // Radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // Distance in km
        return distance;
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !visitedNodes.contains(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    public double getShortestDistance(Vertex destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        // create the graph les sommets
        Vertex v1 = new Vertex(1, 48.726041, 2.365231);
        Vertex v2 = new Vertex(2, 33.370069, -7.583809);
//        Vertex v3 = new Vertex(3, 34.0522, -118.2437);
//        Vertex v4 = new Vertex(4, 40.7128, -74.0060);
//        Vertex v5 = new Vertex(5, 51.5074, -0.1278);
//        Vertex v6 = new Vertex(6, 48.8566, 2.3522);

        List<Vertex> vertices = new ArrayList<>();
        vertices.add(v1);
        vertices.add(v2);
//        vertices.add(v3);
//        vertices.add(v4);
//        vertices.add(v5);
//        vertices.add(v6);

        //les arrets
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, v1, v2));
//        edges.add(new Edge(2, v2, v3));
//        edges.add(new Edge(3, v3, v4));
//        edges.add(new Edge(4, v4, v5));
//        edges.add(new Edge(5, v5, v6));
//        edges.add(new Edge(6, v6, v1));

        Graph graph = new Graph(vertices, edges);

        // run Dijkstra's algorithm on the graph
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(v1);

        // get the shortest path between two nodes
        LinkedList<Vertex> path = dijkstra.getPath(v2);

        // print the path
        for (Vertex vertex : path) {
            System.out.println(vertex.getId());
        }

        double dis = dijkstra.getShortestDistance(v2);

        System.out.println(dis);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            double dis = dijkstra.getShortestDistance(v2);
            public void run() {

                if (dis > 0) {
                    dis-=200;
                    System.out.println("Number: " + dis);
                } else {
                    System.out.println("the aiplane has arrived.");
                    timer.cancel();
                }
            }
        };
        long delay = 0;
        long period = 2000; // 2 seconds
        timer.scheduleAtFixedRate(task, delay, period);

    }

// inner classes

    public static class Vertex {
        private final int id;
        private final double latitude;
        private final double longitude;

        public Vertex(int id, double latitude, double longitude) {
            this.id = id;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public int getId() {
            return id;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Vertex)) return false;
            Vertex vertex = (Vertex) o;
            return id == vertex.id &&
                    Double.compare(vertex.latitude, latitude) == 0 &&
                    Double.compare(vertex.longitude, longitude) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, latitude, longitude);
        }
    }

    public static class Edge {
        private final int id;
        private final Vertex source;
        private final Vertex destination;

        public Edge(int id, Vertex source, Vertex destination) {
            this.id = id;
            this.source = source;
            this.destination = destination;
        }

        public int getId() {
            return id;
        }

        public Vertex getSource() {
            return source;
        }

        public Vertex getDestination() {
            return destination;
        }
    }

    public static class Graph {
        private final List<Vertex> vertices;
        private final List<Edge> edges;

        public Graph(List<Vertex> vertices, List<Edge> edges) {
            this.vertices = vertices;
            this.edges = edges;
        }

        public List<Vertex> getVertices() {
            return vertices;
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }
}
