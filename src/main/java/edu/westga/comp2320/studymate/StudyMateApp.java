package edu.westga.comp2320.studymate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application launcher for StudyMate.
 */
public class StudyMateApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/StudyMateView.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("StudyMate");
        stage.show();
    }

    /**
     * Launches the StudyMate application.
     * @param args command line arguments
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        launch();
    }
}