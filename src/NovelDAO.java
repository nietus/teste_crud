import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NovelDAO extends DAO {
	
	public NovelDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Novel novel) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO novel (ch_content, ch_id) VALUES ('" + novel.getContent() + "', " + novel.getNext_id() + ")";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Novel get(int ch_id) {
		Novel novel = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM novel WHERE ch_id=" + ch_id;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 novel = new Novel(rs.getString("ch_content"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return novel;
	}
	
	
	public List<Novel> get() {
		return get("");
	}

	
	public List<Novel> getOrderById() {
		return get("ch_id");		
	}
	
	private List<Novel> get(String orderBy) {	
	
		List<Novel> novels = new ArrayList<Novel>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM novel" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Novel u = new Novel(rs.getString("ch_content"));
	            novels.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return novels;
	}
	
	public boolean update(Novel novel) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE novel SET ch_content = '" + novel.getContent() + "' WHERE ch_id = " + novel.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int chapter) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM novel WHERE ch_id = " + chapter;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}