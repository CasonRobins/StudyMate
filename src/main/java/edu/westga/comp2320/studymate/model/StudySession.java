package edu.westga.comp2320.studymate.model;

public class StudySession {

    // StudySession model handles validation and formatting of study data
    private String day;
    private String subject;
    private String task;

    public StudySession(String day, String subject, String task) {

        if (!isValidDay(day)) {
            throw new IllegalArgumentException("Invalid day");
        }

        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Subject required");
        }

        this.day = convertDay(day);
        this.subject = subject;
        this.task = task;
    }

    private boolean isValidDay(String day) {
        if (day == null) return false;

        String d = day.toUpperCase();

        return d.equals("M") || d.equals("T") ||
                d.equals("W") || d.equals("R") ||
                d.equals("F");
    }

    private String convertDay(String day) {
        switch (day.toUpperCase()) {
            case "M": return "Monday";
            case "T": return "Tuesday";
            case "W": return "Wednesday";
            case "R": return "Thursday";
            case "F": return "Friday";
        }
        return "";
    }

    public String getDay() {
        return this.day;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {

        if (this.task == null || this.task.isBlank()) {
            return this.day + ": " + this.subject;
        }

        return this.day + ": " + this.subject + " - " + this.task;
    }
}