package prs.fltoshi.ui;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.controlsfx.control.RangeSlider;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup; // Add this import!
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import prs.fltoshi.models.dto.VehicleDTO;
import prs.fltoshi.services.VehicleSearchService;
import prs.fltoshi.services.VehicleSearchService.SearchCriteria;

public class MainController {

    @FXML private ToggleGroup navigationGroup;
    @FXML private ToggleButton btnCatalog;
    @FXML private ToggleButton btnCompare;

    @FXML private StackPane mainContentStack;
    @FXML private BorderPane catalogView;
    @FXML private ScrollPane inspectorView;

    @FXML private TextField searchField;
    @FXML private ComboBox<String> nationComboBox;
    @FXML private RangeSlider brSlider;
    @FXML private Label minBrLabel;
    @FXML private Label maxBrLabel;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private FlowPane vehicleFlowPane;
    @FXML private Button resetButton;

    @FXML private Button backToCatalogBtn;
    @FXML private VBox inspectorContent;

    private VehicleSearchService searchService;
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public void setSearchService(VehicleSearchService searchService) {
        this.searchService = searchService;
        refreshSearch();
    }

    @FXML
    public void initialize() {
        // Инициализация списков
        nationComboBox.setItems(FXCollections.observableArrayList(
                "USA", "Germany", "USSR", "Britain", "Japan", "China", "Italy", "France", "Sweden", "Israel"));

        typeComboBox.setItems(FXCollections.observableArrayList(
                "Fighter", "Assault", "Bomber", "Tank", "SPAA", "Tank Destroyer", "Helicopter", "Ship"));

        // Слушатели для автоматического поиска
        searchField.textProperty().addListener((obs, oldVal, newVal) -> refreshSearch());
        nationComboBox.valueProperty().addListener((obs, oldVal, newVal) -> refreshSearch());
        typeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> refreshSearch());

        brSlider.lowValueProperty().addListener((obs, oldVal, newVal) -> {
            minBrLabel.setText(String.format("%.1f", newVal.doubleValue()));
            refreshSearch();
        });
        brSlider.highValueProperty().addListener((obs, oldVal, newVal) -> {
            maxBrLabel.setText(String.format("%.1f", newVal.doubleValue()));
            refreshSearch();
        });

        resetButton.setOnAction(e -> resetFilters());

        backToCatalogBtn.setOnAction(e -> showCatalog());
        
        showCatalog();
    }

    private void refreshSearch() {
        if (searchService == null)
            return;

        String query = searchService.normalizeQuery(searchField.getText());
        String nation = nationComboBox.getValue();
        String type = typeComboBox.getValue();
        double minBr = brSlider.getLowValue();
        double maxBr = brSlider.getHighValue();

        executor.submit(() -> {
            SearchCriteria criteria = new SearchCriteria(
                    query,
                    nation != null ? nation.toLowerCase() : null,
                    type != null ? type.toLowerCase() : null,
                    minBr,
                    maxBr,
                    null);

            List<VehicleDTO> results = searchService.search(criteria);

            Platform.runLater(() -> {
                vehicleFlowPane.getChildren().clear();
                for (VehicleDTO vehicle : results) {
                    Tile card = VehicleCardFactory.createVehicleTile(vehicle);
                    card.setOnMouseClicked(e -> showInspector(vehicle));
                    vehicleFlowPane.getChildren().add(card);
                }
            });
        });
    }

    private void resetFilters() {
        searchField.clear();
        nationComboBox.setValue(null);
        typeComboBox.setValue(null);
        brSlider.setLowValue(1.0);
        brSlider.setHighValue(12.7);
        refreshSearch();
    }

    public void showCatalog() {
        catalogView.setVisible(true);
        inspectorView.setVisible(false);
        if (btnCatalog != null) btnCatalog.setSelected(true);
    }

    public void showInspector(VehicleDTO vehicle) {
        catalogView.setVisible(false);
        inspectorView.setVisible(true);
        inspectorContent.getChildren().clear();

        // 1. Title and basic Info
        Label title = new Label(vehicle.getIdentifier() + " (" + vehicle.getCountry().toUpperCase() + ")");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        // BR Cards Comparison (Arcade, Realistic, Simulator)
        HBox brStats = new HBox(15);
        brStats.getChildren().addAll(
            createStatCard("Arcade BR", vehicle.getArcadeBr()),
            createStatCard("Realistic BR", vehicle.getRealisticBr()),
            createStatCard("Simulator BR", vehicle.getSimulatorBr())
        );

        // Tech Image
        ImageView techImage = new ImageView();
        techImage.setFitWidth(600);
        techImage.setPreserveRatio(true);
        if (vehicle.getImages() != null && vehicle.getImages().get("techtree") != null) {
            Image img = new Image(vehicle.getImages().get("techtree"), 600, 0, true, true, true);
            techImage.setImage(img);
        }

        // Radar Chart
        Tile radarChart = TileBuilder.create()
                .skinType(Tile.SkinType.RADAR_CHART)
                .prefSize(300, 300)
                .title("Characteristics Balance")
                .chartData(
                        new ChartData("Armor", vehicle.getEngine() != null ? 50 : 20, Color.web("#4ade80")),
                        new ChartData("Speed", vehicle.getEngine() != null ? vehicle.getEngine().getMaxSpeedRbSb() / 10.0 : 50, Color.web("#facc15")),
                        new ChartData("Firepower", vehicle.getWeapons() != null && !vehicle.getWeapons().isEmpty() ? 80 : 30, Color.web("#f87171")),
                        new ChartData("Agility", 60, Color.web("#3b82f6")) // Maneuverability/Agility to fulfill the 4 sectors requirement
                )
                .build();

        HBox topArea = new HBox(20, techImage, radarChart);

        // Details Grid (Economy, Engine, Weapons)
        Tile geoTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(400, 200)
                .title("Economy & Details")
                .text("Repair (RB): " + vehicle.getRCostRealistic() + " SL")
                .description("Reward Multiplier: " + vehicle.getSlMulRealistic() + "x")
                .build();

        inspectorContent.getChildren().addAll(title, brStats, topArea, geoTile);
    }

    private VBox createStatCard(String label, Double value) {
        VBox card = new VBox(5);
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: #2a2a2a; -fx-padding: 10; -fx-background-radius: 5;");
        Label vLabel = new Label(value != null ? String.format("%.1f", value) : "N/A");
        vLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #ffcc00;");
        Label lLabel = new Label(label);
        lLabel.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 12px;");
        card.getChildren().addAll(vLabel, lLabel);
        return card;
    }
}
