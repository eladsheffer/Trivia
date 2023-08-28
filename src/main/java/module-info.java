module com.example.trivia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.trivia to javafx.fxml;
    exports com.example.trivia;
}