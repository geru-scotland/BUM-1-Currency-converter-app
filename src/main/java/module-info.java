module eus.ehu.bum1_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens eus.ehu.bum1_fx to javafx.fxml;
    exports eus.ehu.bum1_fx;
    exports eus.ehu.bum1_fx.presentation;
    opens eus.ehu.bum1_fx.presentation to javafx.fxml;
    exports eus.ehu.bum1_fx.business_logic;
    opens eus.ehu.bum1_fx.business_logic to javafx.fxml;
}
