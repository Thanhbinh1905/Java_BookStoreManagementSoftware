package com.quanlybansach_java.DAO;

import com.quanlybansach_java.DATABASE.ConnectDatabase;
import com.quanlybansach_java.Model.DiscountCoupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountCouponDAO {
    public static List<DiscountCoupon> getAllCoupons() {
        List<DiscountCoupon> coupons = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Discount_Coupons");
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                String coupon_id = resultSet.getString("coupon_id");
                double discount_percentage = resultSet.getDouble("discount_percentage");

                coupons.add(new DiscountCoupon(coupon_id, discount_percentage));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coupons;
    }
    public static List<String> getCouponIds() {
        List<String> couponids = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT coupon_id FROM Discount_Coupons");
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                String coupon_id = resultSet.getString("coupon_id");
                couponids.add(coupon_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return couponids;
    }
    public static boolean isCouponExists(String couponIdToCheck) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS count FROM Discount_Coupons WHERE coupon_id = ?")) {

            stmt.setString(1, couponIdToCheck);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0; // Trả về true nếu mã giảm giá đã tồn tại
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Trả về false nếu có lỗi hoặc mã giảm giá không tồn tại
    }
    public static boolean addCoupon(DiscountCoupon coupon) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO Discount_Coupons (coupon_id, discount_percentage) VALUES (?, ?)")) {

            stmt.setString(1, coupon.getCouponId());
            stmt.setDouble(2, coupon.getDiscountPercentage());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteCoupon(String couponId) {
        String sql = "DELETE FROM Discount_Coupons WHERE coupon_id = ?";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, couponId);
            int rowsAffected = stmt.executeUpdate();

            // Nếu có ít nhất một dòng bị ảnh hưởng (đã xóa thành công)
            if (rowsAffected > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Xóa không thành công hoặc mã giảm giá không tồn tại
    }

}
