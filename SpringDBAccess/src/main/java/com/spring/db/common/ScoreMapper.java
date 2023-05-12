package com.spring.db.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.db.model.ScoreVO;

//JdbcTemplate에서 SELECT 쿼리를 위한 ResultSet 사용을 편하게 하기 위한
//클래스 생성.
//RowMapper 인터페이스를 구현해야한다.
public class ScoreMapper implements RowMapper<ScoreVO> {

	@Override
	public ScoreVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("mapRow 메서드 호출!");
		
		ScoreVO vo = new ScoreVO(//ScoreVO 객체생성 후 객체 포장
					rs.getInt("stu_id"),
					rs.getString("stu_name"),
					rs.getInt("kor"),
					rs.getInt("eng"),
					rs.getInt("math"),
					rs.getInt("total"),
					rs.getDouble("average")
				); 
		return vo; 
	}

}
