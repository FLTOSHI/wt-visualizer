package prs.fltoshi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import prs.fltoshi.repositories.impl.JooqVehicleRepository;
import prs.fltoshi.services.VehicleSearchService;
import prs.fltoshi.ui.MainController;

public class Main extends Application {

    private static DSLContext dsl;
    private static VehicleSearchService searchService;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Инициализация DB и сервиса
        initializeBackend();

        // 3. Загрузка FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/prs/fltoshi/views/MainLayout.fxml"));
        Parent root = loader.load();

        // 4. Настройка контроллера
        MainController controller = loader.getController();
        controller.setSearchService(searchService);

        // 5. Показ окна
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/prs/fltoshi/style.css").toExternalForm());
        
        primaryStage.setTitle("War Thunder Data Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeBackend() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/wt_db");
        config.setUsername("postgres");
        config.setPassword("root");
        config.setMaximumPoolSize(5);

        HikariDataSource dataSource = new HikariDataSource(config);
        dsl = DSL.using(dataSource, SQLDialect.POSTGRES);
        
        JooqVehicleRepository repository = new JooqVehicleRepository(dsl);
        searchService = new VehicleSearchService(repository);
    }

    public static void main(String[] args) {
        launch(args);
    }
}