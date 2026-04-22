package edu.westga.comp2320.studymate;

import edu.westga.comp2320.studymate.model.StudySession;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudyMateView.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("StudyMate");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    // ================= ADD =================
    @FXML
    private void handleAdd() {
        String day = dayField.getText().trim();
        String subject = subjectField.getText().trim();
        String task = taskField.getText().trim();

        boolean valid = true;

        // Day validation
        if (!(day.equalsIgnoreCase("M") || day.equalsIgnoreCase("T")
                || day.equalsIgnoreCase("W") || day.equalsIgnoreCase("R")
                || day.equalsIgnoreCase("F"))) {

            dayError.setText("must be M, T, W, R, or F");
            valid = false;
        } else {
            dayError.setText("");
        }

        // Subject validation
        if (subject.isEmpty()) {
            subjectError.setText("required");
            valid = false;
        } else {
            subjectError.setText("");
        }

        if (!valid) {
            return; // 🚨 STOP if invalid
        }

        StudySession session = new StudySession(day, subject, task);
        listView.getItems().add(session);

        // Select new item
        listView.getSelectionModel().selectLast();

        // Clear fields
        dayField.clear();
        subjectField.clear();
        taskField.clear();
    }

    // ================= DELETE =================
    @FXML
    private void handleDelete() {
        StudySession selected = listView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("No selection");
            alert.setContentText("Please select a study session to delete.");
            alert.showAndWait();
            return;
        }

        listView.getItems().remove(selected);

        dayField.clear();
        subjectField.clear();
        taskField.clear();
        dayError.setText("");
        subjectError.setText("");
    }

    // ================= LIST CLICK =================
    @FXML
    public void initialize() {

        // Populate fields when clicking list
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                dayField.setText(newVal.getDay().substring(0,1)); // back to M,T,W...
                subjectField.setText(newVal.getSubject());
                taskField.setText(newVal.getTask());
            }
        });

        // ================= DYNAMIC VALIDATION (TASK 6) =================
        dayField.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(javafx.beans.value.ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if (newValue.isEmpty()) {
                    dayError.setText("");
                    return;
                }

                if (!(newValue.equalsIgnoreCase("M") || newValue.equalsIgnoreCase("T")
                        || newValue.equalsIgnoreCase("W") || newValue.equalsIgnoreCase("R")
                        || newValue.equalsIgnoreCase("F"))) {

                    dayError.setText("must be M, T, W, R, or F");
                } else {
                    dayError.setText("");
                }
            }
        });
    }
}