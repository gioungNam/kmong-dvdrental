package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import config.DatabaseConnection;

public class RentalDAO {

	public int createRental(String memberId) {
        String query = "INSERT INTO rental23 (member_id, rental_date) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            statement.setString(1, memberId);
            statement.setTimestamp(2, new Timestamp(new Date().getTime()));
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
        String query = "INSERT INTO rental_detail23 (rental_id, dvd_id, is_returned) VALUES (?, ?, FALSE)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, rentalId);
            statement.setInt(2, dvdId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
 // 렌탈 이력 조회 메서드
    public List<RentalDetail> getAllRentals() {
    	List<RentalDetail> rentalHistory = new ArrayList<>();
        String query = "SELECT rd.rental_item_id, r.rental_id, r.rental_date, d.title, r.member_id, rd.is_returned, rd.return_date " +
                       "FROM rental_detail23 rd " +
                       "JOIN rental23 r ON rd.rental_id = r.rental_id " +
                       "JOIN dvd23 d ON rd.dvd_id = d.id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                RentalDetail rentalDetail = new RentalDetail();
                rentalDetail.setRentalItemId(rs.getInt("rental_item_id"));
                rentalDetail.setRentalId(rs.getInt("rental_id"));
                rentalDetail.setRentalDate(rs.getTimestamp("rental_date"));
                rentalDetail.setDvdTitle(rs.getString("title"));
                rentalDetail.setMemberId(rs.getString("member_id"));
                rentalDetail.setReturned(rs.getBoolean("is_returned"));
                rentalDetail.setReturnDate(rs.getTimestamp("return_date")); // 반납일자 설정
                
                rentalHistory.add(rentalDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rentalHistory;
    }

	public void returnRentalItem(int rentalItemId) {
		// 먼저, rental_item_id로 dvd_id를 가져옴
        String getDvdIdQuery = "SELECT dvd_id FROM rental_detail23 WHERE rental_item_id = ?";
        String returnRentalQuery = "UPDATE rental_detail23 SET is_returned = TRUE, return_date = ? WHERE rental_item_id = ?";
        String updateDvdQuery = "UPDATE dvd23 SET is_rented = FALSE WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement getDvdIdStatement = connection.prepareStatement(getDvdIdQuery);
             PreparedStatement returnRentalStatement = connection.prepareStatement(returnRentalQuery);
             PreparedStatement updateDvdStatement = connection.prepareStatement(updateDvdQuery)) {

            // dvd_id 조회
            getDvdIdStatement.setInt(1, rentalItemId);
            try (ResultSet rs = getDvdIdStatement.executeQuery()) {
                if (rs.next()) {
                    int dvdId = rs.getInt("dvd_id");

                    // rental_detail23 테이블에서 반납 처리
                    returnRentalStatement.setTimestamp(1, new Timestamp(new Date().getTime())); // 현재 날짜와 시간 설정
                    returnRentalStatement.setInt(2, rentalItemId);
                    returnRentalStatement.executeUpdate();

                    // dvd23 테이블에서 is_rented 값을 FALSE로 업데이트
                    updateDvdStatement.setInt(1, dvdId);
                    updateDvdStatement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

		
	}
	
	// DVD가 현재 렌탈 중인지 확인하는 메서드
    public boolean isDvdCurrentlyRented(int dvdId) {
        String query = "SELECT COUNT(*) FROM rental_detail23 WHERE dvd_id = ? AND is_returned = FALSE";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dvdId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // 렌탈 중인 이력이 있으면 true 반환
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
