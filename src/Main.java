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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
		UserGraph myGraph = new UserGraph(vertices, edges);
		
		//Instance of Smart Graph
		Graph<Vertex<User>,Edge<User>> smartGraph = new GraphEdgeList<>();
		
		//Calling the Setup method
		setupGraph(smartGraph, myGraph);
		
		//Undirected Graph hence...
		String removeEdgeArrows = "edge.label = true" + "\n" + "edge.arrow = true" + "\n" + "vertex.label=true";
		
		//Passing the property to the Smart Graph
		SmartGraphProperties properties = new SmartGraphProperties(removeEdgeArrows);
		
		//For a static graph in circular motion
		//SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
		
		//Instance of a Graph View
		SmartGraphPanel<Vertex<User>, Edge<User>> graphView = new SmartGraphPanel<>(smartGraph, properties);
		
		//Automated graph layout //
		//Toggle automatic mode to true or false
		graphView.setAutomaticLayout(true);		
		CheckBox automatic = new CheckBox("Automatic layout");
		automatic.selectedProperty().bindBidirectional(graphView.automaticLayoutProperty());
		
		MenuBar menuBar = new MenuBar();
		TextField txtName = new TextField("Name");
		TextField txtSurname = new TextField("Surname");
		TextField txtLocation = new TextField("Location");
		TextField txtAge = new TextField("Age");
		TextField txtExpertise = new TextField("Expertise");
		Button btnAddUser = new Button("Add New User");		
		TextArea txtDisplay = new TextArea("Connections:" + "\n");
		Button btnDisplay = new Button("Display Connections");
		
		
		txtName.setMaxWidth(200);
		txtSurname.setMaxWidth(200);
		txtLocation.setMaxWidth(200);
		txtAge.setMaxWidth(200);
		txtExpertise.setMaxWidth(200);
		txtDisplay.setMaxWidth(200);

		btnAddUser.setOnAction(graphVertex ->{
			User addUser = new User(txtName.getText(), txtSurname.getText(), txtLocation.getText(), txtAge.getText(), txtExpertise.getText());
			System.out.println(addUser.getName());
			//vertices.add(0, addUser);
			Vertex<User> newUser = new Vertex<User>(addUser);			
			myGraph.getVertices().add(newUser);			
			smartGraph.insertVertex(newUser);
			
			int cost= 0;
			
			for(Vertex<User> v : vertices) {
				if(newUser.getValue().compareTo(v.getValue()) == 0) {
					cost++;
					Vertex<User> vFROM = new Vertex<>(newUser);
					
					Vertex<User> vTO = new Vertex<>(v);

					Edge<User> newEdge = new Edge<User>(cost, vFROM, vTO);
					
//					System.out.println(newEdge.getFromVertex().getValue().getName() + "->" +
//							newEdge.getToVertex().getValue().getName());
					
					edges.add(newEdge);
					smartGraph.insertEdge(v, newUser, newEdge);
				}
			}
						
			graphView.update();

			txtName.clear();
			txtSurname.clear();
			txtLocation.clear();
			txtAge.clear();
			txtExpertise.clear();

		});
		
		btnDisplay.setOnAction(e->{
			
			String displayConnection = "";
			for(Edge<User> g : myGraph.getEdges()) {
				displayConnection += g.toString() + "\n";
			}
			
			txtDisplay.appendText(displayConnection);
			
		});
		
		GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(5);
		root.setAlignment(Pos.CENTER);

		root.add(txtName, 0, 3);
		root.add(txtSurname, 0, 4);
		root.add(txtLocation, 0, 5);
		root.add(txtAge, 0, 6);
		root.add(txtExpertise, 0, 7);
		root.add(btnAddUser, 0, 9);
		root.add(btnDisplay, 0, 10);
		root.add(txtDisplay, 0, 12);
		root.add(graphView, 1, 1, 50, 50);
						
		//Display User Interface
		Scene scene = new Scene(root, 1300, 600);
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("Tennis Programme");
		stage.setScene(scene);
		stage.show();
		
		//IMPORTANT - Called after scene is displayed so we can have width and height values
		graphView.init();
		
		graphView.setVertexDoubleClickAction(graphVertex ->{
			System.out.println("Vertex " + graphVertex.getUnderlyingVertex().element());
			graphView.update();
		});
		
		graphView.setEdgeDoubleClickAction(graphEdge -> {
			System.out.println("Edge contains elements: " + graphEdge.getUnderlyingEdge().element());
			smartGraph.edges().remove(graphEdge.getUnderlyingEdge().element());
			graphView.update();
			//graphEdge.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
		});

		graphView.updateAndWait();
	}
	
	
	private void setupGraph(Graph<Vertex<User>,Edge<User>> smartGraph, UserGraph myGraph) {
		
		for(Vertex<User> v : myGraph.getVertices()) {
			//Insert each of our nodes to the graph
			smartGraph.insertVertex(v);
		}
		
		for(Edge<User> e : myGraph.getEdges()) {
			
			//To and From vertices
			com.brunomnsilva.smartgraph.graph.Vertex<Vertex<User>> vFROM = null;
			com.brunomnsilva.smartgraph.graph.Vertex<Vertex<User>> vTO = null;
			
			for(com.brunomnsilva.smartgraph.graph.Vertex<Vertex<User>> v : smartGraph.vertices()) {
				//assigning the To and From Vertices
				if(v.element().equals(e.getFromVertex())) {
					vFROM = v;
				}else if(v.element().equals(e.getToVertex())) {
					vTO = v;
				}
			}
			
			//If from and to both exist, add an edge between them on the Graph
			if(vFROM != null && vTO != null) {
				smartGraph.insertEdge(vFROM, vTO, e);
			}
			
		}
		
	}
	
}

//public void start(Stage primaryStage) throws Exception {
//// TODO Auto-generated method stub
////Reading the File
//
////Graph<User> Graph = new Graph<User>();
//ArrayList<Vertex<User>> vertices = new ArrayList<Vertex<User>>();
//ArrayList<Edge<User>> edges = new ArrayList<Edge<User>>();
//
////Instance of my Graph
//UserGraph myGraph = new UserGraph(vertices, edges);
//
////Instance of Smart Graph
//Graph<Vertex<User>,Edge<User>> smartGraph = new GraphEdgeList<>();
//
////Calling the Setup method
//setupGraph(smartGraph, myGraph);
//
////Undirected Graph hence...
//String removeEdgeArrows = "edge.label = true" + "\n" + "edge.arrow = false" + "\n" + "vertex.label=true";
//
////Passing the property to the Smart Graph
//SmartGraphProperties properties = new SmartGraphProperties(removeEdgeArrows);
//
////For a static graph in circular motion
////SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
//
////Instance of a Graph View
//SmartGraphPanel<Vertex<User>, Edge<User>> graphView = new SmartGraphPanel<>(smartGraph, properties);
//
////Automated graph layout //
////Toggle automatic mode to true or false
//graphView.setAutomaticLayout(true);		
//CheckBox automatic = new CheckBox("Automatic layout");
//automatic.selectedProperty().bindBidirectional(graphView.automaticLayoutProperty());
//
////Display User Interface
//Scene scene = new Scene(graphView, 800, 600);
//Stage stage = new Stage(StageStyle.DECORATED);
//stage.setTitle("Tennis Programme");
//stage.setScene(scene);
//stage.show();
//
////IMPORTANT - Called after scene is displayed so we can have width and height values
//graphView.init();
//
//graphView.setVertexDoubleClickAction(graphVertex -> {
//	System.out.println("Vertex contains element: " + graphVertex.getUnderlyingVertex().element());
//});
//
//graphView.setEdgeDoubleClickAction(graphEdge -> {
//	System.out.println("Edge contains elements: " + graphEdge.getUnderlyingEdge().element());
//	//
//	graphEdge.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
//});
//
//smartGraph.edges().remove(edges.get(0));

//}
