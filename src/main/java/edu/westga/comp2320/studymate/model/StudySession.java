package edu.westga.comp2320.studymate.model;

/**
 * Represents a study session with a day, subject, and optional task.
 */
public class StudySession {

    // StudySession model handles validation and formatting of study data
    private String day;
    private String subject;
    private String task;

    /**
     * Creates a StudySession.
     *
     * @param day     the day of the week (M, T, W, R, F)
     * @param subject the subject (required)
     * @param task    the task (optional)
     */
    public StudySession(String day, String subject, String task) {

        if (!this.isValidDay(day)) {
            throw new IllegalArgumentException("Invalid day");
        }

        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Subject required");
        }

        this.day = this.convertDay(day);
        this.subject = subject;
        this.task = task;
    }

    /**
     * Checks if the day is valid.
     */
    private boolean isValidDay(String day) {
        if (day == null) {
            return false;
        }

        String dayUpper = day.toUpperCase();

        return dayUpper.equals("M") || dayUpper.equals("T") ||
                dayUpper.equals("W") || dayUpper.equals("R") ||
                dayUpper.equals("F");
    }

    /**
     * Converts a single-letter day into full name.
     */
    private String convertDay(String day) {
        switch (day.toUpperCase()) {
            case "M":
                return "Monday";
            case "T":
                return "Tuesday";
            case "W":
                return "Wednesday";
            case "R":
                return "Thursday";
            case "F":
                return "Friday";
            default:
                return "";
        }
    }

    /**
     * Returns the day of the study session.
     * @return the day
     */
    public String getDay() {
        return this.day;
    }

    /**
     * Returns the subject of the study session.
     * @return the subject
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Returns the task of the study session.
     * @return the task
     */
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