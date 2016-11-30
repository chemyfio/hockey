package id.sql.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import id.sql.models.MatchHistory;

public class MatchHistoryBean {
	public List<MatchHistory> getAllMatchHistory() throws SQLException
	{
		List<MatchHistory> list = new ArrayList<>();
		ConnectionManager conn = new ConnectionManager();
		conn.connect();
		Statement stm = conn.con.createStatement();
		ResultSet hasil = stm.executeQuery("Select * from match_history");
		while(hasil.next())
		{
			MatchHistory emp = new MatchHistory(
					hasil.getString("user1"), hasil.getString("user2"), hasil.getString("user3"), hasil.getString("user4"), hasil.getInt("scoreLeft"), hasil.getInt("scoreRight"));
			list.add(emp);
		}
		conn.disconnect();
		return list;
	}
	
	public void insertMatchHistory (MatchHistory emp) throws SQLException
	{
		ConnectionManager conn = new ConnectionManager();
		conn.connect();
		String query ="insert into match_history(user1,user2,user3,user4,time,scoreLeft,scoreRight) values (?,?,?,?,NOW(),?,?)";
		PreparedStatement stmt = conn.con.prepareStatement(query);
		stmt.setString(1, emp.getUser1());
		stmt.setString(2, emp.getUser2());
		stmt.setString(3, emp.getUser3());
		stmt.setString(4, emp.getUser4());
		stmt.setInt(5, emp.getScoreLeft());
		stmt.setInt(6, emp.getScoreRight());
		int hasil = stmt.executeUpdate();
		conn.disconnect();
	}
}
