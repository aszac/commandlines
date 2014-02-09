package uk.ac.reading.tq011338.commandlines;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Vertex implements Comparable<Vertex> {
	public final int x;
	public final int y;
	public List<Edge> adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;

	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}
}

class Edge {
	public final Vertex target;
	public final double weight;

	public Edge(Vertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}

public class Dijkstra {

	public void computePaths(Vertex source) {
		source.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);
		Collections.reverse(path);
		return path;
	}

	public void pathfinding(int source, int goal) {
		List<Vertex> vertices = new ArrayList<Vertex>();

		for (int i = 0; i < TheGame.mapSizeX; i++) {
			for (int j = 0; j < TheGame.mapSizeY; j++) {
				if (TheGame.worldMap[i][j] == null) {
					Vertex currentVertex = new Vertex(i, j);
					vertices.add(currentVertex);
					currentVertex.adjacencies = new ArrayList<Edge>();
					if (i + 1 < TheGame.mapSizeX) {
						currentVertex.adjacencies.add(new Edge(currentVertex, 1));
					}
					if (i - 1 >= 0) {
						currentVertex.adjacencies.add(new Edge(currentVertex, 1));
					}
					if (j + 1 < TheGame.mapSizeY) {
						if ((i + 1) * TheGame.mapSizeY < TheGame.mapSizeX
								* TheGame.mapSizeY) {
							currentVertex.adjacencies.add(new Edge(
									currentVertex, 1));
						}
					}
					if (j - 1 >= 0) {
						currentVertex.adjacencies.add(new Edge(currentVertex, 1));
					}
				}
			}
		}

		computePaths(vertices.get(source));
		for (Vertex v : vertices) {
			System.out.println("Distance to " + v + ": " + v.minDistance);
			List<Vertex> path = getShortestPathTo(vertices.get(goal));
			System.out.println("Path: " + path);
		}
		System.out.println("df");
	}

}
