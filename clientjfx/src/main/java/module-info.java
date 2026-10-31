module estm.dsic.jee {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;

    opens estm.dsic.jee to javafx.fxml;
    exports estm.dsic.jee;
    opens business.services to javafx.fxml;
    exports business.services;
    opens models to javafx.fxml;
    exports models;
}
