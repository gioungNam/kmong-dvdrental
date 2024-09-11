package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;

public class MemberDAO {

	public Member getMemberById(String memberId) {
        String query = "SELECT * FROM member23 WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, memberId);
            
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Member member = new Member();
                    member.setId(rs.getString("id"));
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
	
	// 회원 등록 메서드 (id, name, email)
    public void registerMember(String id, String name, String email) {
        String query = "INSERT INTO member23 (id, name, email) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 회원 조회 메서드 수정
    public List<Member> getAllMembers() {
        List<Member> memberList = new ArrayList<>();
        String query = "SELECT * FROM member23";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getString("id"));  // id 타입을 String으로 변경
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                
                memberList.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return memberList;
    }
	
    // 회원 삭제 메서드 
    public void deleteMember(String memberId) {
        String query = "DELETE FROM member23 WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, memberId);  // id 타입을 String으로 처리
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
