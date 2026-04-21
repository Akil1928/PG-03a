module ucr.lab.pg03a {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens ucr.lab.pg03a to javafx.fxml;
    exports ucr.lab.pg03a;
    exports controller;
    opens controller to javafx.fxml;
}