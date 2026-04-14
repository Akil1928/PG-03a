module ucr.lab.pg03a {
    requires javafx.controls;
    requires javafx.fxml;


    opens ucr.lab.pg03a to javafx.fxml;
    exports ucr.lab.pg03a;
}