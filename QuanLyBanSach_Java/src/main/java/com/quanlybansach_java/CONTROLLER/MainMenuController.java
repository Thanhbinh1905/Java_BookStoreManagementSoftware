package com.quanlybansach_java.CONTROLLER;

import com.quanlybansach_java.DAO.*;
import com.quanlybansach_java.Model.*;
import com.quanlybansach_java.utils.ResourceUtils;
import com.quanlybansach_java.utils.UserId;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.time.LocalDate;
import java.util.*;

import static com.quanlybansach_java.DAO.PublisherDAO.getPublisherIdByName;

public class MainMenuController {
    private final BookDAO bookDAO = new BookDAO();
    @FXML
    private SplitPane purchaseOrderPane;
    @FXML
    private TableView<PurchaseOrder> purchaseOrderTableView;
    public TableColumn<PurchaseOrder, Integer> purchaseOrderIDClolumn;
    @FXML
    private TableColumn<PurchaseOrder, String> purchaseOrderPublisherNameColumn;
    @FXML
    private TableColumn<PurchaseOrder, String> purchaseOrderUsernameColumn;
    @FXML
    private TableColumn<PurchaseOrder, Double> purchaseOrderTotalAmountColumn;
    @FXML
    private TableColumn<PurchaseOrder, Date> purchaseOrderDateColumn;
    @FXML
    private ComboBox<String> purchaseOrderPublisherNameComboBox;
    @FXML
    private AnchorPane purchaseOrderItemAnchorPane;
    @FXML
    private DatePicker purchaseOrderDatePicker;
    @FXML
    private TableView<PurchaseOrderItems> purchaseOrderItemTableView;
    @FXML
    private TableColumn<PurchaseOrderItems, Integer> purchaseOrderItemIDColumn;
    @FXML
    private TableColumn<PurchaseOrderItems, String> purchaseOrderItemBookNameColumn;
    public TableColumn<PurchaseOrderItems, Integer> purchaseOrderItemQuantityColumn;
    @FXML
    private TableColumn<PurchaseOrderItems, Double> purchaseOrderItemPriceColumn;
    @FXML
    private TableColumn<PurchaseOrderItems, Double> purchaseOrderItemTotalColumn;
    @FXML
    private ComboBox<String> bookNameComboBox1;
    @FXML
    private TextField quantityTextField1;
    @FXML
    private Label sumTotalAmount11;
    @FXML
    private TextField userNameTX;
    @FXML
    private TextField userUsernameTX;
    @FXML
    private TextField userPassTX;
    @FXML
    private TextField userPhoneTX;
    @FXML
    private TextField userEmailTX;
    @FXML
    private ComboBox<String> userPerCB;
    @FXML
    private  SplitPane userSplitane;
    @FXML
    private Label usserNameLabel;
    @FXML
    private Label userPhoneLabel;
    @FXML
    private Label userEmailLabel;
    @FXML
    private Label userPermissionLabel;
    @FXML
    private Button purchaseOrderButton;
    @FXML
    private AnchorPane adminPermissionAnchorPane;
    @FXML
    private Button customerButton;
    @FXML
    private Button orderButton;
    @FXML
    private Label sumTotalAmount1;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, Integer> userIdColumn;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, String> userPhoneColumn;
    @FXML
    private TableColumn<User, String> userEmailColumn;
    @FXML
    private TableColumn<User, String> userUsernameColumn;
    @FXML
    private TableColumn<User, String> userPassWordColumn;
    @FXML
    private TableColumn<User, String> userPerColumn;
    @FXML
    private TableView<Order> CustomerOrderTableView;
    @FXML
    private TableColumn<Order, Integer> CustomerOrderIdColumn;
    @FXML
    private TableColumn<Order, String> orderUsernameColumn;
    @FXML
    private TableColumn<Order, Date> CustomerOrderDateColumn;
    @FXML
    private TableColumn<Order, String> CustomerOrderCouponColumn;
    @FXML
    private TableColumn<Order, Double> CustomerOrderTotalAmountColumn;
    @FXML
    private SplitPane OrderPane;
    @FXML
    private Pane centerPane;
    @FXML
    private AnchorPane orderItemAnchorPane;
    @FXML
    private TabPane bookPane;
    @FXML
    private SplitPane CustomerFigure;
    @FXML
    private TextField bookIdTextField;
    @FXML
    private TextField titleBookTextField;
    @FXML
    private ComboBox<String> authorBookComboBox;
    @FXML
    private ComboBox<String> publisherBookComboBox;
    @FXML
    private ComboBox<String> genreBookComboBox;
    @FXML
    private TextField purchasepriceBookTextField;
    @FXML
    private TextField priceBookTextField;
    @FXML
    private TextField quantityBookTextField;
    @FXML
    public Button submitButton;
    @FXML
    private Button addAuthorButton;
    @FXML
    private Button addPublisherButton;
    @FXML
    private Button addGenreButton;
    @FXML
    private ComboBox<String> genreComboBox;
    @FXML
    private TableColumn<Book, Integer> idBookColumn;
    @FXML
    private TableColumn<Book, String> titleBookColumn;
    @FXML
    private TableColumn<Book, String> authorBookColumn;
    @FXML
    private TableColumn<Book, String> publisherBookColumn;
    @FXML
    private TableColumn<Book, String> genreBookColumn;
    @FXML
    private TableColumn<Book, Double> purchasepriceBookColumn;
    @FXML
    private TableColumn<Book, Double> priceBookColumn;
    @FXML
    private TableColumn<Book, Integer> quantityBookColumn;
    @FXML
    private TableColumn<DiscountCoupon, String> couponIdColumn;
    @FXML
    private TableColumn<DiscountCoupon, Double> couponPercentageColumn;
    @FXML
    private TableColumn<OrderItems, Integer> orderItemIDColumn;
    @FXML
    private TableColumn<OrderItems, String> orderItemBookNameColumn;
    @FXML
    private TableColumn<OrderItems, Integer> orderItemQuantityColumn;
    @FXML
    private TableColumn<OrderItems, Double> orderItemPriceColumn;
    @FXML
    private TableColumn<OrderItems, Double> orderItemTotalColumn;
    @FXML
    private TextField IdTextField;
    @FXML
    private TextField titleFieldText;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField publisherTextField;
    private int selectedCustomerId = -1;
    private int selectedUserId = -1;
    private int selectedBookId = -1;
    private int selectedAuthorId = -1;
    private int selectedPublisherId = -1;
    private String selectedCoupon;
    private int selectedOrder = -1;
    private int selectedOrderItem = -1;
    private int selectedPurchaseOrder = -1;
    private int selectedPurchaseOrderItem = -1;
    private int selectedSubmitButton = -1;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableView<DiscountCoupon> discountCouponsTableView;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableView<Order> orderTableView;
    @FXML
    private TableView<OrderItems> orderItemTableView;
    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> firstNameColumn;

    @FXML
    private TableColumn<Customer, String> lastNameColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;
    @FXML
    private TableColumn<Order, Integer> orderIDClolumn;
    @FXML
    private TableColumn<Order, String> orderCutomerNameColumn;
    @FXML
    private TableColumn<Order, String> orderCouponColumn;
    @FXML
    private TableColumn<Order, Date> orderDateColumn;
    @FXML
    private TableColumn<Order, Double> orderTotalAmountColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Label showId;
    @FXML
    private TextField showFirstName;
    @FXML
    private TextField showLastName;
    @FXML
    private TextField showEmail;
    @FXML
    private TextField showPhone;
    @FXML
    private ComboBox<Integer> orderCustomerNameComboBox;
    @FXML
    private ComboBox<String> orderCouponComboBox;
    @FXML
    private DatePicker orderDatePicker;
    @FXML
    private TextField itemIdTextField;
    @FXML
    private ComboBox<String> bookNameComboBox;
    @FXML
    private TextField quantityTextField;
    @FXML
    private Label sumTotalAmountLabel;
    private int userCurrent = -1;

    public void setUserPermission(UserId userId) {

//        System.out.println("User Permission set to: " + userId.getUser_id()); // Thêm dòng này để kiểm tra
        String userPermission = UserDAO.getPermissionById(userId.getUser_id());
//        System.out.println(userPermission);

        if (Objects.requireNonNull(userPermission).equals("NHÂN VIÊN KHO")) {
            adminPermissionAnchorPane.setVisible(false);
            customerButton.setDisable(true);
            orderButton.setDisable(true);
        }   else if (userPermission.equals("NHÂN VIÊN BÁN HÀNG")) {
            purchaseOrderButton.setDisable(true);
            adminPermissionAnchorPane.setVisible(false);
        }
        userPermissionLabel.setText(userPermission);

        List<User> users = UserDAO.getUserInformationById(userId.getUser_id());
        User user = Objects.requireNonNull(users).get(0);
        userCurrent = userId.getUser_id();
        System.out.println(userCurrent);
        usserNameLabel.setText(user.getName());
        userEmailLabel.setText(user.getEmail());
        userPhoneLabel.setText(user.getPhone());
    }
    @FXML
    private void initialize() {
        // Đặt cột cho userTableView
        userIdColumn.setCellValueFactory(cellData -> cellData.getValue().getUserIdProperty().asObject());
        userNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        userPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());
        userEmailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        userUsernameColumn.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
        userPassWordColumn.setCellValueFactory(cellData -> cellData.getValue().getPasswordProperty());
        userPerColumn.setCellValueFactory(cellData -> cellData.getValue().getPermissionProperty());
        // Đặt cột cho CustomerTableView
        idColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        // Đặt cột cho bookTableView
        idBookColumn.setCellValueFactory(cellData -> cellData.getValue().getBookIdProperty().asObject());
        titleBookColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
        authorBookColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthorNameProperty());
        publisherBookColumn.setCellValueFactory(cellData -> cellData.getValue().getPublisherNameProperty());
        genreBookColumn.setCellValueFactory(cellData -> cellData.getValue().getGenreProperty());
        purchasepriceBookColumn.setCellValueFactory(cellData -> cellData.getValue().getPurchase_priceProperty().asObject());
        priceBookColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        quantityBookColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantityInStockProperty().asObject());

        //Đặt cột cho discountCouponsTableView
        couponIdColumn.setCellValueFactory(cellData -> cellData.getValue().CouponIdProperty());
        couponPercentageColumn.setCellValueFactory(cellData -> cellData.getValue().DiscountPercentageProperty().asObject());

        //Đặt cột cho orderTableView
        orderIDClolumn.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty().asObject());
        orderUsernameColumn.setCellValueFactory(cellData -> cellData.getValue().getUserUsernameProperty());
        orderCutomerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        orderCouponColumn.setCellValueFactory(cellData -> cellData.getValue().couponIdProperty());
        orderDateColumn.setCellValueFactory(cellData -> cellData.getValue().orderDateProperty());
        orderTotalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().totalAmountProperty().asObject());

        //Đặt cột cho orderDetailTableView
        orderItemIDColumn.setCellValueFactory(cellData -> cellData.getValue().getOrderDetailIdProperty().asObject());
        orderItemBookNameColumn.setCellValueFactory(cellData -> cellData.getValue().getBookNameProperty());
        orderItemQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty().asObject());
        orderItemPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getBookPriceProperty().asObject());
        orderItemTotalColumn.setCellValueFactory(cellData -> cellData.getValue().getSubtotalProperty().asObject());

        //Đặt cột cho purchaseOrderTableView
        purchaseOrderIDClolumn.setCellValueFactory(cellData -> cellData.getValue().purchaseOrder_idProperty().asObject());
        purchaseOrderUsernameColumn.setCellValueFactory(cellData -> cellData.getValue().userUsernameProperty());
        purchaseOrderPublisherNameColumn.setCellValueFactory(cellData -> cellData.getValue().publisher_nameProperty());
        purchaseOrderDateColumn.setCellValueFactory(cellData -> cellData.getValue().purchaseOrderDateProperty());
        purchaseOrderTotalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().totalAmountProperty().asObject());

        //Đặt cột cho purchaseOrderItemTableView
        purchaseOrderItemIDColumn.setCellValueFactory(cellData -> cellData.getValue().item_idProperty().asObject());
        purchaseOrderItemBookNameColumn.setCellValueFactory(cellData -> cellData.getValue().bookNameSoldProperty());
        purchaseOrderItemQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        purchaseOrderItemPriceColumn.setCellValueFactory(cellData -> cellData.getValue().bookBuyingPriceSoldProperty().asObject());
        purchaseOrderItemTotalColumn.setCellValueFactory(cellData -> cellData.getValue().total_amountProperty().asObject());

        //Đặt cột cho CustomerOrderTableView
        CustomerOrderIdColumn.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty().asObject());
        CustomerOrderDateColumn.setCellValueFactory(cellData -> cellData.getValue().orderDateProperty());
        CustomerOrderCouponColumn.setCellValueFactory(cellData -> cellData.getValue().couponIdProperty());
        CustomerOrderTotalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().totalAmountProperty().asObject());

        // Xử lý sự kiện khi nhấn phím Enter
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!searchField.getText().isEmpty()) {
                    handleReadersPane();
                }
            }
        });
        // event chon thong tin trong userTableView
        userTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Kiểm tra nếu người dùng nhấn chuột
                User selectedUser = userTableView.getSelectionModel().getSelectedItem();
                if (selectedUser != null) {
                    selectedUserId = selectedUser.getUserId();
                    // System.out.println(selectedUserId);

                    userNameTX.setText(selectedUser.getName1());
                    userUsernameTX.setText(selectedUser.getUsername());
                    userPassTX.setText(selectedUser.getPassword());
                    userPhoneTX.setText(selectedUser.getPhone1());
                    userEmailTX.setText(selectedUser.getEmail1());
                    userPerCB.setValue(selectedUser.getPermission());
                }
            }
        });
        // event chon thong tin trong customerTable
        customerTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Kiểm tra nếu người dùng nhấn chuột
                Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
                if (selectedCustomer != null) {
                    selectedCustomerId = selectedCustomer.getCustomerId();
                    showId.setText("#" + selectedCustomerId);

                    showLastName.setText(selectedCustomer.getLastName());
                    showFirstName.setText(selectedCustomer.getFirstName());

                    String selectedCustomerEmail = selectedCustomer.getEmail();
                    showEmail.setText(selectedCustomerEmail);

                    String selectedCustomerPhone = selectedCustomer.getPhone();
                    showPhone.setText(selectedCustomerPhone);

                    // Lưu ID của khách hàng được chọn
                    //System.out.println("Đã chọn khách hàng id: " + selectedCustomerId);
                    if (selectedCustomerId != -1) {
                        List<Order> orders = OrderDAO.showCustomerOrder(selectedCustomerId);

                        // Cập nhật TableView với kết quả tìm kiếm
                        CustomerOrderTableView.setItems(FXCollections.observableList(orders));
                        CustomerOrderTableView.getSortOrder().add(CustomerOrderIdColumn);
                        double totalAmount = 0;
                        for (Order order : orders) {
                            totalAmount += order.gettotalAmount();
                        }
                        sumTotalAmountLabel.setText(String.valueOf(totalAmount));
                    }
                }
            }
        });
        // event chon thong tin trong bookTable
        bookTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    selectedBookId = selectedBook.getBookId();
                    bookIdTextField.setText("#" + selectedBookId);

                    String selectedBookTitle = selectedBook.getTitle();
                    titleBookTextField.setText(selectedBookTitle);

                    String selectedAuthorNameBook = selectedBook.getAuthorName();
                    authorBookComboBox.setValue(selectedAuthorNameBook);

                    String selectedPublisherNameBook = selectedBook.getPublisherName();
                    publisherBookComboBox.setValue(selectedPublisherNameBook);

                    String selectedGenreBook = selectedBook.getGenre();
                    genreBookComboBox.setValue(selectedGenreBook);


                    double selectedPurchasePriceBook = selectedBook.getPurchasePrice();
                    purchasepriceBookTextField.setText(String.valueOf(selectedPurchasePriceBook));

                    double selectedPriceBook = selectedBook.getPrice();
                    priceBookTextField.setText(String.valueOf(selectedPriceBook));

                    int selectedQuantityStockBook = selectedBook.getQuantityInStock();
                    quantityBookTextField.setText(String.valueOf(selectedQuantityStockBook));
                }
            }
        });
        //event chon thong tin trong CouponTable
        discountCouponsTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                DiscountCoupon selectedDiscountCoupon = discountCouponsTableView.getSelectionModel().getSelectedItem();
                if (selectedDiscountCoupon != null) {
                    selectedCoupon = selectedDiscountCoupon.getCouponId();
                   //System.out.println(selectedCoupon);
                }
            }
        });
        //event chon thong tin trong OrderTable
        orderTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Order selectOrder = orderTableView.getSelectionModel().getSelectedItem();
                if (selectOrder != null) {
                    selectedOrder = selectOrder.getOrderId();

                    int selectedOrderCustomerId = selectOrder.getCustomerId();
                    orderCustomerNameComboBox.setValue(selectedOrderCustomerId);

                    String selectedOrderCoupon = selectOrder.getCouponId();
                    orderCouponComboBox.setValue(selectedOrderCoupon);

                    Date selectedOrderDate = selectOrder.getOrderDate();
                    orderDatePicker.setValue(LocalDate.parse(selectedOrderDate.toString()));

                    if (selectedOrder != -1) {
                        List<OrderItems> orderItems = OrderDetailDAO.getAllOrderDetails();
                        List<OrderItems> matchingOrderItems = OrderDetailDAO.searchOrderDetails(orderItems, selectedOrder);

                        // Cập nhật TableView với kết quả tìm kiếm
                        orderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                        orderItemTableView.getSortOrder().add(orderItemIDColumn);
                        orderItemAnchorPane.setDisable(false);
                        // Cập nhật TableView với kết quả tìm kiếm
                        double totalAmount = 0;
                        for (OrderItems orderItem : matchingOrderItems) {
                            totalAmount += orderItem.getSubtotal();
                        }
                        sumTotalAmount1.setText(String.valueOf(totalAmount));
                    }
                }
            }
        });
        //event chon thong tin trong OrderTable
        purchaseOrderTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                PurchaseOrder selectPurchaseOrder = purchaseOrderTableView.getSelectionModel().getSelectedItem();
                if (selectPurchaseOrder != null) {
                    selectedPurchaseOrder = selectPurchaseOrder.getPurchaseOrder_id();


                    String selectedPurchaseOrderPublisherName = selectPurchaseOrder.getPublisher_name();
                    purchaseOrderPublisherNameComboBox.setValue(selectedPurchaseOrderPublisherName);

                    Date selectedPurchaseOrderDate = selectPurchaseOrder.getPurchaseOrderDate();
                    purchaseOrderDatePicker.setValue(LocalDate.parse(selectedPurchaseOrderDate.toString()));

                    if (selectedPurchaseOrder != -1) {
                        List<PurchaseOrderItems> purchaseOrderItems = PurchaseOrderItemDAO.getAllPurchaseOrderItems();
                        List<PurchaseOrderItems> matchingOrderItems = PurchaseOrderItemDAO.searchPurchaseOrders(purchaseOrderItems, selectedPurchaseOrder);

                        // Cập nhật TableView với kết quả tìm kiếm
                        purchaseOrderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                        purchaseOrderItemTableView.getSortOrder().add(purchaseOrderItemIDColumn);
                        purchaseOrderItemAnchorPane.setDisable(false);
                        // Cập nhật TableView với kết quả tìm kiếm
                        double totalAmount = 0;
                        for (PurchaseOrderItems orderItem : matchingOrderItems) {
                            totalAmount += orderItem.getTotal_amount();
                        }
                        sumTotalAmount11.setText(String.valueOf(totalAmount));
                    }
                }
            }
        });

        purchaseOrderItemTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                PurchaseOrderItems selectOrderItem = purchaseOrderItemTableView.getSelectionModel().getSelectedItem();
                if (selectOrderItem != null) {
                    selectedPurchaseOrderItem = selectOrderItem.getItem_id();

                    String selectedBookOrderItem = selectOrderItem.getBookNameSold();
                    bookNameComboBox1.setValue(selectedBookOrderItem);

                    int selectedBookQuantity = selectOrderItem.getQuantity();
                    quantityTextField1.setText(String.valueOf(selectedBookQuantity));
                }
            }
        });

        orderItemTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                OrderItems selectOrderItem = orderItemTableView.getSelectionModel().getSelectedItem();
                if (selectOrderItem != null) {
                    selectedOrderItem = selectOrderItem.getOrderDetailId();

                    String selectedBookOrderItem = selectOrderItem.getBookNameSold();
                    bookNameComboBox.setValue(selectedBookOrderItem);

                    int selectedBookQuantity = selectOrderItem.getQuantity();
                    quantityTextField.setText(String.valueOf(selectedBookQuantity));
                }
            }
        });

        // Lấy danh sách
        List<String> genres = BookDAO.getAllGenres();
        List<String> authors = AuthorDAO.getAuthorName();
        List<String> publishers = PublisherDAO.getPublisherName();
        List<Integer> customerIds = CustomerDAO.getCustomerIds();
        List<String> couponIds = DiscountCouponDAO.getCouponIds();
        List<String> bookNames = BookDAO.getBookName();
        List<String> permissions = UserDAO.getAllUserPermission();
        // Đưa danh sách thể loại vào ComboBox
        genreComboBox.setItems(FXCollections.observableArrayList(genres));
        genreBookComboBox.setItems(FXCollections.observableArrayList(genres));
        authorBookComboBox.setItems(FXCollections.observableArrayList(authors));
        publisherBookComboBox.setItems(FXCollections.observableArrayList(publishers));
        orderCustomerNameComboBox.setItems(FXCollections.observableArrayList(customerIds));
        orderCouponComboBox.setItems(FXCollections.observableArrayList(couponIds));
        bookNameComboBox.setItems(FXCollections.observableArrayList(bookNames));
        bookNameComboBox1.setItems(FXCollections.observableArrayList(bookNames));
        userPerCB.setItems(FXCollections.observableArrayList(permissions));
        purchaseOrderPublisherNameComboBox.setItems(FXCollections.observableArrayList(publishers));

        authorBookComboBox.setOnAction(event -> {
            // Lấy tên tác giả được chọn
            String selectedAuthor = authorBookComboBox.getValue();
            selectedAuthorId = AuthorDAO.getAuthorIdByName(selectedAuthor);
        });
        publisherBookComboBox.setOnAction(event -> {
            // Lấy tên NXB được chọn
            String selectedPulisher = publisherBookComboBox.getValue();
            selectedPublisherId = getPublisherIdByName(selectedPulisher);
        });
        bookNameComboBox.setOnAction(event -> {
            // Lấy tên khách hàng được chọn
            String selectedBookidItems = bookNameComboBox.getValue();
            selectedBookId = OrderDetailDAO.getBookIdByName(selectedBookidItems);
        });
    }
    @FXML
    private void handleUserPane() {
        centerPane.setVisible(false);
        CustomerFigure.setVisible(false);
        bookPane.setVisible(false);
        OrderPane.setVisible(false);
        purchaseOrderPane.setVisible(false);
        userSplitane.setVisible(true);

        List<User> users = UserDAO.getAllUser();
        userTableView.setItems(FXCollections.observableList(users));
        userTableView.getSortOrder().add(userIdColumn);
    }
    @FXML
    private void handleReadersPane() {
        List<Customer> customers = CustomerDAO.getAllCustomers();
        // Đặt danh sách khách hàng vào TableView (customerTable)
        customerTable.setItems(FXCollections.observableList(customers));
        customerTable.getSortOrder().add(idColumn);

        centerPane.setVisible(false);
        CustomerFigure.setVisible(true);
        bookPane.setVisible(false);
        OrderPane.setVisible(false);
        purchaseOrderPane.setVisible(false);
        userSplitane.setVisible(false);
    }
    @FXML
    private void handleCustomerSearch() {
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customers = CustomerDAO.getAllCustomers();
        String keyword = searchField.getText();

        if (!keyword.isEmpty()) {
            // Gọi phương thức searchCustomers từ CustomerDAO
            List<Customer> matchingCustomers = customerDAO.searchCustomers(customers, keyword);
            searchField.clear();
            // Cập nhật TableView với kết quả tìm kiếm
            customerTable.setItems(FXCollections.observableList(matchingCustomers));
            customerTable.getSortOrder().add(idColumn);
        }
    }

    @FXML
    private void handleClearCustomerSearch() {
        List<Customer> customers = CustomerDAO.getAllCustomers();
        searchField.clear(); // Xóa nội dung trong search bar
        customerTable.setItems(FXCollections.observableList(customers)); // Hiển thị tất cả dữ liệu
        // Sắp xếp dữ liệu theo ID
        customerTable.getSortOrder().add(idColumn);
    }

    @FXML
    private void handleVersionMenuItem() {
        Alert versionAlert = new Alert(Alert.AlertType.INFORMATION);
        versionAlert.setTitle("Phiên bản");
        versionAlert.setHeaderText(null);
        versionAlert.setContentText("PHẦN MỀM QUẢN LÍ BÁN SÁCH\nAUTHOR: TRẦN THANH BÌNH\nPhiên bản: 1.0.0");

        ImageView icon = new ImageView(ResourceUtils.getImage("logo.jpg"));

        versionAlert.getDialogPane().setGraphic(icon);
        versionAlert.showAndWait();
    }
    @FXML
    private void handleExitMenuItem() {
        Platform.exit();
    }
    @FXML
    private void handleAddCustomer() {
        showAddUser();
    }
    @FXML
    private void handleEditCustomer() {
        if (!showLastName.getText().isEmpty() && !showFirstName.getText().isEmpty() && !showPhone.getText().isEmpty() && !showEmail.getText().isEmpty()) {
            // Tạo cửa sổ xác nhận xóa
            Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDelete.setTitle("Xác nhận sửa thông tin");
            confirmDelete.setHeaderText(null);
            confirmDelete.setContentText("Bạn có chắc chắn muốn sửa thông tin khách hàng #" + selectedCustomerId + " không?");

            // Thêm các nút "Có" và "Không"
            ButtonType buttonYes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmDelete.getButtonTypes().setAll(buttonYes, buttonNo);

            // Hiển thị cửa sổ xác nhận và chờ người dùng lựa chọn
            Optional<ButtonType> result = confirmDelete.showAndWait();

            if (result.isPresent() && result.get() == buttonYes) {
                // Create a new Customer object with the data from the text fields
                Customer newCustomer = new Customer(selectedCustomerId, showFirstName.getText(), showLastName.getText(), showEmail.getText(), showPhone.getText());
                CustomerDAO customerDAO = new CustomerDAO();
                customerDAO.updateCustomer(newCustomer);

                List<Customer> customers = CustomerDAO.getAllCustomers();
                customerTable.setItems(FXCollections.observableList(customers)); // Hiển thị tất cả dữ liệu
                // Sắp xếp dữ liệu theo ID
                customerTable.getSortOrder().add(idColumn);

                // Hiển thị thông báo xóa thành công
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đã sửa thông tin khách hàng #" + selectedCustomerId + " thành công");
                successAlert.showAndWait();

                selectedCustomerId = -1; // Đặt lại selectedCustomerId sau khi xóa
            }
        }
    }
    @FXML
    private void deleteCustomer() {
        if (selectedCustomerId != -1) {
            // Tạo cửa sổ xác nhận xóa
            Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDelete.setTitle("Xác nhận xóa");
            confirmDelete.setHeaderText(null);
            confirmDelete.setContentText("Bạn có chắc chắn muốn xóa khách hàng #" + selectedCustomerId + " không?");

            // Thêm các nút "Có" và "Không"
            ButtonType buttonYes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmDelete.getButtonTypes().setAll(buttonYes, buttonNo);

            // Hiển thị cửa sổ xác nhận và chờ người dùng lựa chọn
            Optional<ButtonType> result = confirmDelete.showAndWait();

            if (result.isPresent() && result.get() == buttonYes) {
                // Nếu người dùng chọn "Có", thực hiện xóa
                CustomerDAO customerDAO = new CustomerDAO();
                customerDAO.deleteCustomer(selectedCustomerId);

                // Xóa khách hàng khỏi TableView
                customerTable.getItems().removeIf(customer -> customer.getCustomerId() == selectedCustomerId);
                customerTable.getSortOrder().add(idColumn);

                // Hiển thị thông báo xóa thành công
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đã xóa thành công khách hàng #" + selectedCustomerId);
                successAlert.showAndWait();

                selectedCustomerId = -1; // Đặt lại selectedCustomerId sau khi xóa
            }
        }
    }

    public void showAddUser() {
        try {
            // Load tệp FXML của `Scene` mới
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quanlybansach_java/AddUser.fxml"));
            Parent root = loader.load();

            Stage addUserStage = new Stage();
            addUserStage.setTitle("Thêm mới độc giả");
            addUserStage.setScene(new Scene(root));
            addUserStage.getIcons().add(ResourceUtils.getImage("logo.jpg"));
            addUserStage.setResizable(false);
            addUserStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleBookSearchingPane() {
        List<Book> books = BookDAO.getAllBooks();
        bookTable.setItems(FXCollections.observableList(books));
        bookTable.getSortOrder().add(idBookColumn);

        centerPane.setVisible(false);
        CustomerFigure.setVisible(false);
        bookPane.setVisible(true);
        OrderPane.setVisible(false);
        purchaseOrderPane.setVisible(false);
        userSplitane.setVisible(false);
    }
    @FXML
    public void handleBookSearchingButton() {
        // Lấy thông tin từ các TextField và ComboBox
        int id = IdTextField.getText().isEmpty() ? -1 : Integer.parseInt(IdTextField.getText());
        String author = authorTextField.getText();
        String title = titleFieldText.getText();
        String publisher = publisherTextField.getText();
        String genre = genreComboBox.getValue();

        // Thực hiện truy vấn dựa trên thông tin đã lấy
        List<Book> searchResult = bookDAO.searchBooks(id, author, title, publisher, genre);

        // Hiển thị kết quả lên TableView
        ObservableList<Book> observableList = FXCollections.observableArrayList(searchResult);
        bookTable.setItems(observableList);
        bookTable.getSortOrder().add(idBookColumn);
    }
    @FXML
    public void handleClearBookSearch() {
        // Thực hiện truy vấn dựa trên thông tin đã lấy
        List<Book> books = BookDAO.getAllBooks();
        bookTable.setItems(FXCollections.observableList(books));
        bookTable.getSortOrder().add(idBookColumn);

        IdTextField.clear();
        authorTextField.clear();
        titleFieldText.clear();
        publisherTextField.clear();

        // Đặt ComboBox genre về giá trị mặc định hoặc giá trị trống tùy thuộc vào thiết kế của ComboBox
        genreComboBox.getSelectionModel().clearSelection(); // Đặt về giá trị trống
        bookTable.getSortOrder().add(idBookColumn);
    }
    @FXML
    private void deleteBook() {
        if (selectedBookId != -1) {
            // Tạo cửa sổ xác nhận xóa
            Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDelete.setTitle("Xác nhận xóa");
            confirmDelete.setHeaderText(null);
            confirmDelete.setContentText("Bạn có chắc chắn muốn xóa loại sách này?");

            // Thêm các nút "Có" và "Không"
            ButtonType buttonYes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmDelete.getButtonTypes().setAll(buttonYes, buttonNo);

            // Hiển thị cửa sổ xác nhận và chờ người dùng lựa chọn
            Optional<ButtonType> result = confirmDelete.showAndWait();

            if (result.isPresent() && result.get() == buttonYes) {
                // Nếu người dùng chọn "Có", thực hiện xóa
                BookDAO bookDAO = new BookDAO();
                bookDAO.deleteBook(selectedBookId);

                // Xóa khách hàng khỏi TableView
                bookTable.getItems().removeIf(customer -> customer.getBookId() == selectedBookId);
                bookTable.getSortOrder().add(idBookColumn);

                // Hiển thị thông báo xóa thành công
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đã xóa loại sách: #" + selectedBookId);
                successAlert.showAndWait();

                selectedBookId = -1; // Đặt lại selectedCustomerId sau khi xóa
            }
        }
    }
    @FXML
    private void addBookButton() {
        this.selectedSubmitButton = 1;
        bookIdTextField.setDisable(false);
        titleBookTextField.setDisable(false);
        authorBookComboBox.setDisable(false);
        publisherBookComboBox.setDisable(false);
        genreBookComboBox.setDisable(false);
        purchasepriceBookTextField.setDisable(false);
        priceBookTextField.setDisable(false);
        quantityBookTextField.setDisable(false);
        submitButton.setDisable(false);
        addAuthorButton.setDisable(false);
        addPublisherButton.setDisable(false);
        addGenreButton.setDisable(false);

        bookIdTextField.setText("");
        titleBookTextField.setText("");
        authorBookComboBox.setValue("");
        publisherBookComboBox.setValue("");
        genreBookComboBox.setValue("");
        purchasepriceBookTextField.setText("");
        priceBookTextField.setText("");
        quantityBookTextField.setText("");
    }
    @FXML
    private void editBookButton() {
        if (selectedBookId != -1) {
            this.selectedSubmitButton = 0;
            bookIdTextField.setDisable(true);
            titleBookTextField.setDisable(false);
            authorBookComboBox.setDisable(false);
            publisherBookComboBox.setDisable(false);
            genreBookComboBox.setDisable(false);
            purchasepriceBookTextField.setDisable(false);
            priceBookTextField.setDisable(false);
            quantityBookTextField.setDisable(false);
            submitButton.setDisable(false);
            addAuthorButton.setDisable(false);
            addPublisherButton.setDisable(false);
            addGenreButton.setDisable(false);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn sách để thực hiện thao tác sửa");

            // Hiển thị hộp thoại thông báo
            alert.showAndWait();
        }
    }
    @FXML
    private void submitOnAction() {
        if (selectedSubmitButton == 0) {
            // Sửa sách
            try {
                String selectedBookId = bookIdTextField.getText();
                int bookId = Integer.parseInt(selectedBookId.substring(1));
                String title = titleBookTextField.getText();
                String authorName = authorBookComboBox.getValue();
                String publisherName = publisherBookComboBox.getValue();
                String genre = genreBookComboBox.getValue();
                double purchasePrice = Double.parseDouble(purchasepriceBookTextField.getText());
                double price = Double.parseDouble(priceBookTextField.getText());
                int quantityInStock = Integer.parseInt(quantityBookTextField.getText());

                // Kiểm tra dữ liệu đầu vào
                if (title.isEmpty() || authorName == null || publisherName == null || genre == null) {
                    showAlert("Vui lòng điền đầy đủ thông tin.");
                    return; // Thoát khỏi phương thức nếu có lỗi
                }

                int authorId = AuthorDAO.getAuthorIdByName(authorName);
                int publisherId = getPublisherIdByName(publisherName);

                // Tạo đối tượng sách để sửa
                Book updatedBook = new Book(
                        bookId, title, authorId, publisherId, genre,
                        purchasePrice, price, quantityInStock, authorName, publisherName
                );

                // Gọi phương thức sửa sách từ BookDAO
                BookDAO.updateBook(updatedBook);
                showAlert("Sửa sách thành công!");

            } catch (NumberFormatException e) {
                showAlert("Nhập dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
            }
        } else if (selectedSubmitButton == 1) {
            // Thêm sách
            try {
                int book_id = Integer.parseInt(bookIdTextField.getText());
                if (BookDAO.isBookIdExists(book_id)) {
                    showAlert("Sách có mã " + book_id + " đã tồn tại. Vui lòng sử dụng một mã khác.");
                    return;
                }
                String title = titleBookTextField.getText();
                if (BookDAO.isBookTitleExists(title)) {
                    showAlert("Sách có tên " + title + " đã tồn tại.");
                    return;
                }
                String authorName = authorBookComboBox.getValue();
                String publisherName = publisherBookComboBox.getValue();
                String genre = genreBookComboBox.getValue();
                double purchasePrice = Double.parseDouble(purchasepriceBookTextField.getText());
                double price = Double.parseDouble(priceBookTextField.getText());
                int quantityInStock = Integer.parseInt(quantityBookTextField.getText());

                // Kiểm tra dữ liệu đầu vào
                if (title.isEmpty() || authorName == null || publisherName == null || genre == null) {
                    showAlert("Vui lòng điền đầy đủ thông tin.");
                    return; // Thoát khỏi phương thức nếu có lỗi
                }

                // Lấy authorId và publisherId dựa vào tên tác giả và nhà xuất bản
                int authorId = AuthorDAO.getAuthorIdByName(authorName);
                int publisherId = getPublisherIdByName(publisherName);

                // Kiểm tra xem authorId và publisherId có hợp lệ không
                if (authorId == -1 || publisherId == -1) {
                    showAlert("Tác giả hoặc nhà xuất bản không tồn tại.");
                    return;
                }

                // Tạo đối tượng sách để thêm
                Book newBook = new Book(
                        book_id,title, authorId, publisherId, genre,
                        purchasePrice, price, quantityInStock, authorName, publisherName
                );

                // Gọi phương thức thêm sách từ BookDAO
                BookDAO.addBook(newBook);

                showAlert("Thêm sách thành công!");

            } catch (NumberFormatException e) {
                showAlert("Nhập dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
            }

        }
        bookIdTextField.setText("");
        titleBookTextField.setText("");
        authorBookComboBox.setValue("");
        publisherBookComboBox.setValue("");
        genreBookComboBox.setValue("");
        purchasepriceBookTextField.setText("");
        priceBookTextField.setText("");
        quantityBookTextField.setText("");

        bookIdTextField.setDisable(true);
        titleBookTextField.setDisable(true);
        authorBookComboBox.setDisable(true);
        publisherBookComboBox.setDisable(true);
        genreBookComboBox.setDisable(true);
        purchasepriceBookTextField.setDisable(true);
        priceBookTextField.setDisable(true);
        quantityBookTextField.setDisable(true);
        submitButton.setDisable(true);
        addAuthorButton.setDisable(true);
        addPublisherButton.setDisable(true);
        addGenreButton.setDisable(true);

        // Hiển thị kết quả lên TableView
        List<Book> books = BookDAO.getAllBooks();
        bookTable.setItems(FXCollections.observableList(books));
        bookTable.getSortOrder().add(idBookColumn);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void exportBooksToExcel() {
        // Tạo danh sách sách (chỉ là một ví dụ, bạn cần có dữ liệu thực tế ở đây)
        List<Book> books = BookDAO.getAllBooks();

        // Khởi tạo một Workbook Excel bằng try-with-resources
        try (Workbook workbook = new XSSFWorkbook()) {
            // Tạo một trang tính mới
            Sheet sheet = workbook.createSheet("Book Data");

            // Tạo tiêu đề cho các cột
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Book ID", "Tên Sách", "Tác giả", "Tên NXB", "Thể loại", "Giá nhập", "Giá niêm yết", "Số lượng"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Điền dữ liệu từ danh sách sách vào bảng Excel
            int rowNum = 1;
            for (Book book : books) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(book.getBookId());
                row.createCell(1).setCellValue(book.getTitle());
                row.createCell(2).setCellValue(book.getAuthorName());
                row.createCell(3).setCellValue(book.getPublisherName());
                row.createCell(4).setCellValue(book.getGenre());
                row.createCell(5).setCellValue(book.getPurchasePrice());
                row.createCell(6).setCellValue(book.getPrice());
                row.createCell(7).setCellValue(book.getQuantityInStock());
            }

            // Lưu workbook vào một tệp Excel với tên duy nhất
            String fileName = "BookData.xlsx";
            int fileCounter = 1;
            while (fileExists(fileName)) {
                fileName = "BookData" + fileCounter + ".xlsx";
                fileCounter++;
            }

            // Khởi tạo FileOutputStream bằng try-with-resources
            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
                showAlert("Dữ liệu đã được xuất ra tệp Excel: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Đã có lỗi xảy ra khi xuất tệp Excel.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã có lỗi xảy ra khi tạo workbook Excel.");
        }
    }


    // Kiểm tra xem tệp có tồn tại hay không
    private boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
    @FXML
    private void exportOrderToExcel() {
        List<Order> orders = OrderDAO.getAllOrders();

        // Tạo một workbook Excel
        try (Workbook workbook = new XSSFWorkbook()) {

            // Tạo một trang tính mới
            Sheet sheet = workbook.createSheet("Order Data");

            // Tạo tiêu đề cho các cột
            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Tên khách hàng", "Mã giảm giá", "Ngày tạo", "Tổng tiền"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Điền dữ liệu từ danh sách vào bảng Excel
            int rowNum = 1;
            for (Order order : orders) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(order.getOrderId());
                String CustomerName = order.getCustomerLastName() + " " + order.getCustomerFirstName();
                row.createCell(1).setCellValue(CustomerName);
                row.createCell(2).setCellValue(order.getCouponId());
                row.createCell(3).setCellValue(order.getOrderDate());
                row.createCell(4).setCellValue(order.gettotalAmount());
            }

            // Lưu workbook vào một tệp Excel với tên duy nhất
            String fileName = "OrderData.xlsx";
            int fileCounter = 1;
            while (fileExists(fileName)) {
                fileName = "OrderData" + fileCounter + ".xlsx";
                fileCounter++;
            }

            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
                showAlert("Dữ liệu hóa đơn đã được xuất ra tệp Excel: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Đã có lỗi xảy ra khi xuất tệp Excel.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã có lỗi xảy ra khi tạo workbook Excel.");
        }
    }
    @FXML
    private void exportOrderItemsToExcel() {
        List<OrderItems> orderItems = OrderDetailDAO.getAllOrderDetails();

        // Tạo một workbook Excel
        try (Workbook workbook = new XSSFWorkbook()) {

            // Tạo một trang tính mới
            Sheet sheet = workbook.createSheet("OrderDetail Data");

            // Tạo tiêu đề cho các cột
            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Tên Sách", "Số lượng", "Giá niêm yết", "Thành tiền"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Điền dữ liệu từ danh sách vào bảng Excel
            int rowNum = 1;
            for (OrderItems orderItem : orderItems) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(orderItem.getOrderDetailId());
                row.createCell(1).setCellValue(orderItem.getBookNameSold());
                row.createCell(2).setCellValue(orderItem.getQuantity());
                row.createCell(3).setCellValue(orderItem.getBookPriceSold());
                row.createCell(4).setCellValue(orderItem.getSubtotal());
            }

            // Lưu workbook vào một tệp Excel với tên duy nhất
            String fileName = "OrderDetail.xlsx";
            int fileCounter = 1;
            while (fileExists(fileName)) {
                fileName = "OrderDetail" + fileCounter + ".xlsx";
                fileCounter++;
            }

            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
                showAlert("Dữ liệu hóa đơn đã được xuất ra tệp Excel: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Đã có lỗi xảy ra khi xuất tệp Excel.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã có lỗi xảy ra khi tạo workbook Excel.");
        }
    }
    @FXML
    private void exportCouponsToExcel() {
        List<DiscountCoupon> coupons = DiscountCouponDAO.getAllCoupons();

        // Tạo một workbook Excel
        try (Workbook workbook = new XSSFWorkbook()) {

            // Tạo một trang tính mới
            Sheet sheet = workbook.createSheet("Coupon Data");

            // Tạo tiêu đề cho các cột
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Mã giảm giá", "Tỷ lệ giảm giá (%)"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Điền dữ liệu từ danh sách mã giảm giá vào bảng Excel
            int rowNum = 1;
            for (DiscountCoupon coupon : coupons) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(coupon.getCouponId());
                row.createCell(1).setCellValue(coupon.getDiscountPercentage());
            }

            // Lưu workbook vào một tệp Excel với tên duy nhất
            String fileName = "CouponData.xlsx";
            int fileCounter = 1;
            while (fileExists(fileName)) {
                fileName = "CouponData" + fileCounter + ".xlsx";
                fileCounter++;
            }

            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
                showAlert("Dữ liệu mã giảm giá đã được xuất ra tệp Excel: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Đã có lỗi xảy ra khi xuất tệp Excel.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã có lỗi xảy ra khi tạo workbook Excel.");
        }
    }

    @FXML
    private void exportCustomersToExcel() {
        List<Customer> customers = CustomerDAO.getAllCustomers();

        // Tạo một workbook Excel
        try (Workbook workbook = new XSSFWorkbook()) {

            // Tạo một trang tính mới
            Sheet sheet = workbook.createSheet("Customer Data");

            // Tạo tiêu đề cho các cột
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Customer ID", "Tên", "Họ", "Email", "Sđt"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Điền dữ liệu từ danh sách khách hàng vào bảng Excel
            int rowNum = 1;
            for (Customer customer : customers) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(customer.getCustomerId());
                row.createCell(1).setCellValue(customer.getFirstName());
                row.createCell(2).setCellValue(customer.getLastName());
                row.createCell(3).setCellValue(customer.getEmail());
                row.createCell(4).setCellValue(customer.getPhone());
            }

            // Lưu workbook vào một tệp Excel với tên duy nhất
            String fileName = "CustomerData.xlsx";
            int fileCounter = 1;
            while (fileExists(fileName)) {
                fileName = "CustomerData" + fileCounter + ".xlsx";
                fileCounter++;
            }

            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
                showAlert("Dữ liệu đã được xuất ra tệp Excel: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Đã có lỗi xảy ra khi xuất tệp Excel.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã có lỗi xảy ra khi tạo workbook Excel.");
        }
    }

    @FXML
    private void handleAddGenreButtonAction() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Thêm thể loại sách:");
        dialog.setHeaderText("Nhập thể loại mới:");
        dialog.setContentText("Thể loại:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(newGenre -> {
            genreBookComboBox.getItems().add(newGenre);
            genreBookComboBox.getSelectionModel().select(newGenre);
            genreComboBox.getItems().add(newGenre);
        });
    }
    @FXML
    private void handleAddAuthorButtonAction() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Thêm tác giả");
        dialog.setHeaderText("Nhập tác giả mới:");
        dialog.setContentText("Tên tác giả:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(newAuthor  -> {
            if (AuthorDAO.isAuthorExists(newAuthor)) {
                showAlert("Tác giả này đã tồn tại.");
                return;
            } else {
                showAlert("Thêm tác giả mới thành công!.");
            }
            authorBookComboBox.getItems().add(newAuthor);
            AuthorDAO.addAuthor(newAuthor);
        });
    }
    @FXML
    private void handleAddPublisherButtonAction() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Thêm nhà xuất bản");
        dialog.setHeaderText("Nhập tên nhà xuất bản mới:");
        dialog.setContentText("Tên nhà xuất bản:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(newPublisher -> {
            if (PublisherDAO.isPublisherExists(newPublisher)) {
                showAlert("Nhà xuất bản này đã tồn tại.");
                return;
            } else {
                showAlert("Thêm nhà xuất bản mới thành công!.");
            }
            publisherBookComboBox.getItems().add(newPublisher);
            PublisherDAO.addPublisher(newPublisher);
        });
    }
    private int generateNewOrderItems(int order_id) {
        List<Integer> orderItems = OrderDetailDAO.getAllOrderItemIdByOrderId(order_id);

        int setNewOrderItemId = orderItems.size() + 1;
        int maxId = 0;

        for (int orderitem : orderItems) {
            if (orderitem > maxId) {
                maxId = orderitem;
            }
        }
        //System.out.println(maxId);
        if (setNewOrderItemId <= maxId) {
            // Tìm giá trị không trùng lặp từ 0 đến setIdNewCustomer
            for (int i = 1; i <= setNewOrderItemId; i++) {
                int finalI = i;
                boolean idInList = orderItems.stream()
                        .anyMatch(id -> id == finalI);
                if (!idInList) {
                    setNewOrderItemId = i;
                    break;
                }
            }
        }
        return setNewOrderItemId;
    }

    @FXML
    private void handleAddOrderItems() {
        try {
            int orderItemId = generateNewOrderItems(selectedOrder);
            int bookid = BookDAO.getBookIdByName(bookNameComboBox.getValue());
            int quantityItems = Integer.parseInt(quantityTextField.getText());

            // Kiểm tra xem orderItemId và quantityItems có hợp lệ không
            if (orderItemId <= 0 || quantityItems <= 0) {
                // Hiển thị thông báo lỗi nếu dữ liệu không hợp lệ
                showAlert("Vui lòng kiểm tra lại dữ liệu đầu vào.");
                return;
            }
            // Kiểm tra xem selectedOrder và selectedBookId có đã được khởi tạo chưa
            if (selectedOrder <= 0) {
                showAlert("Vui lòng chọn đơn hàng và sản phẩm trước khi thêm.");
                return;
            }
            // Thêm order detail vào cơ sở dữ liệu
            OrderDetailDAO.addOrderDetail(new OrderItems(orderItemId, selectedOrder, bookid, quantityItems, 0, "", 0));

            // Hiển thị thông báo thành công
            showAlert("Đã thêm chi tiết đơn hàng.");
            if (selectedOrder != -1) {
                List<OrderItems> orderItems = OrderDetailDAO.getAllOrderDetails();
                List<OrderItems> matchingOrderItems = OrderDetailDAO.searchOrderDetails(orderItems, selectedOrder);

                // Cập nhật TableView với kết quả tìm kiếm
                orderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                orderItemTableView.getSortOrder().add(orderItemIDColumn);

                double totalAmount = 0;
                for (OrderItems orderItem : matchingOrderItems) {
                    totalAmount += orderItem.getSubtotal();
                }
                sumTotalAmount1.setText(String.valueOf(totalAmount));
            }
            List<Order> orders = OrderDAO.getAllOrders();
            orderTableView.setItems(FXCollections.observableList(orders));
        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ nếu nhập liệu không phải là số
            showAlert("Vui lòng nhập số cho số lượng và mã sản phẩm.");
        } catch (Exception ex) {
            // Xử lý ngoại lệ chung (ví dụ: lỗi cơ sở dữ liệu)
            ex.printStackTrace();
            showAlert("Đã xảy ra lỗi khi thêm chi tiết đơn hàng.");
        }
    }
    private int generateNewPurchaseOrderItems(int order_id) {
        List<Integer> orderItems = PurchaseOrderItemDAO.getAllPurchaseOrderItemIdByOrderId(order_id);

        int setNewOrderItemId = orderItems.size() + 1;
        int maxId = 0;

        for (int orderitem : orderItems) {
            if (orderitem > maxId) {
                maxId = orderitem;
            }
        }
        //System.out.println(maxId);
        if (setNewOrderItemId <= maxId) {
            // Tìm giá trị không trùng lặp từ 0 đến setIdNewCustomer
            for (int i = 1; i <= setNewOrderItemId; i++) {
                int finalI = i;
                boolean idInList = orderItems.stream()
                        .anyMatch(id -> id == finalI);
                if (!idInList) {
                    setNewOrderItemId = i;
                    break;
                }
            }
        }
        return setNewOrderItemId;
    }
    @FXML
    private void handleAddPurchaseOrderItems() {
        try {
            int orderItemId = generateNewPurchaseOrderItems(selectedPurchaseOrder);
            int bookid = BookDAO.getBookIdByName(bookNameComboBox1.getValue());
            int quantityItems = Integer.parseInt(quantityTextField1.getText());

            // Kiểm tra xem orderItemId và quantityItems có hợp lệ không
            if (orderItemId <= 0 || quantityItems <= 0) {
                // Hiển thị thông báo lỗi nếu dữ liệu không hợp lệ
                showAlert("Vui lòng kiểm tra lại dữ liệu đầu vào.");
                return;
            }
            // Kiểm tra xem selectedOrder và selectedBookId có đã được khởi tạo chưa
            if (selectedPurchaseOrder <= 0) {
                showAlert("Vui lòng chọn đơn hàng và sản phẩm trước khi thêm.");
                return;
            }
            // Thêm order detail vào cơ sở dữ liệu
            PurchaseOrderItemDAO.addOrderItems(new PurchaseOrderItems(orderItemId, selectedPurchaseOrder, bookid, quantityItems, 0, "", 0));

            // Hiển thị thông báo thành công
            showAlert("Đã thêm chi tiết đơn hàng.");
            if (selectedPurchaseOrder != -1) {
                List<PurchaseOrderItems> orderItems = PurchaseOrderItemDAO.getAllPurchaseOrderItems();
                List<PurchaseOrderItems> matchingOrderItems = PurchaseOrderItemDAO.searchPurchaseOrders(orderItems, selectedPurchaseOrder);

                // Cập nhật TableView với kết quả tìm kiếm
                purchaseOrderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                purchaseOrderItemTableView.getSortOrder().add(purchaseOrderItemIDColumn);

                double totalAmount = 0;
                for (PurchaseOrderItems orderItem : matchingOrderItems) {
                    totalAmount += orderItem.getTotal_amount();
                }
                sumTotalAmount1.setText(String.valueOf(totalAmount));
            }
            List<PurchaseOrder> orders = PurchaseOrderDAO.getAllPurchaseOrders();
            purchaseOrderTableView.setItems(FXCollections.observableList(orders));
        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ nếu nhập liệu không phải là số
            showAlert("Vui lòng nhập số cho số lượng và mã sản phẩm.");
        } catch (Exception ex) {
            // Xử lý ngoại lệ chung (ví dụ: lỗi cơ sở dữ liệu)
            ex.printStackTrace();
            showAlert("Đã xảy ra lỗi khi thêm chi tiết đơn hàng.");
        }
    }
    @FXML
    private void handleOpenCouponManage() {
        List<DiscountCoupon> discountCoupons = DiscountCouponDAO.getAllCoupons();
        discountCouponsTableView.setItems(FXCollections.observableList(discountCoupons));

        centerPane.setVisible(false);
        CustomerFigure.setVisible(false);
        OrderPane.setVisible(false);
    }
    @FXML
    private void handleAddCoupon() {
        // Tạo hộp thoại nhập thông tin mã giảm giá và tỷ lệ giảm giá
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Thêm Mã Giảm Giá");
        dialog.setHeaderText("Nhập thông tin mã giảm giá:");
        dialog.setContentText("Mã giảm giá:");

        // Hiển thị hộp thoại và chờ người dùng nhập thông tin
        Optional<String> result = dialog.showAndWait();

        // Kiểm tra xem người dùng đã nhập thông tin hay chưa
        result.ifPresent(couponId -> {
            // Kiểm tra nếu mã giảm giá đã tồn tại
            if (DiscountCouponDAO.isCouponExists(couponId)) {
                showAlert("Mã giảm giá đã tồn tại. Vui lòng chọn một mã khác.");
            } else {
                // Hiển thị hộp thoại khác để nhập tỷ lệ giảm giá
                TextInputDialog discountDialog = new TextInputDialog();
                discountDialog.setTitle("Thêm mã giảm giá");
                discountDialog.setHeaderText("Nhập tỷ lệ giảm giá (dạng số):");
                discountDialog.setContentText("Tỷ lệ giảm giá (%):");

                Optional<String> discountResult = discountDialog.showAndWait();

                discountResult.ifPresent(discountPercentage -> {
                    try {
                        double discount = Double.parseDouble(discountPercentage);
                        DiscountCoupon newCoupon = new DiscountCoupon(couponId, discount);

                        // Thêm mã giảm giá vào cơ sở dữ liệu
                        boolean added = DiscountCouponDAO.addCoupon(newCoupon);

                        if (added) {
                            showAlert("Thêm mã giảm giá thành công!");

                            List<DiscountCoupon> discountCoupons = DiscountCouponDAO.getAllCoupons();
                            discountCouponsTableView.setItems(FXCollections.observableList(discountCoupons));
                            List<String> couponIds = DiscountCouponDAO.getCouponIds();
                            orderCouponComboBox.setItems(FXCollections.observableArrayList(couponIds));
                        } else {
                            showAlert("Đã có lỗi xảy ra khi thêm mã giảm giá.");
                        }
                    } catch (NumberFormatException e) {
                        showAlert("Tỷ lệ giảm giá không hợp lệ. Vui lòng nhập một số.");
                    }
                });
            }
        });
    }
    @FXML
    private void handleAddUser() {
        try {
            // Lấy các giá trị từ các trường nhập liệu
            List<User> users = UserDAO.getAllUser();
            int userid = users.size() + 1;

            String name = userNameTX.getText();
            String username = userUsernameTX.getText();
            String pass = userPassTX.getText();
            String phone = userPhoneTX.getText();
            String email = userEmailTX.getText();
            String permission = userPerCB.getValue();

            // Kiểm tra xem các giá trị đã nhập liệu đầy đủ chưa
            if (username.isEmpty() || pass.isEmpty() || permission.isEmpty()) {
                showAlert("Vui lòng điền đầy đủ thông tin");
                return; // Không thực hiện nếu có lỗi
            }
            if (UserDAO.isUserExists(username)) {
                showAlert("Tên tài khoản này đã tồn tại.");
                return;
            }
            User newUser = new User(userid, username, pass, name, phone, email, permission);
            UserDAO.insertUser(newUser);

            List<User> newusers = UserDAO.getAllUser();
            userTableView.setItems(FXCollections.observableList(newusers));
            // Hiển thị thông báo thành công
            showAlert("Thêm nhân viên thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra ngoại lệ nếu có lỗi khác
            showAlert("Đã có lỗi xảy ra.");
        }
    }
    @FXML
    private void handleDeleteCoupon() {
        if (!selectedCoupon.isEmpty()) { // Kiểm tra xem đã chọn mã giảm giá để xóa hay chưa
            // Tạo cửa sổ xác nhận xóa
            Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDelete.setTitle("Xác nhận xóa");
            confirmDelete.setHeaderText(null);
            confirmDelete.setContentText("Bạn có chắc chắn muốn xóa mã " + selectedCoupon + " không?");

            // Thêm các nút "Có" và "Không"
            ButtonType buttonYes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmDelete.getButtonTypes().setAll(buttonYes, buttonNo);

            // Hiển thị cửa sổ xác nhận và chờ người dùng lựa chọn
            Optional<ButtonType> result = confirmDelete.showAndWait();

            if (result.isPresent() && result.get() == buttonYes) {
                // Nếu người dùng chọn "Có", thực hiện xóa
                DiscountCouponDAO.deleteCoupon(selectedCoupon);

                // Xóa mã giảm giá khỏi TableView
                List<DiscountCoupon> discountCoupons = DiscountCouponDAO.getAllCoupons();
                discountCouponsTableView.setItems(FXCollections.observableList(discountCoupons));

                // Hiển thị thông báo xóa thành công
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đã xóa thành công mã giảm giá " + selectedCoupon);
                successAlert.showAndWait();

                selectedCoupon = ""; // Đặt lại selectedCoupon sau khi xóa
            }
        }
    }
    @FXML
    private void handleDeleteOrder() {
        if (selectedOrder != -1) {
            // Tạo cửa sổ xác nhận xóa
            Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDelete.setTitle("Xác nhận xóa");
            confirmDelete.setHeaderText(null);
            confirmDelete.setContentText("Bạn có chắc chắn muốn xóa đơn hàng #" + selectedOrder + " không?");

            // Thêm các nút "Có" và "Không"
            ButtonType buttonYes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmDelete.getButtonTypes().setAll(buttonYes, buttonNo);

            // Hiển thị cửa sổ xác nhận và chờ người dùng lựa chọn
            Optional<ButtonType> result = confirmDelete.showAndWait();

            if (result.isPresent() && result.get() == buttonYes) {
                // Nếu người dùng chọn "Có", thực hiện xóa

                OrderDAO orderDAO = new OrderDAO();
                orderDAO.deleteOrder(selectedOrder);

                // Xóa khách hàng khỏi TableView
                if (selectedOrder != -1) {
                    List<OrderItems> orderItems = OrderDetailDAO.getAllOrderDetails();
                    List<OrderItems> matchingOrderItems = OrderDetailDAO.searchOrderDetails(orderItems, selectedOrder);

                    // Cập nhật TableView với kết quả tìm kiếm
                    orderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                    orderItemTableView.getSortOrder().add(orderItemIDColumn);
                }
                List<Order> orders = OrderDAO.getAllOrders();
                orderTableView.setItems(FXCollections.observableList(orders));

                // Hiển thị thông báo xóa thành công
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đã xóa thành công đơn hàng #" + selectedOrder);
                successAlert.showAndWait();

                selectedOrder = -1;
            }
        }
    }
    @FXML
    private void handleDeletePurchaseOrder() {
        if (selectedPurchaseOrder != -1) {
            // Tạo cửa sổ xác nhận xóa
            Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDelete.setTitle("Xác nhận xóa");
            confirmDelete.setHeaderText(null);
            confirmDelete.setContentText("Bạn có chắc chắn muốn xóa đơn hàng #" + selectedPurchaseOrder + " không?");

            // Thêm các nút "Có" và "Không"
            ButtonType buttonYes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmDelete.getButtonTypes().setAll(buttonYes, buttonNo);

            // Hiển thị cửa sổ xác nhận và chờ người dùng lựa chọn
            Optional<ButtonType> result = confirmDelete.showAndWait();

            if (result.isPresent() && result.get() == buttonYes) {
                // Nếu người dùng chọn "Có", thực hiện xóa

                PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAO();
                purchaseOrderDAO.deletePuchaseOrder(selectedPurchaseOrder);

                // Xóa khách hàng khỏi TableView
                if (selectedPurchaseOrder != -1) {
                    List<PurchaseOrderItems> purchaseOrderItems = PurchaseOrderItemDAO.getAllPurchaseOrderItems();
                    List<PurchaseOrderItems> matchingOrderItems = PurchaseOrderItemDAO.searchPurchaseOrders(purchaseOrderItems, selectedPurchaseOrder);

                    // Cập nhật TableView với kết quả tìm kiếm
                    purchaseOrderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                    purchaseOrderItemTableView.getSortOrder().add(purchaseOrderItemIDColumn);
                }
                List<PurchaseOrder> purchaseOrders = PurchaseOrderDAO.getAllPurchaseOrders();
                purchaseOrderTableView.setItems(FXCollections.observableList(purchaseOrders));

                // Hiển thị thông báo xóa thành công
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đã xóa thành công đơn hàng #" + selectedPurchaseOrder);
                successAlert.showAndWait();

                selectedPurchaseOrder = -1;
            }
        }
    }
    @FXML
    private void handleDeletePurchaseOrderItem() {
        if (selectedPurchaseOrderItem != -1) {
            // Tạo cửa sổ xác nhận xóa
            Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDelete.setTitle("Xác nhận xóa");
            confirmDelete.setHeaderText(null);
            confirmDelete.setContentText("Bạn có chắc chắn muốn xóa đơn hàng #" + selectedPurchaseOrderItem + " không?");

            // Thêm các nút "Có" và "Không"
            ButtonType buttonYes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmDelete.getButtonTypes().setAll(buttonYes, buttonNo);

            // Hiển thị cửa sổ xác nhận và chờ người dùng lựa chọn
            Optional<ButtonType> result = confirmDelete.showAndWait();

            if (result.isPresent() && result.get() == buttonYes) {
                // Nếu người dùng chọn "Có", thực hiện xóa

                PurchaseOrderItemDAO purchaseOrderItemDAO = new PurchaseOrderItemDAO();
                purchaseOrderItemDAO.deletePurchaseOrderItem(selectedPurchaseOrderItem);

                // Hiển thị thông báo xóa thành công
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đã xóa thành công đơn hàng #" + selectedPurchaseOrderItem);

                if (selectedPurchaseOrderItem != -1) {
                    List<PurchaseOrderItems> orderItems = PurchaseOrderItemDAO.getAllPurchaseOrderItems();
                    List<PurchaseOrderItems> matchingOrderItems = PurchaseOrderItemDAO.searchPurchaseOrders(orderItems, selectedPurchaseOrder);

                    // Cập nhật TableView với kết quả tìm kiếm
                    purchaseOrderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                    purchaseOrderItemTableView.getSortOrder().add(purchaseOrderItemIDColumn);
                }

                successAlert.showAndWait();
                selectedPurchaseOrderItem = -1;
            }
        }
    }
    private int generateNewOrder() {
        List<Order> orders = OrderDAO.getAllOrders();
        int setNewOrderId = orders.size() + 1;
        int maxId = 0;

        for (Order order : orders) {
            if (order.getOrderId() > maxId) {
                maxId = order.getOrderId();
            }
        }
        //System.out.println(maxId);
        if (setNewOrderId <= maxId) {
            // Tìm giá trị không trùng lặp từ 0 đến setIdNewCustomer
            for (int i = 1; i <= setNewOrderId; i++) {
                int finalI = i;
                boolean idInList = orders.stream()
                        .map(Order::getOrderId)
                        .anyMatch(id -> id == finalI);
                if (!idInList) {
                    setNewOrderId = i;
                    break;
                }
            }
        }
        return setNewOrderId;
    }
    @FXML
    private void handleAddOrder() {
        try {
            // Lấy các giá trị từ các trường nhập liệu
            int orderId = generateNewOrder();
//            System.out.println(orderId);
            int userid = userCurrent;
            int customerId = Integer.parseInt(String.valueOf(orderCustomerNameComboBox.getValue()));
            String selectedCoupon = orderCouponComboBox.getValue();
            LocalDate selectedOrderDate = orderDatePicker.getValue();
            Date orderDate = java.sql.Date.valueOf(selectedOrderDate);
            double orderTotalAmount = 0;

            // Kiểm tra xem các giá trị đã nhập liệu đầy đủ chưa
            if (orderId <= 0 || customerId <= 0 || selectedCoupon == null || orderDate == null) {
                showAlert("Vui lòng điền đầy đủ thông tin đơn hàng.");
                return; // Không thực hiện thêm đơn hàng nếu có lỗi
            }

            // Tạo đối tượng Order với thông tin từ các trường nhập liệu
            Order newOrder = new Order(orderId, userid, customerId, selectedCoupon, orderDate, "", "",orderTotalAmount, "");

            // Thêm đơn hàng vào cơ sở dữ liệu
            OrderDAO.addOrder(newOrder);

            // Cập nhật TableView nếu cần
            if (selectedOrder != -1) {
                List<OrderItems> orderItems = OrderDetailDAO.getAllOrderDetails();
                List<OrderItems> matchingOrderItems = OrderDetailDAO.searchOrderDetails(orderItems, selectedOrder);

                // Cập nhật TableView với kết quả tìm kiếm
                orderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                orderItemTableView.getSortOrder().add(orderItemIDColumn);
            }
            List<Order> orders = OrderDAO.getAllOrders();
            orderTableView.setItems(FXCollections.observableList(orders));
            // Hiển thị thông báo thành công
            showAlert("Thêm đơn hàng thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra ngoại lệ nếu có lỗi khác
            showAlert("Đã có lỗi xảy ra khi thêm đơn hàng.");
        }
    }
    private int generateNewPurchaseOrder() {
        List<PurchaseOrder> orders = PurchaseOrderDAO.getAllPurchaseOrders();
        int setNewOrderId = orders.size() + 1;
        int maxId = 0;

        for (PurchaseOrder order : orders) {
            if (order.getPurchaseOrder_id() > maxId) {
                maxId = order.getPurchaseOrder_id();
            }
        }
        //System.out.println(maxId);
        if (setNewOrderId <= maxId) {
            // Tìm giá trị không trùng lặp từ 0 đến setIdNewCustomer
            for (int i = 1; i <= setNewOrderId; i++) {
                int finalI = i;
                boolean idInList = orders.stream()
                        .map(PurchaseOrder::getPurchaseOrder_id)
                        .anyMatch(id -> id == finalI);
                if (!idInList) {
                    setNewOrderId = i;
                    break;
                }
            }
        }
        return setNewOrderId;
    }
    @FXML
    private void handleAddPurchaseOrder() {
        try {
            // Lấy các giá trị từ các trường nhập liệu
            int orderId = generateNewPurchaseOrder();
            int userid = userCurrent;
            int publiserid = Integer.parseInt(String.valueOf(getPublisherIdByName(purchaseOrderPublisherNameComboBox.getValue())));
            LocalDate selectedOrderDate = purchaseOrderDatePicker.getValue();
            Date orderDate = java.sql.Date.valueOf(selectedOrderDate);
            double orderTotalAmount = 0;

            // Kiểm tra xem các giá trị đã nhập liệu đầy đủ chưa
            if (orderId <= 0 || publiserid <= 0 || orderDate == null) {
                showAlert("Vui lòng điền đầy đủ thông tin đơn hàng.");
                return; // Không thực hiện thêm đơn hàng nếu có lỗi
            }

            // Tạo đối tượng Order với thông tin từ các trường nhập liệu
            PurchaseOrder newPurchaseOrder = new PurchaseOrder(orderId, userid, publiserid, orderDate);

            // Thêm đơn hàng vào cơ sở dữ liệu
            PurchaseOrderDAO.addPuchaseOrder(newPurchaseOrder);

            // Cập nhật TableView nếu cần
            if (selectedPurchaseOrder != -1) {
                List<PurchaseOrderItems> orderItems = PurchaseOrderItemDAO.getAllPurchaseOrderItems();
                List<PurchaseOrderItems> matchingOrderItems = PurchaseOrderItemDAO.searchPurchaseOrders(orderItems, selectedPurchaseOrder);

                // Cập nhật TableView với kết quả tìm kiếm
                purchaseOrderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                purchaseOrderItemTableView.getSortOrder().add(purchaseOrderItemIDColumn);
            }
            List<PurchaseOrder> orders = PurchaseOrderDAO.getAllPurchaseOrders();
            purchaseOrderTableView.setItems(FXCollections.observableList(orders));
            // Hiển thị thông báo thành công
            showAlert("Thêm đơn hàng thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra ngoại lệ nếu có lỗi khác
            showAlert("Đã có lỗi xảy ra khi thêm đơn hàng.");
        }
    }
    @FXML
    private void handleDeleteOrderItems() {
        if (selectedOrderItem != -1) {
            // Tạo cửa sổ xác nhận xóa
            Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDelete.setTitle("Xác nhận xóa");
            confirmDelete.setHeaderText(null);
            confirmDelete.setContentText("Bạn có chắc chắn muốn xóa đơn hàng #" + selectedOrderItem + " không?");

            // Thêm các nút "Có" và "Không"
            ButtonType buttonYes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmDelete.getButtonTypes().setAll(buttonYes, buttonNo);

            // Hiển thị cửa sổ xác nhận và chờ người dùng lựa chọn
            Optional<ButtonType> result = confirmDelete.showAndWait();

            if (result.isPresent() && result.get() == buttonYes) {
                // Nếu người dùng chọn "Có", thực hiện xóa

                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                orderDetailDAO.deleteOrderDetail(selectedOrderItem);

                // Xóa khoi TableView
                if (selectedOrder != -1) {
                    List<OrderItems> orderItems = OrderDetailDAO.getAllOrderDetails();
                    List<OrderItems> matchingOrderItems = OrderDetailDAO.searchOrderDetails(orderItems, selectedOrder);

                    // Cập nhật TableView với kết quả tìm kiếm
                    orderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                    orderItemTableView.getSortOrder().add(orderItemIDColumn);
                }
                List<Order> orders = OrderDAO.getAllOrders();
                orderTableView.setItems(FXCollections.observableList(orders));

                // Hiển thị thông báo xóa thành công
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đã xóa thành công đơn hàng #" + selectedOrderItem);
                successAlert.showAndWait();

                selectedOrderItem = -1; // Đặt lại selectedCustomerId sau khi xóa
            }
        }
    }
    @FXML
    private void handleDeleteUser() {
        if (selectedUserId != -1) {
            // Tạo cửa sổ xác nhận xóa
            Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDelete.setTitle("Xác nhận xóa");
            confirmDelete.setHeaderText(null);
            confirmDelete.setContentText("Bạn có chắc chắn muốn xóa nhân viên #" + selectedUserId + " không?");

            // Thêm các nút "Có" và "Không"
            ButtonType buttonYes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmDelete.getButtonTypes().setAll(buttonYes, buttonNo);

            // Hiển thị cửa sổ xác nhận và chờ người dùng lựa chọn
            Optional<ButtonType> result = confirmDelete.showAndWait();

            if (result.isPresent() && result.get() == buttonYes) {
                // Nếu người dùng chọn "Có", thực hiện xóa
                UserDAO userDAO = new UserDAO();
                UserDAO.deleteUser(selectedUserId);

                // Xóa nhân viên khỏi TableView
                List<User> users = UserDAO.getAllUser();
                userTableView.setItems(FXCollections.observableList(users));

                // Hiển thị thông báo xóa thành công
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đã xóa thành công nhân viên #" + selectedUserId);
                successAlert.showAndWait();

                selectedUserId = -1; // Đặt lại selectedCoupon sau khi xóa
            }
        }
    }
    @FXML
    private void handleOpenOrderManagerPane() {
        centerPane.setVisible(false);
        CustomerFigure.setVisible(false);
        bookPane.setVisible(false);
        OrderPane.setVisible(true);
        purchaseOrderPane.setVisible(false);
        userSplitane.setVisible(false);


        if (selectedOrder != -1) {
            List<OrderItems> orderItems = OrderDetailDAO.getAllOrderDetails();
            List<OrderItems> matchingOrderItems = OrderDetailDAO.searchOrderDetails(orderItems, selectedOrder);

            // Cập nhật TableView với kết quả tìm kiếm
            orderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
            orderItemTableView.getSortOrder().add(orderItemIDColumn);
        }
        List<Order> orders = OrderDAO.getAllOrders();
        orderTableView.setItems(FXCollections.observableList(orders));
        List<Integer> customerIds = CustomerDAO.getCustomerIds();
        orderCustomerNameComboBox.setItems(FXCollections.observableArrayList(customerIds));
    }
    @FXML
    private void handleOpenPurchaseOrderManagerPane() {
        centerPane.setVisible(false);
        CustomerFigure.setVisible(false);
        bookPane.setVisible(false);
        OrderPane.setVisible(false);
        purchaseOrderPane.setVisible(true);
        userSplitane.setVisible(false);


        if (selectedPurchaseOrder != -1) {
            List<PurchaseOrderItems> orderItems = PurchaseOrderItemDAO.getAllPurchaseOrderItems();
            List<PurchaseOrderItems> matchingOrderItems = PurchaseOrderItemDAO.searchPurchaseOrders(orderItems, selectedOrder);

            // Cập nhật TableView với kết quả tìm kiếm
            purchaseOrderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
            purchaseOrderItemTableView.getSortOrder().add(purchaseOrderItemIDColumn);
        }
        List<PurchaseOrder> orders = PurchaseOrderDAO.getAllPurchaseOrders();
        purchaseOrderTableView.setItems(FXCollections.observableList(orders));
    }
    @FXML
    private void handleEditOrder() {
        try {
            int selectedOrderId = selectedOrder;
            String selectedCoupon = orderCouponComboBox.getValue();
            int selectedCustomerOrder = orderCustomerNameComboBox.getValue();
            LocalDate currentDate = LocalDate.now();
            orderDatePicker.setValue(currentDate);
            LocalDate selectedOrderDate = orderDatePicker.getValue();
            Date orderDate = java.sql.Date.valueOf(selectedOrderDate);

            // Kiểm tra xem selectedOrderItemId có hợp lệ không
            if (selectedOrderId < 1) {
                showAlert("Mã hóa đơn không hợp lệ. Vui lòng kiểm tra lại.");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }

            // Kiểm tra xem quantityItem có hợp lệ không
            if (selectedCoupon.isEmpty()) {
                showAlert("Vui lòng chọn mã giảm giá");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }

            if (selectedCustomerOrder < 1) {
                showAlert("Vui lòng chọn khách hàng");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }
            if (selectedOrderDate == null) {
                showAlert("Vui lòng chọn ngày tháng");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }
            // Gọi phương thức updateOrderDetail sau khi kiểm tra dữ liệu hợp lệ
            OrderDAO.updateOrder(new Order(selectedOrderId, selectedCustomerOrder, selectedCoupon, orderDate));
            showAlert("Sửa hóa đơn thành công!");
            if (selectedOrder != -1) {
                List<OrderItems> orderItems = OrderDetailDAO.getAllOrderDetails();
                List<OrderItems> matchingOrderItems = OrderDetailDAO.searchOrderDetails(orderItems, selectedOrder);

                // Cập nhật TableView với kết quả tìm kiếm
                orderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                orderItemTableView.getSortOrder().add(orderItemIDColumn);
            }
            List<Order> orders = OrderDAO.getAllOrders();
            orderTableView.setItems(FXCollections.observableList(orders));

        } catch (NumberFormatException e) {
            showAlert("Nhập dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
        }
    }
    @FXML
    private void handleEditPurchaseOrder() {
        try {
            int selectedPurchaseOrderId = selectedPurchaseOrder;
            int selectedPubliseridOrder = PublisherDAO.getPublisherIdByName(purchaseOrderPublisherNameComboBox.getValue());

            LocalDate selectedOrderDate = purchaseOrderDatePicker.getValue();
            Date orderDate = java.sql.Date.valueOf(selectedOrderDate);

            // Kiểm tra xem selectedOrderItemId có hợp lệ không
            if (selectedPurchaseOrderId < 1) {
                showAlert("Mã hóa đơn không hợp lệ. Vui lòng kiểm tra lại.");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }

            if (selectedPubliseridOrder < 1) {
                showAlert("Vui lòng chọn nhà xuất bản");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }
            if (selectedOrderDate == null) {
                showAlert("Vui lòng chọn ngày tháng");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }
            // Gọi phương thức updateOrderDetail sau khi kiểm tra dữ liệu hợp lệ
            PurchaseOrderDAO.updatePuchaseOrder(new PurchaseOrder(selectedPurchaseOrderId, selectedPubliseridOrder, orderDate));
            showAlert("Sửa hóa đơn thành công!");
            if (selectedPurchaseOrder != -1) {
                List<PurchaseOrderItems> orderItems = PurchaseOrderItemDAO.getAllPurchaseOrderItems();
                List<PurchaseOrderItems> matchingOrderItems = PurchaseOrderItemDAO.searchPurchaseOrders(orderItems, selectedOrder);

                // Cập nhật TableView với kết quả tìm kiếm
                purchaseOrderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                purchaseOrderItemTableView.getSortOrder().add(purchaseOrderItemIDColumn);
            }
            List<PurchaseOrder> orders = PurchaseOrderDAO.getAllPurchaseOrders();
            purchaseOrderTableView.setItems(FXCollections.observableList(orders));

        } catch (NumberFormatException e) {
            showAlert("Nhập dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
        }
    }
    @FXML
    private void handleEditUser() {
        try {
            String name = userNameTX.getText();
            String username = userUsernameTX.getText();
            String pass = userPassTX.getText();
            String phone = userPhoneTX.getText();
            String email = userEmailTX.getText();
            String permission = userPerCB.getValue();

            // Kiểm tra xem selectedOrderItemId có hợp lệ không
            if (selectedUserId < 1) {
                showAlert("Id nhân viên không hợp lệ. Vui lòng kiểm tra lại.");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }

            if (selectedUserId < 0) {
                showAlert("Vui lòng chọn nhân viên muốn sửa");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }
            if (UserDAO.isUserExists(username, selectedUserId)) {
                showAlert("Tên tài khoản này đã tồn tại.");
                return;
            }

            UserDAO.updateUser(new User(selectedUserId, username, pass, name, phone, email,permission));
            showAlert("Sửa thông tin nhân viên thành công!");

            List<User> users = UserDAO.getAllUser();
            userTableView.setItems(FXCollections.observableList(users));

        } catch (NumberFormatException e) {
            showAlert("Nhập dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
        }
    }
    @FXML
    private void handleEditOrderItems() {
        try {
            int selectedOrderItemId = selectedOrderItem;
            int quantityItem = Integer.parseInt(quantityTextField.getText());
            int bookid = PurchaseOrderItemDAO.getBookIdByName(bookNameComboBox.getValue());

            // Kiểm tra xem selectedOrderItemId có hợp lệ không
            if (selectedOrderItemId < 1) {
                showAlert("Mã sản phẩm không hợp lệ. Vui lòng kiểm tra lại.");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }

            // Kiểm tra xem quantityItem có hợp lệ không
            if (quantityItem < 1) {
                showAlert("Số lượng không hợp lệ. Vui lòng kiểm tra lại.");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }

            // Gọi phương thức updateOrderDetail sau khi kiểm tra dữ liệu hợp lệ
            OrderDetailDAO.updateOrderDetail(new OrderItems(selectedOrderItemId, selectedOrder, bookid, quantityItem, 0, "", 0));
            showAlert("Sửa hóa đơn thành công!");
            if (selectedOrder != -1) {
                List<OrderItems> orderItems = OrderDetailDAO.getAllOrderDetails();
                List<OrderItems> matchingOrderItems = OrderDetailDAO.searchOrderDetails(orderItems, selectedOrder);

                // Cập nhật TableView với kết quả tìm kiếm
                orderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                orderItemTableView.getSortOrder().add(orderItemIDColumn);
            }
            List<Order> orders = OrderDAO.getAllOrders();
            orderTableView.setItems(FXCollections.observableList(orders));

        } catch (NumberFormatException e) {
            showAlert("Nhập dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
        }
    }
    @FXML
    private void handleEditPurchaseOrderItems() {
        try {
            int selectedOrderItemId = selectedPurchaseOrderItem;
            int quantityItem = Integer.parseInt(quantityTextField1.getText());
            int bookid = PurchaseOrderItemDAO.getBookIdByName(bookNameComboBox1.getValue());

            // Kiểm tra xem selectedOrderItemId có hợp lệ không
            if (selectedOrderItemId < 1) {
                showAlert("Mã sản phẩm không hợp lệ. Vui lòng kiểm tra lại.");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }

            // Kiểm tra xem quantityItem có hợp lệ không
            if (quantityItem < 1) {
                showAlert("Số lượng không hợp lệ. Vui lòng kiểm tra lại.");
                return; // Kết thúc phương thức nếu dữ liệu không hợp lệ
            }

            // Gọi phương thức updateOrderDetail sau khi kiểm tra dữ liệu hợp lệ
            PurchaseOrderItemDAO.updatePurchaseOrderItem(new PurchaseOrderItems(selectedOrderItemId, selectedPurchaseOrderItem, bookid, quantityItem, 0, "", 0));
            showAlert("Sửa hóa đơn thành công!");
            if (selectedPurchaseOrderItem != -1) {
                List<PurchaseOrderItems> orderItems = PurchaseOrderItemDAO.getAllPurchaseOrderItems();
                List<PurchaseOrderItems> matchingOrderItems = PurchaseOrderItemDAO.searchPurchaseOrders(orderItems, selectedPurchaseOrder);

                // Cập nhật TableView với kết quả tìm kiếm
                purchaseOrderItemTableView.setItems(FXCollections.observableList(matchingOrderItems));
                purchaseOrderItemTableView.getSortOrder().add(purchaseOrderItemIDColumn);
            }
            List<PurchaseOrder> orders = PurchaseOrderDAO.getAllPurchaseOrders();
            purchaseOrderTableView.setItems(FXCollections.observableList(orders));

        } catch (NumberFormatException e) {
            showAlert("Nhập dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
        }
    }
}