package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;


public class MemberDao {

	private JdbcTemplate jdbcTemplate;
	
	public MemberDao() {}
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<Member> memRowMapper = new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int nowNum) throws SQLException{
				Member member = new Member();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setTel(rs.getString("tel"));
				member.setBirth(rs.getString("birth"));
				member.setAddr(rs.getString("addr"));
				member.setEmail(rs.getString("email"));
				member.setRegdate(rs.getTimestamp("regdate").toLocalDateTime());
				return member;
			}
		};
	
	
	public int checkById(String id) {
		List<Member> results = jdbcTemplate.query("select * from member where id = ?",memRowMapper, id);
		
		return results.isEmpty() ? 0 : 1;  //비어있으면 0, 비어있지 않으면 즉 있으면 1
	}

	public Member selectById(String id) {
		List<Member> results = jdbcTemplate.query("select * from member where id = ?",memRowMapper, id);
		
		return results.isEmpty() ? null : results.get(0);  //results가 비어잇으면 null, 잇으면 0
	}
	public void insert(Member member) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
						"insert into member values(?,?,?,?,?,?,?,?)");
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getPw());
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getTel());
				pstmt.setString(5, member.getBirth());
				pstmt.setString(6, member.getAddr());
				pstmt.setString(7, member.getEmail());
				pstmt.setTimestamp(8,
						Timestamp.valueOf(member.getRegdate()));
//				pstmt.executeUpdate();
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		});
	}
	

}