package views.screen.home;

import controller.HomeController;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.stationinfo.StationScreenHandler;

import java.io.IOException;
import java.util.logging.Logger;

public class SearchStationScreenHandler extends BaseScreenHandler {
    private static Logger LOGGER = Utils.getLogger(SearchStationScreenHandler.class.getName());

    private Station station;

    @FXML
    private AnchorPane pane;

    public HomeController getBController() {
        return (HomeController) super.getBController();
    }

    public SearchStationScreenHandler(Stage stage, String screenPath, Station station) throws IOException {
        super(stage, screenPath);
        this.station = station;
        StationHandlder stationHandlder = new StationHandlder(Configs.STATION_HOME ,station);
        pane.getChildren().add(stationHandlder.getContent());

    }
}
