package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import config.DatabaseConnection;

public class RentalDAO {

	public int createRental(int memberId) {
        String query = "INSERT INTO rental (member_id, rental_date) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, memberId);
            statement.setDate(2, new java.sql.Date(new Date().getTime()));
            statement.executeUpdate();
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // 생성된 rental_id 반환
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 실패 시 -1 반환
    }

    public void addRentalItem(int rentalId, int dvdId) {
        String query = "INSERT INTO rental_item (rental_id, dvd_id, is_returned) VALUES (?, ?, FALSE)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, rentalId);
            statement.setInt(2, dvdId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
