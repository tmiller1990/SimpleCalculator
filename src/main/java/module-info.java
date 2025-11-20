module com.lermiller.simplecalulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.lermiller.simplecalulator to javafx.fxml;
    exports com.lermiller.simplecalulator;
}