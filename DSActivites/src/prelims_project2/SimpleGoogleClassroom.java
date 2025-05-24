package prelims_project2;

import java.util.*;


// GPT Idea
public class SimpleGoogleClassroom {

    // Create the outermost list representing classrooms
    static LinkedList<LinkedList<LinkedList<Object>>> classrooms = new LinkedList<>();

    // Create a classroom (outer list)
    public static void createClassroom() {
        LinkedList<LinkedList<Object>> newClassroom = new LinkedList<>();
        classrooms.add(newClassroom);
        System.out.println("New classroom created!");
    }

    // Create an assignment in a specific classroom
    public static void createAssignment(int classroomIndex) {
        if (classroomIndex < classrooms.size()) {
            LinkedList<Object> newAssignment = new LinkedList<>();
            classrooms.get(classroomIndex).add(newAssignment);
            System.out.println("New assignment created in Classroom " + classroomIndex + "!");
        } else {
            System.out.println("Classroom does not exist.");
        }
    }

    // Create a submission for an assignment
    public static void createSubmission(int classroomIndex, int assignmentIndex, String studentName, String submissionContent) {
        if (classroomIndex < classrooms.size()) {
            LinkedList<LinkedList<Object>> classroom = classrooms.get(classroomIndex);
            if (assignmentIndex < classroom.size()) {
                classroom.get(assignmentIndex).add(new Submission(studentName, submissionContent));
                System.out.println("Submission created for assignment " + assignmentIndex + " in Classroom " + classroomIndex);
            } else {
                System.out.println("Assignment does not exist.");
            }
        } else {
            System.out.println("Classroom does not exist.");
        }
    }

    // Read classrooms, assignments, or submissions
    public static void readClassrooms() {
        System.out.println("Classrooms: ");
        for (int i = 0; i < classrooms.size(); i++) {
            System.out.println("Classroom " + i + ": " + classrooms.get(i));
        }
    }

    public static void readAssignments(int classroomIndex) {
        if (classroomIndex < classrooms.size()) {
            System.out.println("Assignments in Classroom " + classroomIndex + ": " + classrooms.get(classroomIndex));
        } else {
            System.out.println("Classroom does not exist.");
        }
    }

    public static void readSubmissions(int classroomIndex, int assignmentIndex) {
        if (classroomIndex < classrooms.size()) {
            if (assignmentIndex < classrooms.get(classroomIndex).size()) {
                System.out.println("Submissions for Assignment " + assignmentIndex + " in Classroom " + classroomIndex + ": " + classrooms.get(classroomIndex).get(assignmentIndex));
            } else {
                System.out.println("Assignment does not exist.");
            }
        } else {
            System.out.println("Classroom does not exist.");
        }
    }

    // Update submission content
    public static void updateSubmission(int classroomIndex, int assignmentIndex, int submissionIndex, String newContent) {
        if (classroomIndex < classrooms.size()) {
            if (assignmentIndex < classrooms.get(classroomIndex).size()) {
                LinkedList<Object> assignment = classrooms.get(classroomIndex).get(assignmentIndex);
                if (submissionIndex < assignment.size()) {
                    Submission submission = (Submission) assignment.get(submissionIndex);
                    submission.content = newContent;
                    System.out.println("Submission updated!");
                } else {
                    System.out.println("Submission does not exist.");
                }
            } else {
                System.out.println("Assignment does not exist.");
            }
        } else {
            System.out.println("Classroom does not exist.");
        }
    }

    // Delete classroom, assignment, or submission
    public static void deleteClassroom(int classroomIndex) {
        if (classroomIndex < classrooms.size()) {
            classrooms.remove(classroomIndex);
            System.out.println("Classroom " + classroomIndex + " deleted.");
        } else {
            System.out.println("Classroom does not exist.");
        }
    }

    public static void deleteAssignment(int classroomIndex, int assignmentIndex) {
        if (classroomIndex < classrooms.size()) {
            if (assignmentIndex < classrooms.get(classroomIndex).size()) {
                classrooms.get(classroomIndex).remove(assignmentIndex);
                System.out.println("Assignment " + assignmentIndex + " in Classroom " + classroomIndex + " deleted.");
            } else {
                System.out.println("Assignment does not exist.");
            }
        } else {
            System.out.println("Classroom does not exist.");
        }
    }

    public static void deleteSubmission(int classroomIndex, int assignmentIndex, int submissionIndex) {
        if (classroomIndex < classrooms.size()) {
            if (assignmentIndex < classrooms.get(classroomIndex).size()) {
                LinkedList<Object> assignment = classrooms.get(classroomIndex).get(assignmentIndex);
                if (submissionIndex < assignment.size()) {
                    assignment.remove(submissionIndex);
                    System.out.println("Submission deleted.");
                } else {
                    System.out.println("Submission does not exist.");
                }
            } else {
                System.out.println("Assignment does not exist.");
            }
        } else {
            System.out.println("Classroom does not exist.");
        }
    }

    // Main class to test the implementation
    public static void main(String[] args) {
        // Create some classrooms, assignments, and submissions
        createClassroom();
        createClassroom();
        createAssignment(0);
        createAssignment(0);
        createSubmission(0, 0, "Alice", "Homework 1");
        createSubmission(0, 1, "Bob", "Project 1");

        // Read the classrooms and assignments
        readClassrooms();
        readAssignments(0);

        // Update a submission
        updateSubmission(0, 0, 0, "Updated Homework 1");

        // Read updated submission
        readSubmissions(0, 0);

        // Delete a submission and read again
        deleteSubmission(0, 0, 0);
        readSubmissions(0, 0);
    }
}

// Class representing a submission
class Submission {
    String studentName;
    String content;

    public Submission(String studentName, String content) {
        this.studentName = studentName;
        this.content = content;
    }

    @Override
    public String toString() {
        return studentName + ": " + content;
    }
}
