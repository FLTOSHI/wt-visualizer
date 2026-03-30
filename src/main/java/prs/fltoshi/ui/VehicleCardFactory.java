package prs.fltoshi.ui;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;
import prs.fltoshi.models.dto.VehicleDTO;



public class VehicleCardFactory {

    public static Tile createVehicleTile(VehicleDTO vehicle) {
        // Иконка типа техники (пример маппинга)
        String iconCode = getIconForType(vehicle.getVehicleType());
        FontIcon typeIcon = new FontIcon(iconCode);
        typeIcon.setIconSize(24);
        typeIcon.setIconColor(Color.WHITE);

        // Бейдж с БР
        Label brBadge = new Label(String.format("%.1f", vehicle.getRealisticBr()));
        brBadge.getStyleClass().add("br-badge");
        brBadge.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 2 6; -fx-text-fill: #ffcc00; -fx-font-weight: bold; -fx-background-radius: 4;");

        // Фоновое изображение (загружается асинхронно)
        ImageView backgroundView = new ImageView();
        backgroundView.setFitWidth(250);
        backgroundView.setFitHeight(150);
        backgroundView.setPreserveRatio(true);

        if (vehicle.getImages() != null && vehicle.getImages().get("image") != null) {
            String imageUrl = vehicle.getImages().get("image");
            Image img = new Image(imageUrl, 250, 150, true, true, true); // true at the end = backgroundLoading
            backgroundView.setImage(img);
        }

        // Контейнер для кастомизации GraphicTile
        StackPane graphicContainer = new StackPane();
        graphicContainer.getChildren().add(backgroundView);

        // Добавляем иконку типа
        StackPane.setAlignment(typeIcon, Pos.TOP_LEFT);
        StackPane.setMargin(typeIcon, new Insets(5));
        graphicContainer.getChildren().add(typeIcon);

        // Бейдж БР
        StackPane.setAlignment(brBadge, Pos.TOP_RIGHT);
        StackPane.setMargin(brBadge, new Insets(5));
        graphicContainer.getChildren().add(brBadge);

        // Название машины и страна
        Label nameLabel = new Label(vehicle.getIdentifier());
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        Label countryLabel = new Label(vehicle.getCountry() != null ? vehicle.getCountry().toUpperCase() : "");
        countryLabel.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 10px;");

        javafx.scene.layout.VBox textContainer = new javafx.scene.layout.VBox(2, nameLabel, countryLabel);
        textContainer.setAlignment(Pos.CENTER_LEFT);
        textContainer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 5;");

        StackPane.setAlignment(textContainer, Pos.BOTTOM_CENTER);
        graphicContainer.getChildren().add(textContainer);

        Color borderColor = Color.TRANSPARENT;
        if (vehicle.isPremium()) {
            borderColor = Color.GOLD;
        } else if (vehicle.isSquadron()) {
            borderColor = Color.web("#4ade80"); // green
        }

        return TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(250, 180)
                .graphic(graphicContainer)
                .roundedCorners(true)
                .backgroundColor(Color.web("#1e1e1e"))
                .borderColor(borderColor)
                .borderWidth(borderColor == Color.TRANSPARENT ? 0 : 2)
                .build();
    }

    private static String getIconForType(String type) {
        if (type == null)
            return "fas-question";
        return switch (type.toLowerCase()) {
            case "fighter", "assault", "bomber", "strike_aircraft" -> "fas-plane";
            case "tank", "heavy_tank", "medium_tank", "light_tank" -> "fas-truck-monster"; // FontAwesome 5 doesn't have
                                                                                           // a tank icon in standard
                                                                                           // packs usually, using
                                                                                           // truck-monster or similar
            case "tank_destroyer", "spaa" -> "fas-shield-alt";
            case "helicopter" -> "fas-helicopter";
            case "ship", "boat", "cruiser", "frigate" -> "fas-ship";
            default -> "fas-question";
        };
    }
}
