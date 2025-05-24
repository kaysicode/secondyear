module com.practice.imfinalproject_x {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.practice.imfinalproject_x to javafx.fxml;
    exports com.practice.imfinalproject_x;
}