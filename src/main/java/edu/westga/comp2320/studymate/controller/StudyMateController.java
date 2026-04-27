package edu.westga.comp2320.studymate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the StudyMate application.
 */
public class StudyMateController {

    // Radio buttons
    @FXML
    private RadioButton mondayRadio;
    @FXML
    private RadioButton tuesdayRadio;
    @FXML
    private RadioButton wednesdayRadio;
    @FXML
    private RadioButton thursdayRadio;
    @FXML
    private RadioButton fridayRadio;

    // Checkboxes
    @FXML
    private CheckBox englCheck;
    @FXML
    private CheckBox histCheck;
    @FXML
    private CheckBox mathCheck;
    @FXML
    private CheckBox compCheck;

    // Other UI
    @FXML
    private TextField taskField;
    @FXML
    private ListView<String> listView;
    @FXML
    private Label errorLabel;

    // Menu
    @FXML
    private MenuItem saveMenuItem;

    /**
     * Initializes the UI components.
     */
    @FXML
    public void initialize() {
        this.mondayRadio.setSelected(true);
        this.errorLabel.setText("");

        // Fix unused warning + connects Save menu
        this.saveMenuItem.setOnAction(event -> this.handleSave());
    }

    private String getSelectedDay() {
        if (this.mondayRadio.isSelected()) {
            return "Monday";
        }
        if (this.tuesdayRadio.isSelected()) {
            return "Tuesday";
        }
        if (this.wednesdayRadio.isSelected()) {
            return "Wednesday";
        }
        if (this.thursdayRadio.isSelected()) {
            return "Thursday";
        }
        if (this.fridayRadio.isSelected()) {
            return "Friday";
        }
        return "";
    }

    private List<String> getSelectedSubjects() {
        List<String> subjects = new ArrayList<>();

        if (this.englCheck.isSelected()) {
            subjects.add("ENGL");
        }
        if (this.histCheck.isSelected()) {
            subjects.add("HIST");
        }
        if (this.mathCheck.isSelected()) {
            subjects.add("MATH");
        }
        if (this.compCheck.isSelected()) {
            subjects.add("COMP");
        }

        return subjects;
    }

    @FXML
    private void handleAdd() {
        List<String> subjects = this.getSelectedSubjects();

        if (subjects.isEmpty()) {
            this.errorLabel.setText("Select at least one subject");
            return;
        }

        this.errorLabel.setText("");

        String day = this.getSelectedDay();
        String task = this.taskField.getText();

        for (String subject : subjects) {
            this.listView.getItems().add(day + ": " + subject + " - " + task);
        }

        this.listView.getSelectionModel().selectLast();
    }

    @FXML
    private void handleDelete() {
        int index = this.listView.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            this.listView.getItems().remove(index);
        }
    }

    @FXML
    private void handleSave() {
        try (FileWriter writer = new FileWriter("studymate.txt")) {
            for (String item : this.listView.getItems()) {
                writer.write(item + "\n");
            }
            this.errorLabel.setText("Saved to studymate.txt");
        } catch (IOException e) {
            this.errorLabel.setText("Error saving file");
        }
    }
}