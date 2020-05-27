package de.unistuttgart.dsass2017.ex07.p1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Hasan Darwish, 3247569
 * @author Polina Jungblut, 3254837
 * @author Ina Vasileiadou, 3124938
 * 
 */
public class ShortestPath implements IShortestPath {

	// an array to set distances 
	private final double dist[];
	// an array to set the previous Nodes of each one 
	private final int prev[];
	private final IWeightedGraph graph;
	private final int startVertex;

	/**
	 * Initializes the shortest path for weighted graph <tt>graph</tt> from
	 * starting vertex <tt>startVertex</tt>. Calls the bellmanFord(graph,
	 * startVertex) method to execute the Bellman Ford algorithm.
	 * 
	 * @param graph
	 *            the weighted graph
	 * @param startVertex
	 *            the starting vertex
	 */
	public ShortestPath(IWeightedGraph graph, int startVertex) {
		this.graph = graph;
		this.startVertex = startVertex;
		this.dist = new double[this.graph.numberOfVertices()];
		this.prev = new int[this.graph.numberOfVertices()];
		bellmanFord(this.graph, this.startVertex);
	}

	@Override
	public void bellmanFord(IWeightedGraph graph, int startVertex) {
		/*
		 * 1: Initialize distances from startVertex to all other vertices as
		 * INFINITE
		 */
		for (int i = 0; i < this.graph.numberOfVertices(); i++) {			dist[i] = Double.POSITIVE_INFINITY;
		}
		dist[startVertex] = 0;

		// 2: update the distance of all edges |V| - 1 times. 
		for (int i = 0; i < this.graph.numberOfVertices(); i++) {
			for (int j = 0; j < this.graph.numberOfEdges(); j++) {
				Iterator<IEdge> it = this.graph.edgeIterator();
				while (it.hasNext()) {
					IEdge edge = it.next();
					int source = edge.getSource();
					int dest = edge.getDestination();
					double weight = edge.getWeight();
					if (dist[source] != Double.POSITIVE_INFINITY && dist[source] + weight < dist[dest]) {
						dist[dest] = dist[source] + weight;
						prev[dest] = source;
					}
				}
			}
		}
	}

	@Override
	public boolean hasNegativeCycle() {
		boolean result = false;
		Iterator<IEdge> it = this.graph.edgeIterator();
		// go through all the edges to check for negative cycle
		for (int i = 0; i < this.graph.numberOfEdges(); i++) {
			while (it.hasNext()) {
				IEdge edge = it.next();
				int source = edge.getSource();
				int dest = edge.getDestination();
				double weight = edge.getWeight();
				if (dist[source] != Double.POSITIVE_INFINITY && dist[source] + weight < dist[dest]) {
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public double distanceTo(int destination) {
		if (hasNegativeCycle()) {
			throw new IllegalStateException();
		} else {
			return dist[destination];
		}
	}

	@Override
	public boolean existsPathTo(int destination) {
		if (distanceTo(destination) == Double.POSITIVE_INFINITY) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Iterator<IEdge> pathTo(int destination) {
		// an arrayList to save the required edges to destination 
		ArrayList<IEdge> edgesList = new ArrayList<>();
		if (hasNegativeCycle()) {
			throw new IllegalStateException();
		} else if (!existsPathTo(destination)) {
			return null;
		} else {
			int tmpVertex = destination;
			while (tmpVertex != startVertex) {
				Iterator<IEdge> it = this.graph.edgeIterator();
				while (it.hasNext()) {
					IEdge edge = it.next();
					if (edge.getDestination() == tmpVertex && edge.getSource() == prev[tmpVertex]) {
						edgesList.add(edge);
					}
				}
				tmpVertex = prev[tmpVertex];
			}
			return edgesList.iterator();
		}
	}
}