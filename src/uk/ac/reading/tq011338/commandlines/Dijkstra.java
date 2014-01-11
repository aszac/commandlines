package uk.ac.reading.tq011338.commandlines;

public class Dijkstra {

	private int matrix_size = TheGame.mapSizeX * TheGame.mapSizeY;
	private int[][] adjacency_matrix = new int[matrix_size][matrix_size];
	private int[] distance = new int[matrix_size]; // distance from source to
													// goal
	private int[] previous = new int[matrix_size]; // previous node from optimal
													// path to source
	private Node[] nodeSet = new Node[matrix_size];
	private int set_size = matrix_size;

	public Dijkstra(WorldObject[][] worldMap) {
		buildAdjacencyMatrix(worldMap);
	}

	public void pathfinding(Node source, Node goal) {
		for (int v = 0; v < matrix_size; v++) {
			distance[v] = 100;
			nodeSet[v] = new Node(v);
		}

		distance[source.getNumber()] = 100;

		while (set_size == 0) {
			int u = findMinimum();
			nodeSet[u] = null;
			set_size--;

			if (distance[u] == 100) {
				break;
			}

			for (int n = 0; n < matrix_size; n++) {
				if (adjacency_matrix[u][n] == 1) {
					int alt = distance[u] + 1;
					if (alt < distance[n]) {
						distance[n] = alt;
						previous[n] = u;

					}
				}
			}

		}
	}

	public int findMinimum() {
		int minimum = 100;
		for (int m = 0; m < matrix_size; m++) {
			if (distance[m] < distance[minimum]) {
				minimum = m;
			}
		}
		return minimum;
	}

	public void buildAdjacencyMatrix(WorldObject[][] worldMap) {
		initializeMatrixToZero();
		for (int i = 0; i < TheGame.mapSizeX; i++) {
			for (int j = 0; j < TheGame.mapSizeY; j++) {
				if (worldMap[i][j] != null) {
					break;
				}
				if (i + 1 <= TheGame.mapSizeX) {
					adjacency_matrix[i * TheGame.mapSizeY + j][i
							* TheGame.mapSizeY + j + 1] = 1;
				}
				if (i - 1 >= 0) {
					adjacency_matrix[i * TheGame.mapSizeY + j][i
							* TheGame.mapSizeY + j - 1] = 1;
				}

				if (j + 1 <= TheGame.mapSizeY) {
					adjacency_matrix[i * TheGame.mapSizeY + j][(i + 1)
							* TheGame.mapSizeY + j] = 1;
				}
				if (j - 1 >= 0) {
					adjacency_matrix[i * TheGame.mapSizeY + j][(i - 1)
							* TheGame.mapSizeY + j] = 1;
				}

			}
		}
	}

	public void initializeMatrixToZero() {
		for (int i = 0; i < matrix_size; i++) {
			for (int j = 0; j < matrix_size; j++) {
				adjacency_matrix[i][j] = 0;
			}
		}
	}

	public class Node {
		private int number;

		public Node(int number) {
			super();
			this.number = number;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

	}
}
