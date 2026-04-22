package edu.westga.comp2320.studymate;

import edu.westga.comp2320.studymate.model.StudySession;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Handles GUI events for StudyMate application.
 */
public class Main extends Application {

    @FXML
    private TextField dayField;

    @FXML
    private TextField subjectField;

    @FXML
    private TextField taskField;

    @FXML
    private ListView<StudySession> listView;

    @FXML
    private Label dayError;

    @FXML
    private Label subjectError;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/StudyMateView.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("StudyMate");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the StudyMate application.
     *
     * @param args command line arguments
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        launch();
    }


    @FXML
    private void handleAdd() {
        String day = this.dayField.getText().trim();
        String subject = this.subjectField.getText().trim();
        String task = this.taskField.getText().trim();

        boolean valid = true;

        if (!(day.equalsIgnoreCase("M") || day.equalsIgnoreCase("T") ||
                day.equalsIgnoreCase("W") || day.equalsIgnoreCase("R") ||
                day.equalsIgnoreCase("F"))) {

            this.dayError.setText("must be M, T, W, R, or F");
            valid = false;
        } else {
            this.dayError.setText("");
        }

        if (subject.isEmpty()) {
            this.subjectError.setText("required");
            valid = false;
        } else {
            this.subjectError.setText("");
        }

        if (!valid) {
            return;
        }

        StudySession session = new StudySession(day, subject, task);
        this.listView.getItems().add(session);

        this.listView.getSelectionModel().selectLast();

        this.dayField.clear();
        this.subjectField.clear();
        this.taskField.clear();
    }

    /**
     * Handles deleting a study session.
     */
    @FXML
    private void handleDelete() {
        StudySession selected = this.listView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("No selection");
            alert.setContentText("Please select a study session to delete.");
            alert.showAndWait();
            return;
        }

        this.listView.getItems().remove(selected);

        this.dayField.clear();
        this.subjectField.clear();
        this.taskField.clear();
        this.dayError.setText("");
        this.subjectError.setText("");
    }

    /**
     * Initializes GUI behavior.
     */
    @FXML
    public void initialize() {

        this.listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                this.dayField.setText(newVal.getDay().substring(0, 1));
                this.subjectField.setText(newVal.getSubject());
                this.taskField.setText(newVal.getTask());
            }
        });

        this.dayField.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(javafx.beans.value.ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.isEmpty()) {
                    Main.this.dayError.setText("");
                    return;
                }

                if (!(newValue.equalsIgnoreCase("M") || newValue.equalsIgnoreCase("T") ||
                        newValue.equalsIgnoreCase("W") || newValue.equalsIgnoreCase("R") ||
                        newValue.equalsIgnoreCase("F"))) {

                    Main.this.dayError.setText("must be M, T, W, R, or F");
                } else {
                    Main.this.dayError.setText("");
                }
            }
        });
    }
}