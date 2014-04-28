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

	List<Vertex> vertices;
	List<Vertex> path;
	WorldObject[][] worldMap;

	public Dijkstra(WorldObject[][] worldMap) {
		this.worldMap = worldMap;
	}

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
		path = new ArrayList<Vertex>();
		
		// find shortest path to the target
		if (target.previous == null) {
			for (Edge e : target.adjacencies) {
				 if ( (e.target.minDistance + 1) < target.minDistance) {
					 target.previous = e.target;
					 target.minDistance = e.target.minDistance + 1;
				 }
			}
		}
		
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);
		
		// remove the source and goal from the path
		path.remove(0);
		Collections.reverse(path);
		path.remove(0);
		return path;
	}

	public void pathfinding(int sourceX, int sourceY, int goalX, int goalY) {
		initializeAllVertices();
		int goal = 0;
		int source = 0;
		for (Vertex currentVertex : vertices) {
			int currentX = currentVertex.getX();
			int currentY = currentVertex.getY();
			
			boolean isSourceOrGoal = false;
			// get source and goal index
			if (sourceX == currentX && sourceY == currentY) {
				source = vertices.indexOf(currentVertex);
				isSourceOrGoal = true;
			}
			if (goalX == currentX && goalY == currentY) {
				goal = vertices.indexOf(currentVertex);
				isSourceOrGoal = true;
			}			
			
			if (worldMap[currentX][currentY] == null || isSourceOrGoal) {
				for (Vertex vertex : vertices) {
					if (currentVertex != vertex) {
						int vertexX = vertex.getX();
						int vertexY = vertex.getY();
						if (worldMap[vertexX][vertexY] == null) {
							if ((currentX - 1) == vertexX
									&& currentY == vertexY) {
								currentVertex.adjacencies.add(new Edge(vertex,
										1));
							}
							if ((currentX + 1) == vertexX
									&& currentY == vertexY) {
								currentVertex.adjacencies.add(new Edge(vertex,
										1));
							}
							if (currentX == vertexX
									&& (currentY - 1) == vertexY) {
								currentVertex.adjacencies.add(new Edge(vertex,
										1));
							}
							if (currentX == vertexX
									&& (currentY + 1) == vertexY) {
								currentVertex.adjacencies.add(new Edge(vertex,
										1));
							}
						}
					}
				}
			}
		}

		computePaths(vertices.get(source));
		getShortestPathTo(vertices.get(goal));
	}

	public void initializeAllVertices() {
		vertices = new ArrayList<Vertex>();

		for (int i = 0; i < TheSingleplayerGame.mapSizeX; i++) {
			for (int j = 0; j < TheSingleplayerGame.mapSizeY; j++) {
				Vertex currentVertex = new Vertex(i, j);
				currentVertex.adjacencies = new ArrayList<Edge>();
				vertices.add(currentVertex);
			}
		}
	}

	public List<Vertex> getPath() {
		return path;
	}

}
