module EventPlanningSystem.EventPlanningSystem {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.persistence;
	requires org.hibernate.orm.core;
	requires java.sql;

    opens EventPlanningSystem.EventPlanningSystem to javafx.fxml;
    exports EventPlanningSystem.EventPlanningSystem;
}
