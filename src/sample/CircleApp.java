package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.io.IOException;
import java.util.Optional;

public class CircleApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Circle circle;
    private Slider slider;
    private ColorPicker circleColor;
    private ColorPicker backgroundColor;
    private Pane background;

    public void start(Stage stage) {

        //left interface
        slider = new Slider(0, 200, 50);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setShowTickLabels(true);
        circleColor = new ColorPicker();
        circleColor.setValue(Color.RED);
        backgroundColor = new ColorPicker();
        backgroundColor.setValue(Color.BLUEVIOLET);

        javafx.scene.text.Text setRadius = new javafx.scene.text.Text("Set radius:");
        javafx.scene.text.Text setCircleColor = new javafx.scene.text.Text("Set color of circle:");
        javafx.scene.text.Text setBackgroundColor = new javafx.scene.text.Text("Set color of background:");

        VBox left = new VBox(30, setRadius, slider, setCircleColor, circleColor, setBackgroundColor, backgroundColor);
        left.setPadding(new Insets(10));

        //right "circle" part of window
        //circle
        circle = new Circle();
        circle.setRadius(slider.getValue());
        circle.setFill(circleColor.getValue());
        circleColor.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                circle.setFill(circleColor.getValue());
            }
        });
        slider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                circle.setRadius(slider.getValue());
            }
            }
        );

        //background
        background = new Pane(circle);
        background.setMinHeight(450);
        background.setMinWidth(530);
        circle.setCenterX(background.getMinWidth()/2);
        circle.setCenterY(background.getMinHeight()/2);
        background.setBackground(new Background(new BackgroundFill(Color.web(String.valueOf(backgroundColor.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
        backgroundColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                background.setBackground(new Background(new BackgroundFill(Color.web(String.valueOf(backgroundColor.getValue())), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        VBox right = new VBox(8, background);

        //window
        BorderPane root = new BorderPane();
        root.setLeft(left);
        root.setRight(right);

        stage.setScene(new Scene(root, 700, 450));
        stage.setTitle("Circle App");
        stage.setResizable(false);
        stage.show();
    }
}