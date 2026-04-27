package edu.westga.comp2320.studymate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class StudyMateController {

    // Radio Buttons
    @FXML private RadioButton mondayRadio;
    @FXML private RadioButton tuesdayRadio;
    @FXML private RadioButton wednesdayRadio;
    @FXML private RadioButton thursdayRadio;
    @FXML private RadioButton fridayRadio;

    // Checkboxes
    @FXML private CheckBox englCheck;
    @FXML private CheckBox histCheck;
    @FXML private CheckBox mathCheck;
    @FXML private CheckBox compCheck;

    // Other UI
    @FXML private TextField taskField;
    @FXML private ListView<String> listView;
    @FXML private Label errorLabel;

    // Menu
    @FXML private MenuItem saveMenuItem;

    @FXML
    public void initialize() {
        mondayRadio.setSelected(true);
        errorLabel.setText("");
    }

    private String getSelectedDay() {
        if (mondayRadio.isSelected()) return "Monday";
        if (tuesdayRadio.isSelected()) return "Tuesday";
        if (wednesdayRadio.isSelected()) return "Wednesday";
        if (thursdayRadio.isSelected()) return "Thursday";
        return "Friday";
    }

    private List<String> getSelectedSubjects() {
        List<String> subjects = new ArrayList<>();

        if (englCheck.isSelected()) subjects.add("ENGL");
        if (histCheck.isSelected()) subjects.add("HIST");
        if (mathCheck.isSelected()) subjects.add("MATH");
        if (compCheck.isSelected()) subjects.add("COMP");

        return subjects;
    }

    @FXML
    private void handleAdd() {
        List<String> subjects = getSelectedSubjects();

        if (subjects.isEmpty()) {
            errorLabel.setText("select at least one subject");
            return;
        }

        errorLabel.setText("");

        String day = getSelectedDay();
        String task = taskField.getText();

        for (String subject : subjects) {
            listView.getItems().add(day + ": " + subject + " - " + task);
        }

        listView.getSelectionModel().selectLast();
    }

    @FXML
    private void handleDelete() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            listView.getItems().remove(index);
        }
    }
}