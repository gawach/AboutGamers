package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Gamer;


public class GamerDaoImpl implements GamerDao {

	public static final String SELECT = "SELECT * FROM gamer ";
	public static final String INSERT = "INSERT INTO gamer(name_gamer, team_name_gamer, settings_gamer, profile_gamer) VALUES(?, ?, ?, ?)";
	public static final String UPDATE = "UPDATE gamer SET ";
	public static final String DELETE = "DELETE FROM gamer WHERE ";

	@Override
	public ArrayList<Gamer> findAll(Connection con) throws SQLException {
		ArrayList<Gamer> gamers = new ArrayList<>();
		String sql = SELECT;
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		gamers = getAll(rs);
		pstmt.close();
		return gamers;
	}

	@Override
	public Gamer find(Connection con, String id) throws SQLException {
		String sql = SELECT + "WHERE id_gamer = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));
		ResultSet rs = pstmt.executeQuery();
		Gamer gamer = getAll(rs).get(0);
		//詳細ページアクセス時、評価プラス１
		plusEvaluation(con, gamer);
		pstmt.close();
		return gamer;
	}

	@Override
	public ArrayList<Gamer> search(Connection con, String...keywords) throws SQLException {
		ArrayList<Gamer> gamers = new ArrayList<>();
		String sql = SELECT + "WHERE name_gamer LIKE ? AND team_name_gamer LIKE ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		for(int i = 1; i <= 2; i++) {
			if (keywords[i] == "") {
				pstmt.setString(i, "%");
			} else {
				pstmt.setString(i, "%" + keywords[i] + "%");
			}
		}
		ResultSet rs = pstmt.executeQuery();
		gamers = getAll(rs);
		pstmt.close();
		return gamers;
	}

	@Override
	public ArrayList<Gamer> sort(Connection con) throws SQLException {
		ArrayList<Gamer> gamers = new ArrayList<>();
		String sql = SELECT + "ORDER BY evaluation_gamer DESC LIMIT 5";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		gamers = getAll(rs);
		pstmt.close();
		return gamers;
	}

	@Override
	public void insert(Connection con, String...strings) throws SQLException {
		String sql = INSERT;
		PreparedStatement pstmt = con.prepareStatement(sql);
		for(int i = 1; i < strings.length; i++) {
			pstmt.setString(i, strings[i]);
		}
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}

	@Override
	public void update(Connection con, String...strings) throws SQLException {
		String sql = UPDATE + "name_gamer = ?, team_name_gamer = ?, settings_gamer = ?, profile_gamer = ? WHERE id_gamer = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		for(int i = 1; i < strings.length; i++) {
			pstmt.setString(i, strings[i]);
			if (i == 5) {
				pstmt.setInt(i, Integer.parseInt(strings[i]));
			}
		}
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}

	@Override
	public void delete(Connection con, String id) throws SQLException {
		String sql = DELETE + "id_gamer = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}

	@Override
	public void plusEvaluation(Connection con, Gamer gamer) throws SQLException {
	    int id = 0;
	    int eval_score = 0;
        id = gamer.getId_gamer();
        eval_score = gamer.getEvaluation_gamer();
	    String sql = UPDATE + "evaluation_gamer = ? WHERE id_gamer = ?";
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, eval_score + 1);
	    pstmt.setInt(2, id);
	    pstmt.executeUpdate();
		pstmt.close();
	}

	@Override
	public ArrayList<Gamer> getAll(ResultSet rs) throws SQLException {
		ArrayList<Gamer> gamers = new ArrayList<>();
		while(rs.next()) {
			Gamer g = new Gamer();
			g.setId_gamer(rs.getInt("id_gamer"));
			g.setName_gamer(rs.getString("name_gamer"));
			g.setTeam_name_gamer(rs.getString("team_name_gamer"));
			g.setSettings_gamer(rs.getString("settings_gamer"));
			g.setProfile_gamer(rs.getString("profile_gamer"));
			g.setEvaluation_gamer(rs.getInt("evaluation_gamer"));
			gamers.add(g);
		}
		return gamers;
	}

}
