package com.spring.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.db.model.ScoreVO;
import com.spring.db.repository.IScoreDAO;

@Service
public class ScoreService implements IScoreService {

	@Autowired
	private IScoreDAO dao;
	
	@Override
	public void insertScore(ScoreVO vo) {
		vo.calcData();
		System.out.println("service: " + vo);
		dao.insertScore(vo); //calc 데이터를 dao로 넘김.
		
	}

	@Override
	public List<ScoreVO> selectAllScores() {

		return dao.selectAllScores();
	}

	@Override
	public void deleteScore(int num) {

		dao.deleteScore(num);
	}

	@Override
	public ScoreVO selectOne(int num) {
		
		return dao.selectOne(num);
	}

}
