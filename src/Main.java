import java.util.ArrayList;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartGraphProperties;
import com.brunomnsilva.smartgraph.graphview.SmartGraphVertex;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 */

/**
 * @author Azan S
 *
 */
public class Main extends Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//Reading the File
		
		//Graph<User> Graph = new Graph<User>();
		ArrayList<Vertex<User>> vertices = new ArrayList<Vertex<User>>();
		ArrayList<Edge<User>> edges = new ArrayList<Edge<User>>();
		
		//Instance of my Graph
		adjGraph myGraph = new adjGraph(vertices, edges);
		
		//Instance of Smart Graph
		Graph<Vertex<User>,Edge<User>> displayGraph = new GraphEdgeList<>();
		
		//Calling the Setup method
		setupGraph(displayGraph, myGraph);
		
		//Undirected Graph hence...
		String removeEdgeArrows = "edge.label = true" + "\n" + "edge.arrow = false" + "\n" + "vertex.label=true";
		
		//Passing the property to the Smart Graph
		SmartGraphProperties properties = new SmartGraphProperties(removeEdgeArrows);
		
		//For a static graph in circular motion
		//SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
		
		//Instance of a Graph View
		SmartGraphPanel<Vertex<User>, Edge<User>> graphView = new SmartGraphPanel<>(displayGraph, properties);
		
		//Automated graph layout //
		//Toggle automatic mode to true or false
		graphView.setAutomaticLayout(true);		
		CheckBox automatic = new CheckBox("Automatic layout");
		automatic.selectedProperty().bindBidirectional(graphView.automaticLayoutProperty());
		
		//Display User Interface
		Scene scene = new Scene(graphView, 800, 600);
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("Tennis Programme");
		stage.setScene(scene);
		stage.show();
		
		//IMPORTANT - Called after scene is displayed so we can have width and height values
		graphView.init();
		
		graphView.setVertexDoubleClickAction(graphVertex -> {
			System.out.println("Vertex contains element: " + graphVertex.getUnderlyingVertex().element());
		});
		
		graphView.setEdgeDoubleClickAction(graphEdge -> {
			System.out.println("Edge contains elements: " + graphEdge.getUnderlyingEdge().element());
			//
			graphEdge.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
		});

	}
	
	private void setupGraph(Graph<Vertex<User>,Edge<User>> displayGraph, adjGraph myGraph) {
		
		for(Vertex<User> v : myGraph.getVertices()) {
			//Insert each of our nodes to the graph
			displayGraph.insertVertex(v);
		}
		
		for(Edge<User> e : myGraph.getEdges()) {
			
			//To and From vertices
			com.brunomnsilva.smartgraph.graph.Vertex<Vertex<User>> vFROM = null;
			com.brunomnsilva.smartgraph.graph.Vertex<Vertex<User>> vTO = null;
			
			for(com.brunomnsilva.smartgraph.graph.Vertex<Vertex<User>> v : displayGraph.vertices()) {
				//assigning the To and From Vertices
				if(v.element().equals(e.getFromVertex())) {
					vFROM = v;
				}else if(v.element().equals(e.getToVertex())) {
					vTO = v;
				}
			}
			
			//If from and to both exist, add an edge between them on the Graph
			if(vFROM != null && vTO != null) {
				displayGraph.insertEdge(vFROM, vTO, e);
			}
			
		}
		
	}
	
}
