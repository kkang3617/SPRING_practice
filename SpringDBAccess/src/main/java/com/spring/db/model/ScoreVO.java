package com.spring.db.model;
/*
	# 해당 계정이 어떤 데이터베이스를 사용할 지 명시하는 문장
	USE spring;

	CREATE TABLE scores(
		stu_id INT PRIMARY KEY AUTO_INCREMENT,
	    stu_name VARCHAR(30) NOT NULL,
	    kor INT DEFAULT 0,
	    eng INT DEFAULT 0,
	    math INT DEFAULT 0,
	    total INT DEFAULT 0,
	    average DECIMAL(5, 2)
	);
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScoreVO {
	
		private int stuId;
		private String stuName;
		private int kor;
		private int eng;
		private int math;
		private int total;
		private double average;
		
		//총점, 평균을 구하는 메서드
		public void calcData() {
			this.total = this.kor + this.eng + this.math;
			this.average = Math.round((this.total/3.0) * 100) / 100.0;
		}
		
	
}
