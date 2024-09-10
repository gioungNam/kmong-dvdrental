package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DatabaseConnection;

public class MemberDAO {

	public Member getMemberById(int memberId) {
        String query = "SELECT * FROM member WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, memberId);
            
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Member member = new Member();
                    member.setId(rs.getInt("id"));
                    member.setName(rs.getString("name"));
                    member.setEmail(rs.getString("email"));
                    return member;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 비회원일 경우 null 반환
    }
}
