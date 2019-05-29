package StockTradingSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

class ControllerUtils {
    static void btnPress(StackPane btn) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0.3,0,1, 0.8));
        dropShadow.setWidth(30);
        dropShadow.setHeight(30);
        dropShadow.setRadius(19.5);
        dropShadow.setSpread(0.03);
        btn.setEffect(dropShadow);
    }

    static void btnRelease(StackPane btn) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0,0,0, 0.3));
        dropShadow.setWidth(30);
        dropShadow.setHeight(30);
        dropShadow.setRadius(19.5);
        dropShadow.setSpread(0.03);
        btn.setEffect(dropShadow);
    }

    static void btnMove(StackPane btn) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0.3,0,1, 0.3));
        dropShadow.setWidth(30);
        dropShadow.setHeight(30);
        dropShadow.setRadius(19.5);
        dropShadow.setSpread(0.03);
        btn.setEffect(dropShadow);
    }

    public static void showAlert(String message) {
        Stage window = new Stage();
        window.setAlwaysOnTop(true);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setMinWidth(300);
        window.setMinHeight(150);
        JFXButton button = new JFXButton("确定");
        button.setOnAction(e -> window.close());
        Label label = new Label(message);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
