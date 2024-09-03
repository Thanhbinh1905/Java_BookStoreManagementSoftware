package com.quanlybansach_java.CONTROLLER;

import com.quanlybansach_java.DAO.CustomerDAO;
import com.quanlybansach_java.Model.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class AddUserController {
    CustomerDAO customerDAO = new CustomerDAO();
    List<Customer> customers = CustomerDAO.getAllCustomers();

    // Kiểm tra xem setIdNewCustomer có trong danh sách idColumn hay không
    public int generateNewCustomerId() {
        int setIdNewCustomer = customers.size() + 1;
        int maxId = 0;

        for (Customer customer : customers) {
            if (customer.getCustomerId() > maxId) {
                maxId = customer.getCustomerId();
            }
        }
        //System.out.println(maxId);
        if (setIdNewCustomer <= maxId) {
            // Tìm giá trị không trùng lặp từ 0 đến setIdNewCustomer
            for (int i = 1; i <= setIdNewCustomer; i++) {
                int finalI = i;
                boolean idInList = customers.stream()
                        .map(Customer::getCustomerId)
                        .anyMatch(id -> id == finalI);
                if (!idInList) {
                    setIdNewCustomer = i;
                    break;
                }
            }
        }
        return setIdNewCustomer;
    }

    public AddUserController() {
    }

    @FXML
    private Label idcustomer;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button AddButton;
    @FXML
    private void initialize() {
        int setIdNewCustomer = generateNewCustomerId();
        idcustomer.setText(String.valueOf(setIdNewCustomer)); // Hiển thị trong Label
    }
    @FXML
    private void addCustomer(){
        int setIdNewCustomer = generateNewCustomerId();

        // Create a new Customer object with the data from the text fields
        Customer newCustomer = new Customer(setIdNewCustomer, firstnameField.getText(), lastNameField.getText(), emailField.getText(), phoneField.getText());
        customerDAO.insertCustomer(newCustomer);

        // Hiển thị thông báo
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Đã thêm thành công khách hàng: #" + setIdNewCustomer);
        alert.showAndWait(); // Hiển thị thông báo và đợi cho đến khi nó đóng

        // Đóng Scene Add
        Stage loginStage = (Stage) AddButton.getScene().getWindow();
        loginStage.close();
    }
}
