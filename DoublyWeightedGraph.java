import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * This creates a Doubly Weighted Bi Directional Graph and has methods to find the best route from start to finish
 * (i.e Finds the path with the highest sum of Node values within the specified time/ sum of path weight)
 *
 */

public class DoublyWeightedGraph {

	public static void main(String[] args) {

		// Test 0
		int[] value = { 5, 10, 15, 20 };
		int[] u = { 0, 1, 0 };
		int[] v = { 1, 2, 3 };
		int[] t = { 6, 7, 10 };
		int n = 4;
		int m = 3;

		DoublyWeightedGraph graph = new DoublyWeightedGraph(value, u, v, t, n, m);

		route bestRoute = graph.exploreGraph(30, 0, 0);

		System.out.println("Test 0:");
		System.out.println("SightValue: " + bestRoute.sightValue + "\nTime: " + bestRoute.time);
		System.out.println("Path: " + bestRoute.path + "\n");

		// Test 1
		int[] value1 = { 5, 5, 10 };
		int[] u1 = { 1 };
		int[] v1 = { 2 };
		int[] t1 = { 4 };
		int n1 = 3;
		int m1 = 1;

		graph = new DoublyWeightedGraph(value1, u1, v1, t1, n1, m1);

		bestRoute = graph.exploreGraph(10, 0, 0);

		System.out.println("Test 1:");
		System.out.println("SightValue: " + bestRoute.sightValue + "\nTime: " + bestRoute.time);
		System.out.println("Path: " + bestRoute.path + "\n");

		// Test 2
		int[] value2 = { 5, 5, 20, 90, 10, 20, 5, 20, 25 };
		int[] u2 = { 0, 1, 2, 3, 7, 0, 0, 6, 0, 4 };
		int[] v2 = { 1, 2, 3, 7, 8, 3, 6, 7, 4, 5 };
		int[] t2 = { 1, 4, 5, 1, 1, 2, 2, 5, 2, 6 };
		int n2 = 9;
		int m2 = 10;

		graph = new DoublyWeightedGraph(value2, u2, v2, t2, n2, m2);

		bestRoute = graph.exploreGraph(36, 0, 0);

		System.out.println("Test 2:");
		System.out.println("SightValue: " + bestRoute.sightValue + "\nTime: " + bestRoute.time);
		System.out.println("Path: " + bestRoute.path + "\n");
		
		// Test 3
		int[] value3 = { 5, 10, 15, 20 };
		int[] u3 = { 0, 1, 0 };
		int[] v3 = { 1, 2, 3 };
		int[] t3 = { 6, 7, 10 };
		int n3 = 4;
		int m3 = 3;

		graph = new DoublyWeightedGraph(value3, u3, v3, t3, n3, m3);

		bestRoute = graph.exploreGraph(30, 3, 2);

		System.out.println("Test 3:");
		System.out.println("SightValue: " + bestRoute.sightValue + "\nTime: " + bestRoute.time);
		System.out.println("Path: " + bestRoute.path + "\n");

		
		// Test 4
		int[] value4 = { 5, 10, 15, 20, 6 };
		int[] u4 = { 0, 1, 0 };
		int[] v4 = { 1, 2, 3 };
		int[] t4 = { 6, 7, 10 };
		int n4 = 5;
		int m4 = 3;

		graph = new DoublyWeightedGraph(value4, u4, v4, t4, n4, m4);

		bestRoute = graph.exploreGraph(30, 4, 2);

		System.out.println("Test 4:");
		System.out.println("SightValue: " + bestRoute.sightValue + "\nTime: " + bestRoute.time);
		System.out.println("Path: " + bestRoute.path + "\n");
		
		
		// Test 5
		bestRoute = graph.exploreGraph(30, 5, 2);

		System.out.println("Test 5:");
		System.out.println("SightValue: " + bestRoute.sightValue + "\nTime: " + bestRoute.time);
		System.out.println("Path: " + bestRoute.path + "\n");
		
	}

	private Map<Integer, graphNode> graphNodes;
	

	/**
	 * Creates a Doubly weighted Graph
	 * 
	 * @param value holds the weight of each graphNode. Index of each element in value represents a unique graphNode
	 * @param u holds the starting Node Id for each path. Index of each element in u represents a unique graphPath but, it is the same graphPath in the index of v.
	 * @param v holds the end Node Id for each path. Index of each element in v represents a unique graphPath but, it is the same graphPath in the index of u.
	 * @param t holds the weight of each graphPath. Index of each element in t represents a unique graphPath.
	 * @param n is the number of graphNodes in the graph. Should be the same size as value.
	 * @param m is the number of graphPath in the graph. Should be the same size as u and v.
	 */
	public DoublyWeightedGraph(int[] value, int[] u, int[] v, int[] t, int n, int m) {
		graphNodes = new HashMap<Integer, graphNode>();

		// Creating the Sights
		for (int i = 0; i < n; i++) {

			graphNodes.put(i, new graphNode(i, value[i], new ArrayList<graphPath>()));

		}

		// Creating the Roads (both ways) and connecting to the sights
		int path_id = 0;
		for (int i = 0; i < m; i++) {

			graphPath road = new graphPath(graphNodes.get(u[i]), graphNodes.get(v[i]), t[i], path_id++);
			graphNodes.get(u[i]).adjList.add(road);

			graphPath road_reversed = new graphPath(graphNodes.get(v[i]), graphNodes.get(u[i]), t[i], path_id++);
			graphNodes.get(v[i]).adjList.add(road_reversed);

		}

	}

	/**
	 * Initializing and starts exploring the graph recursively.
	 * 
	 * @param max_t is the maximum weight gained by taking each graphPath
	 * @param startingId is the starting graphNode in the graph
	 * @param endingId is the end graphNode in the graph
	 * @return the route that reaches the end graphNode from the start graphNode within the specified max_t
	 */
	public route exploreGraph(int max_t, int startingId, int endingId) {

		route curr_route = new route();
		graphNode start = graphNodes.get(startingId);

		if (start == null)
			return curr_route;
		
		curr_route.sightValue += start.value;
		curr_route.visited.add(startingId);
		curr_route.currentNode = start;
		curr_route.path += startingId;

		route optimalroute = exploreGraph(start, null, max_t, endingId, curr_route, curr_route);

		return optimalroute;
	}

	// Recursively following all possible paths and carrying/returning the best path
	// found
	private route exploreGraph(graphNode root, graphPath taken, int max_t, int endingId, route curr_route,
			route best) {

		// Edge case if there are no sights connected to the starting sight
		if (root.adjList.isEmpty()) {
			return best;

			// Kind of a base case? When max time is reached AND the currentNode being
			// explored is the starting node
		} else if ((max_t == curr_route.time && curr_route.currentNode.id == endingId)) {
			best = curr_route;
			return best;

			// Otherwise, explore all possible paths
		} else {

			for (int i = 0; i < root.adjList.size(); i++) {

				// Check if the current route is better than the best route found yet
				if (curr_route.currentNode.id == endingId && best.sightValue < curr_route.sightValue) {
						best = curr_route;
				} else if(curr_route.currentNode.id == endingId && best.sightValue == curr_route.sightValue && best.time > curr_route.time) {
					best = curr_route;
				}

				// Get the new the path and sight
				graphPath pathToTake = root.adjList.get(i);
				graphNode nextSight = pathToTake.to;

				// check if it is even possible to even go there or have already been there from
				// the current node
				if (curr_route.time + pathToTake.time > max_t || curr_route.pathsTaken.contains(pathToTake.id)) {
					continue;
				}

				// Create a branch/ new route to explore the next sight
				route branch = new route();

				// Check if the sight has been visited or not incrementing the sightValue
				if (curr_route.visited.contains(nextSight.id)) {
					branch.sightValue = curr_route.sightValue;
				} else {
					branch.sightValue = curr_route.sightValue + nextSight.value;
				}

				// Add/Increment Stuff
				branch.time = curr_route.time + pathToTake.time;
				branch.path = new String(curr_route.path);
				branch.addPath(nextSight.id);
				branch.visited.add(nextSight.id);
				branch.pathsTaken.add(pathToTake.id);
				branch.currentNode = nextSight;

				// Copy Data Over from Current Route
				branch.visited.addAll(curr_route.visited);
				branch.pathsTaken.addAll(curr_route.pathsTaken);

				// ~EXPLORE~
				route explored = exploreGraph(nextSight, pathToTake, max_t, endingId, branch, best);

				// Check if the explored route is better than the best route found yet
				if (explored.currentNode.id == endingId && best.sightValue < explored.sightValue) {
					best = explored;
				} else if(explored.currentNode.id == endingId && best.sightValue == explored.sightValue && best.time > explored.time) {
					best = explored;
				}
			}
		}
		return best;
	}

}

// Data structure to keep track of which path is taken, how much sight value
// there is, how much time is taken, etc.
class route {

	int sightValue;
	int time;
	Set<Integer> visited;
	Set<Integer> pathsTaken;
	graphNode currentNode;
	String path;

	public route() {
		this.sightValue = 0;
		this.time = 0;
		this.visited = new HashSet<Integer>();
		this.pathsTaken = new HashSet<Integer>();
		this.currentNode = null;
		this.path = "";
	}

	public void addPath(int id) {

		this.path +=  "->" + id;

	}
}

// Weighted graphNode - Has an id, value (weight), and connected paths
// Id is used to keep track of nodes visited
class graphNode {

	int id;
	int value;
	ArrayList<graphPath> adjList;

	public graphNode(int id, int value, ArrayList<graphPath> adjList) {
		this.id = id;
		this.value = value;
		this.adjList = adjList;
	}
}

// Weighted graphPath - Has an id, time (weight), and from/to graphNodes
// Id is used to keep track of paths taken
class graphPath {

	int id;
	int time;
	graphNode from;
	graphNode to;

	public graphPath(graphNode from, graphNode to, int time, int id) {
		this.from = from;
		this.to = to;
		this.time = time;
		this.id = id;
	}
}