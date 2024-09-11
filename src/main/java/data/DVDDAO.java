package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;

public class DVDDAO {
	
	// 모든 DVD를 조회하는 메서드
    public List<DVD> getAllDVDs() {
        List<DVD> dvdList = new ArrayList<>();
        String query = "SELECT * FROM dvd23";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            
            while (rs.next()) {
                DVD dvd = new DVD();
                dvd.setId(rs.getInt("id"));
                dvd.setTitle(rs.getString("title"));
                dvd.setGenre(rs.getString("genre"));
                dvd.setLeadActor(rs.getString("lead_actor"));
                dvd.setRented(rs.getBoolean("is_rented"));
                
                dvdList.add(dvd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dvdList;
    }

    // 제목으로 DVD를 검색하는 메서드
    public List<DVD> searchDVDsByTitle(String title) {
        List<DVD> dvdList = new ArrayList<>();
        String query = "SELECT * FROM dvd23 WHERE title LIKE ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, "%" + title + "%");
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    DVD dvd = new DVD();
                    dvd.setId(rs.getInt("id"));
                    dvd.setTitle(rs.getString("title"));
                    dvd.setGenre(rs.getString("genre"));
                    dvd.setLeadActor(rs.getString("lead_actor"));
                    dvd.setRented(rs.getBoolean("is_rented"));
                    
                    dvdList.add(dvd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dvdList;
    }

    // DVD 대출 상태를 업데이트하는 메서드
    public void rentDVD(int dvdId) {
        String query = "UPDATE dvd23 SET is_rented = TRUE WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, dvdId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // DVD 삭제 메서드
    public void deleteDVD(int dvdId) {
        String query = "DELETE FROM dvd23 WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, dvdId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void insertDVD(String title, String genre, String leadActor) {
		String query = "INSERT INTO dvd23 (title, genre, lead_actor, is_rented) VALUES (?, ?, ?, FALSE)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, title);
            statement.setString(2, genre);
            statement.setString(3, leadActor);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	public boolean isTitleDuplicated(String title) {
		String query = "SELECT COUNT(*) FROM dvd23 WHERE title = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, title);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // 제목이 존재하면 true 반환
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
	}
}
