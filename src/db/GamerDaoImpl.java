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

	//レコード数取得
	public int amountRecordGamer(Connection con) throws SQLException {
		int amountRecord = 0;
		String sql = "SELECT COUNT(name_gamer) FROM gamer";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		amountRecord = rs.getInt(1);
		return amountRecord;
	}

	/**
	 * ページング処理
	 */
	//ページ毎のデータを取得
	public ArrayList<Gamer> findPerPage(Connection con, String page) throws SQLException {
		ArrayList<Gamer> gamers = new ArrayList<>();
		String sql = SELECT + "LIMIT 10 OFFSET ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		int num = (Integer.parseInt(page) - 1) * 10;
		pstmt.setInt(1, num);
		ResultSet rs = pstmt.executeQuery();
		gamers = getAll(rs);
		pstmt.close();
		return gamers;
	}

	//ページ番号のボタン表示処理
	public String[] adjustPaging(Connection con, String page) throws SQLException {
		int launch = Integer.parseInt(page) - 2;
		int totalPageNum = amountRecordGamer(con);
		String[] launchButton_totalPageNum = new String[2];
		if((totalPageNum % 10) > 0) {
			//表示件数が、中途半端な時はページ数＋１
			totalPageNum = (totalPageNum / 10) + 1;
		} else {
			totalPageNum /= 10;
		}
		int flow = launch + 4 - totalPageNum;
		if(launch <= 0) {
		//下回った分をスタート地点に足す
		  launch += (launch - 1) * -1;	//0も下回ったとカウントするから追加で-1
		} else if(flow > 0) {
		//上回った分をスタート地点から引く
		  launch -= flow;
		}
		launchButton_totalPageNum[0] = String.valueOf(launch);
		launchButton_totalPageNum[1] = String.valueOf(totalPageNum);
		return launchButton_totalPageNum;
	}

}
