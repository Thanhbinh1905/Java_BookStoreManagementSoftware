package com.quanlybansach_java;
import com.quanlybansach_java.utils.ResourceUtils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class GUIApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage LoginStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/quanlybansach_java/Login_Screen.fxml")));
        Scene scene = new Scene(root);

        LoginStage.setTitle("Login");
        LoginStage.setScene(scene);
        LoginStage.setResizable(false);
        LoginStage.show();

        LoginStage.getIcons().add(ResourceUtils.getImage("logo.jpg"));
        LoginStage.show();
    }
}
