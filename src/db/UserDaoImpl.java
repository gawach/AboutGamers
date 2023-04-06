package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.User;

public class UserDaoImpl implements UserDao {
	public static final String INSERT = "INSERT INTO user(name_user, email_user, password_user, level_user) VALUES(?, ?, ?, ?)";
	public static final String SELECT = "SELECT * FROM user ";
	public static final String UPDATE = "UPDATE user SET ";

	@Override
	public User signup(Connection con, String...strings) throws SQLException {
		String sql = INSERT;
		int level_user = 1;
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, strings[1]);
		pstmt.setString(2, strings[2]);
		pstmt.setString(3, strings[3]);
		pstmt.setInt(4, level_user);
		pstmt.executeUpdate();

		sql = SELECT + "WHERE email_user = ?";
		PreparedStatement pstmt2 = con.prepareStatement(sql);
		pstmt2.setString(1, strings[2]);
		ResultSet rs = pstmt2.executeQuery();
		User signupUser = getAll(rs).get(0);
		pstmt.close();
		return signupUser;
	}

	@Override
	public User login(Connection con, String...strings) throws SQLException, IndexOutOfBoundsException {
		User user = new User();
		String sql = SELECT + "WHERE email_user = ? AND password_user = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, strings[1]);
		pstmt.setString(2, strings[2]);
		ResultSet rs = pstmt.executeQuery();
		user = getAll(rs).get(0);
		userLevelUp(con, user.getPassword_user());
		pstmt.close();
		return user;
	}

	@Override
	public void userLevelUp(Connection con, String password) throws SQLException {
		String sql = UPDATE + "level_user" + " = ? " + "WHERE password_user = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		if (password.equals("admin")) {
			pstmt.setInt(1, 2);
		} else {
			pstmt.setInt(1, 1);
		}
		pstmt.setString(2, password);
		pstmt.executeUpdate();
		pstmt.close();
	}

	@Override
	public void logout(Connection con, int id) throws SQLException {
	    String sql = UPDATE + "level_user = 0 WHERE id_user = ?";
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, id);
	    pstmt.executeUpdate();
		pstmt.close();
	}

	@Override
	public ArrayList<User> getAll(ResultSet rs) throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		while(rs.next()) {
			User u = new User();
			u.setId_user(rs.getInt("id_user"));
			u.setName_user(rs.getString("name_user"));
			u.setEmail_user(rs.getString("email_user"));
			u.setPassword_user(rs.getString("password_user"));
			u.setLevel_user(rs.getInt("level_user"));
			users.add(u);
		}
		return users;
	}

}