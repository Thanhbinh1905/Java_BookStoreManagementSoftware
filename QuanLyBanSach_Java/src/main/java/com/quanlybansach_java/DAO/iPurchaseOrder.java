package com.quanlybansach_java.DAO;

public interface iPurchaseOrder {
    String GETPURCHASEORDERS = "SELECT\n" +
            "PurchaseOrders.purchaseOrder_id,\n" +
            "PurchaseOrders.user_id,\n" +
            "PurchaseOrders.publisher_id,\n" +
            "PurchaseOrders.purchaseOrder_date, \n" +
            "Publishers.publisher_name,\n" +
            "SUM(PurchaseOrderItems.total_amount) AS total_amount,\n" +
            "Users.Username\n" +
            "FROM PurchaseOrders\n" +
            "INNER JOIN Publishers ON PurchaseOrders.publisher_id = Publishers.publisher_id\n" +
            "INNER JOIN PurchaseOrderItems ON PurchaseOrders.purchaseOrder_id = PurchaseOrderItems.purchaseOrder_id\n" +
            "INNER JOIN Users ON Users.user_id = PurchaseOrders.user_id\n" +
            "GROUP BY\n" +
            "PurchaseOrders.purchaseOrder_id,\n" +
            "PurchaseOrders.user_id,\n" +
            "PurchaseOrders.publisher_id,\n" +
            "PurchaseOrders.purchaseOrder_date,\n" +
            "Publishers.publisher_name,\n" +
            "Users.Username";
    String INSERT_PURCHASEORDER = "INSERT INTO PurchaseOrders (purchaseOrder_id, user_id, publisher_id, purchaseOrder_date) " +
            "VALUES (?, ?, ?, ?)";
    String DELETE_PURCHASEORDER = "DELETE FROM PurchaseOrders WHERE purchaseOrder_id = ?";
    String PURCHASEORDER_UPDATE = "UPDATE PurchaseOrders SET publisher_id=?, purchaseOrder_date=? " +
            "WHERE purchaseOrder_id=?";
//    String SHOWCUSTOMERORDER = "SELECT Orders.order_id, Orders.coupon_id, Orders.Order_date, " +
//            "SUM(OrderItems.total_amount - (OrderItems.total_amount * Discount_Coupons.discount_percentage / 100)) AS total_amount " +
//            "FROM Orders " +
//            "INNER JOIN OrderItems ON Orders.order_id = OrderItems.order_id " +
//            "INNER JOIN Discount_Coupons ON Orders.coupon_id = Discount_Coupons.coupon_id " +
//            "WHERE Orders.customer_id = ? " +
//            "GROUP BY Orders.order_id, Orders.coupon_id, Orders.order_date";
}
