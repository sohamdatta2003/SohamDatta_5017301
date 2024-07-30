public class MVCPatternExample {
    public static void main(String[] args) {
        // Create a Student model
        Student student = new Student("67", "Soham datta", "A");

        // Create a Student view
        StudentView view = new StudentView();

        // Create a Student controller
        StudentController controller = new StudentController(student, view);

        // Display initial student details
        controller.updateView();

        // Update student details
        controller.setStudentName("Zoya Khan");
        controller.setStudentGrade("B");

        // Display updated student details
        controller.updateView();
    }
}
