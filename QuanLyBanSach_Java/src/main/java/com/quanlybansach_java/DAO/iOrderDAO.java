package com.quanlybansach_java.DAO;

public interface iOrderDAO {
    String GETORDERS = "SELECT \n" +
            "    Orders.order_id, \n" +
            "    Orders.user_id, \n" +
            "    Orders.customer_id, \n" +
            "    Orders.coupon_id, \n" +
            "    Orders.order_date, \n" +
            "    Customers.first_name, \n" +
            "    Customers.last_name, \n" +
            "    SUM(OrderItems.total_amount - (OrderItems.total_amount * Discount_Coupons.discount_percentage / 100)) AS total_amount, \n" +
            "    Users.Username\n" +
            "FROM Orders \n" +
            "INNER JOIN Customers ON Orders.customer_id = Customers.customer_id \n" +
            "INNER JOIN OrderItems ON Orders.order_id = OrderItems.order_id \n" +
            "INNER JOIN Discount_Coupons ON Orders.coupon_id = Discount_Coupons.coupon_id \n" +
            "INNER JOIN Users ON Users.user_id = Orders.user_id\n" +
            "GROUP BY \n" +
            "    Orders.order_id, \n" +
            "    Orders.user_id,\n" +
            "    Orders.customer_id, \n" +
            "    Orders.coupon_id, \n" +
            "    Orders.order_date,\n" +
            "    Customers.first_name, \n" +
            "    Customers.last_name, \n" +
            "    Users.Username";
    String INSERT_ORDER = "INSERT INTO Orders (order_id, user_id, customer_id, coupon_id, order_date) " +
            "VALUES (?, ?, ?, ?, ?)";
    String DELETE_ORDER = "DELETE FROM Orders WHERE order_id = ?";
    String ORDER_UPDATE = "UPDATE Orders SET Customer_id=?, Coupon_id=?, Order_Date=? " +
            "WHERE Order_id=?";
    String SHOWCUSTOMERORDER = "SELECT Orders.order_id, Orders.coupon_id, Orders.Order_date, " +
            "SUM(OrderItems.total_amount - (OrderItems.total_amount * Discount_Coupons.discount_percentage / 100)) AS total_amount " +
            "FROM Orders " +
            "INNER JOIN OrderItems ON Orders.order_id = OrderItems.order_id " +
            "INNER JOIN Discount_Coupons ON Orders.coupon_id = Discount_Coupons.coupon_id " +
            "WHERE Orders.customer_id = ? " +
            "GROUP BY Orders.order_id, Orders.coupon_id, Orders.order_date";
}
