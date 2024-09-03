package com.quanlybansach_java.CONTROLLER;

import com.quanlybansach_java.utils.ResourceUtils;
import com.quanlybansach_java.utils.UserId;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.quanlybansach_java.DAO.UserDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    private UserId userId;
    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert emptyFieldsAlert = new Alert(AlertType.WARNING);
            emptyFieldsAlert.setTitle("Cảnh báo");
            emptyFieldsAlert.setHeaderText(null);
            emptyFieldsAlert.setContentText("Vui lòng điền đủ thông tin đăng nhập.");
            emptyFieldsAlert.showAndWait();
        } else {
            UserDAO userDAO = new UserDAO();
            boolean isAuthenticated = userDAO.authenticateUser(username, password);

            if (isAuthenticated) {
                // Đăng nhập thành công
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đăng nhập thành công!");
                successAlert.showAndWait();

                userId = UserDAO.getUserId(username, password);
//                System.out.println(userId.getUser_id());

                showMainMenu();
            } else {
                // Đăng nhập thất bại
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Lỗi");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin đăng nhập.");
                errorAlert.showAndWait();
            }
        }
    }
    @FXML
    private void initialize() {
        // Xử lý sự kiện khi nhấn phím Enter trên usernameField
        usernameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                    handleLoginButtonAction();
                }
            }
        });

        // Xử lý sự kiện khi nhấn phím Enter trên passwordField
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                    handleLoginButtonAction();
                }
            }
        });
    }


    private void showMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quanlybansach_java/Main_Menu.fxml"));
            Parent root = loader.load();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.setUserPermission(userId);

            Stage MenuStage = new Stage();
            MenuStage.setTitle("PHẦN MỀM QUẢN LÍ BÁN SÁCH THANH BÌNH BOOKS");
            MenuStage.setScene(new Scene(root));
            MenuStage.getIcons().add(ResourceUtils.getImage("logo.jpg"));
            MenuStage.setResizable(false);
            MenuStage.show();

            // Đóng Scene đăng nhập
            Stage loginStage = (Stage) usernameField.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
