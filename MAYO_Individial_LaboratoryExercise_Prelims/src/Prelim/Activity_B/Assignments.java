package Prelim.Activity_B;
/*
Class Object for the MyGrowingArrayList
which has the same properties in the instructor
Assignment
 */
public class Assignments {
    private String projectName;
    private String dateAssigned;
    private String dateSumbitted;

    public Assignments(String projectName, String dateAssigned, String dateSumbitted) {
        this.projectName = projectName;
        this.dateAssigned = dateAssigned;
        this.dateSumbitted = dateSumbitted;
    }

    public Assignments() {
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getProjectName() {
        return projectName;
    }

    public String getDateAssigned() {
        return dateAssigned;
    }

    public String getDateSumbitted() {
        return dateSumbitted;
    }

    @Override
    public String toString() {
        return " | Project Name = " + getProjectName() +
                " | Date Assigned = " + getDateAssigned() +
                " | Date Submitted = " + getDateSumbitted() +
                "\n";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getProjectName() != getProjectName()) {
            return false;
        }
        Assignments assignments = (Assignments) obj;
        return projectName.equals(assignments.projectName);
    }

}
