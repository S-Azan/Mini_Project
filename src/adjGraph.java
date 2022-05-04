import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

/**
 * 
 */

/**
 * @author Azan S
 *
 */

public class adjGraph {
	
	public Graph<User> myGraph;
	public ArrayList<Vertex<User>> vertices;
	public ArrayList<Edge<User>> edges;
	
	public adjGraph() {
		//Default Constructor
	}
	
	/*
	 * Constructor
	 * @param myGraph the Adjacency List Graph
	 * @param vertices ArrayList of Users
	 * @param edges ArrayList of Edges between Users
	 */
	public adjGraph(ArrayList<Vertex<User>> vertices, ArrayList<Edge<User>> edges) {
		//this.myGraph = myGraph;
		this.vertices = vertices;
		this.edges = edges;
		
		connectUser2();
	}
	
	public ArrayList<Vertex<User>> getVertices() {
		return vertices;
	}
	
	public void setVertices(ArrayList<Vertex<User>> vertices) {
		this.vertices = vertices;
	}
	
	public ArrayList<Edge<User>> getEdges() {
		return edges;
	}
	
	public void setEdges(ArrayList<Edge<User>> edges) {
		this.edges = edges;
	}
	
	
	/*
	 * connectUser Forms connections between Users if any attributes match
	 * Adds an Edge for every common field between two Verties
	 */	
	public void connectUser1() {
		File file = new File("Users.txt");
		ArrayList<User> userList = readUsers(file);
		int cost = 0;
		int u = 0;
		
		System.out.println("Users: \n");
		while(u < userList.size()) {
			System.out.println(userList.get(u).getName());
			
			Vertex<User> newVertex = new Vertex<>(userList.get(u));
			vertices.add(newVertex);
			//vertices.add(new Vertex<User>(userList.get(u)));
			Vertex<User> vFROM = vertices.get(u);
			Vertex<User> vTO = vertices.get(u);
			
			if(vFROM.compareTo(vTO) == 0) {
			cost++;
			
			Edge<User> newEdge = new Edge<User>(cost, vFROM, vTO);
			
//			Edge<User> newEdge = new Edge<User>(cost, vertices.get(u), vertices.get(u+1));
			edges.add(newEdge);
//			edges.add(new Edge<User>(cost, vertices.get(u), vertices.get(u + 1)));
			System.out.println(u + " " + newEdge.getCost());
//			vertices.get(u).addEdge(newEdge);
			}
			u++;
		}
		
	}
	
	public void connectUser2() {
		File file = new File("Users.txt");
		ArrayList<User> userList = readUsers(file);
		int cost = 0;
		int u = 0;

		System.out.println("Users: \n");
		while(u < userList.size()) {
			System.out.println(userList.get(u).getName());
			
			Vertex<User> newVertex = new Vertex<>(userList.get(u));
			vertices.add(newVertex);
			//vertices.add(new Vertex<User>(userList.get(u)));
			
			if(vertices.get(u).compareTo(vertices.get(u)) == 0) {
			cost++;
			Vertex<User> vFROM = vertices.get(u);
			Vertex<User> vTO = vertices.get(u);
			u++;
			Edge<User> newEdge = new Edge<User>(cost, vFROM, vTO);
			
//			Edge<User> newEdge = new Edge<User>(cost, vertices.get(u), vertices.get(u+1));
			edges.add(newEdge);
//			edges.add(new Edge<User>(cost, vertices.get(u), vertices.get(u + 1)));
			System.out.println(u + " cost: " + newEdge.getCost());
//			vertices.get(u).addEdge(newEdge);
			}
		}

		}
	
	
	/*
	 * Textbook Implementation of BFS
	 */
	public static void BFS(Graph<Vertex<User>,Edge<User>> g, Vertex<User> S, Set<Vertex<User>> known, Map<Vertex<User>, Edge<User>> forest) {
		
		PositionalList<Vertex<User>> level = new LinkedPositionalList<>();
		
		known.add(s);
		level.addLast(s);
		
		while(!level.isEmpty()) {
			PositionalList<Vertex<User>> nextLevel = new LinkedPositionalList<>();
			for(Vertex<User> u : level)
				for(Edge<User> e : g.outgoingEdges(u)) {
					Vertex<User> v = g.opposite(u,e);
					
					if(!known.contains(v)) {
						known.add(v);
						forest.put(v, e);
						nextLevel.addLast(v);
					}
				}
			level = nextLevel;
		}
		
	}
	
	/*
	 * readUsers Reads users from the file
	 * @param File The userfile to read data from
	 * @return Arraylist of Users
	 */
	public static ArrayList<User> readUsers(File userfile) {
		
		ArrayList<User> userlist = new ArrayList<User>();
		try {
		Scanner sc = null;
		sc = new Scanner(userfile);	
				
		while(sc.hasNextLine()) {

			String line = sc.nextLine();
			String lineSec[] = line.split("\\s");
			
			User newUser = new User();
			
			String Name = lineSec[0];
			String Surname = lineSec[1];
			String Location = lineSec[2];
			String Age = lineSec[3];		
			String Expertise = lineSec[4];
			String Contact = lineSec[5];
			
			newUser = new User(Name, Surname, Location, Age, Expertise, Contact);
			
			userlist.add(newUser);
			
		}		
		sc.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return userlist;		
	}

}
