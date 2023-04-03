package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Gamer;

public interface GamerDao {

	/**
	 * 全検索
	 * @param con 接続情報
	 * @return Gamerテーブルのデータを全て受け渡す
	 * @return 選手一覧
	 * @throws SQLException
	 */
	ArrayList<Gamer> findAll(Connection con) throws SQLException;

	/**
	 * 特定検索
	 * @param con 接続情報
	 * @param id 選択されたゲーマーのレコードID
	 * @return 選択されたゲーマーのインスタンス
	 * @throws SQLException
	 */
	Gamer find(Connection con, String id) throws SQLException;

	/**
	 * あいまい検索
	 * @param con 接続情報
	 * @param keywords 部分検索
	 * @return 一致したゲーマー一覧
	 * @throws SQLException
	 */
	ArrayList<Gamer> search(Connection con, String...keywords) throws SQLException;

	/**
	 * ゲーマを人気順に表示
	 * @param gamers gamerテーブルの全情報
	 * @return gamers  gamerテーブルの選手情報を降順にしたリスト
	 * @throws SQLException
	 */
	ArrayList<Gamer> sort(Connection con) throws SQLException;

	/**
	 * ゲーマー登録
	 * @param con 接続情報
	 * @param strings 登録情報
	 * @throws SQLException
	 */
	void insert(Connection con, String...strings) throws SQLException;

	/**
	 * ゲーマー情報更新
	 * @param con 接続情報
	 * @param strings 更新情報
	 * @throws SQLException
	 */
	void update(Connection con, String...strings) throws SQLException;

	/**
	 * ゲーマー削除
	 * @param con 接続情報
	 * @param id 削除するレコードID
	 * @throws SQLException
	 */
	void delete(Connection con, String id) throws SQLException;

	/**
	 * 詳細データへのアクセス時、人気度をプラスする機能
	 * @param gamer
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	void plusEvaluation(Connection con, Gamer gamer) throws SQLException;

	/**
	 * ResultSet → ArrayListへ変換
	 * @param rs SQLで取得したデータが格納されたResultSet
	 * @return 格納されたデータ一覧
	 * @throws SQLException
	 */
	ArrayList<Gamer> getAll(ResultSet rs) throws SQLException;
}
