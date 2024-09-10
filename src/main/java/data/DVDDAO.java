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
        String query = "SELECT * FROM dvd";
        
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
        String query = "SELECT * FROM dvd WHERE title LIKE ?";
        
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
        String query = "UPDATE dvd SET is_rented = TRUE WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, dvdId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
