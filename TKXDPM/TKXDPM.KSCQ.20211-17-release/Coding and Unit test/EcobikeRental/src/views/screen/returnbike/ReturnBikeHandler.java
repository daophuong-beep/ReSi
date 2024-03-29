package views.screen.returnbike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ReturnBikeController;
import controller.TransactionController;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.transaction.TransactionHandler;

public class ReturnBikeHandler extends BaseScreenHandler implements Initializable{
	@FXML
	private AnchorPane pane;
	
	@FXML
	private TextField searchInput;
	
	@FXML
	private Button searchButton;
	
	private List dockItems;
	
	public ReturnBikeHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new ReturnBikeController());
		try {
			List docks = getBController().getAvailableDocks();
			this.dockItems = new ArrayList<>();
			for(Object o: docks) {
				Dock dock = (Dock) o;
				DockHandler handler = new DockHandler(Configs.DOCK_PATH, dock, this);
				this.dockItems.add(handler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		addDocksToGrid(this.dockItems);
		
		searchButton.setOnAction(e->{
			String key = searchInput.getText();
			if(key != null) {
				List filter = new ArrayList<>();
				dockItems.forEach(d->{
					DockHandler dh = (DockHandler) d;
					if(dh.getDock().getStationName().contains(key)) {
						filter.add(dh);
					}
				});
				addDocksToGrid(filter);
			}
		});
	}
	
	private void addDocksToGrid(List items) {
		 ArrayList list = (ArrayList)((ArrayList) items).clone();
		 GridPane grid = new GridPane();
		 grid.setMaxWidth(pane.getMaxWidth());
		 int c = 0;
		 int r = 0;
		 for(Object item: list) {
			 DockHandler dh = (DockHandler) item;
			 if(c > 1) {
				 c = 0; r++;
			 }
			 grid.add(dh.getContent(), c, r);
			 c++;
		 }
		 pane.getChildren().add(grid);
	}

	public ReturnBikeController getBController() {
		return (ReturnBikeController) super.getBController();
	}

	public void createTransactionHandler(Dock dock) throws SQLException, IOException {
		TransactionHandler transactionHandler = new TransactionHandler(this.stage, Configs.TRANSACTION_PATH, dock);
		transactionHandler.setBController(new TransactionController());
		transactionHandler.setHomeScreenHandler(homeScreenHandler);
		transactionHandler.show();
	}

	@FXML
	public void goHome(MouseEvent event) {
		homeScreenHandler.show();
	}
}
