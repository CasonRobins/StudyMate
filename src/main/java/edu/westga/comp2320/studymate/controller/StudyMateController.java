package edu.westga.comp2320.studymate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
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

        // LIST CLICK SYNC
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selected) -> {
            if (selected != null) {
                updateUIFromSelection(selected);
            }
        });

        // SAVE MENU ACTION
        saveMenuItem.setOnAction(e -> handleSave());
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

    private void updateUIFromSelection(String selected) {

        // Reset checkboxes
        englCheck.setSelected(false);
        histCheck.setSelected(false);
        mathCheck.setSelected(false);
        compCheck.setSelected(false);

        // Parse string
        String[] parts = selected.split(": ");
        String day = parts[0];

        String[] subjectTask = parts[1].split(" - ");
        String subject = subjectTask[0];
        String task = subjectTask[1];

        // Set day
        switch (day) {
            case "Monday": mondayRadio.setSelected(true); break;
            case "Tuesday": tuesdayRadio.setSelected(true); break;
            case "Wednesday": wednesdayRadio.setSelected(true); break;
            case "Thursday": thursdayRadio.setSelected(true); break;
            case "Friday": fridayRadio.setSelected(true); break;
        }

        // Set subject
        switch (subject) {
            case "ENGL": englCheck.setSelected(true); break;
            case "HIST": histCheck.setSelected(true); break;
            case "MATH": mathCheck.setSelected(true); break;
            case "COMP": compCheck.setSelected(true); break;
        }

        // Set task
        taskField.setText(task);
    }

    // 🔥 FINAL FEATURE
    private void handleSave() {
        try (FileWriter writer = new FileWriter("studymate.txt")) {
            for (String item : listView.getItems()) {
                writer.write(item + "\n");
            }
            errorLabel.setText("Saved to studymate.txt");
        } catch (IOException e) {
            errorLabel.setText("Error saving file");
        }
    }
}