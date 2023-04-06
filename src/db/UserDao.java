package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.User;

public interface UserDao {

	/**
	 * ユーザー新規登録
	 * @param con データベース接続情報
	 * @param strings ユーザー登録に必要な情報群
	 * @return User 登録したユーザー情報
	 * @throws SQLException
	 */
	User signup(Connection con, String...strings) throws SQLException;

	/**
	 * ユーザーログイン
	 * @param con データベース接続情報
	 * @param strings　ログインに必要な情報群
	 * @return ログインしたUserインスタンス
	 * @throws SQLException
	 * @throws IndexOutOfBoundsException
	 */
	User login(Connection con, String...strings) throws SQLException;

	/**
	 * 適切なユーザー権限レベルを、パスワード情報を基に付与
	 * @param con データベース接続情報
	 * @param password ログインできたユーザーのパスワード情報
	 * @throws SQLException
	 */
	void userLevelUp(Connection con, String password) throws SQLException;

	/**
	 * ユーザー権限レベルを0にする
	 * @param con データベース接続情報
	 * @param id ログインセッションユーザーのid
	 * @throws SQLException
	 */
	void logout(Connection con, int id) throws SQLException;

	/**
	 * ResultSet → ArrayListへ変換
	 * @param rs SQLで取得したデータが格納されたResultSet
	 * @return 格納されたデータ一覧
	 * @throws SQLException
	 */
	ArrayList<User> getAll(ResultSet rs) throws SQLException;
}
