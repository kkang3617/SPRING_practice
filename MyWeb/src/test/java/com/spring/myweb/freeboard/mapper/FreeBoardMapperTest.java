package com.spring.myweb.freeboard.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

@ExtendWith(SpringExtension.class)  //테스트 환경을 만들어 주는 junit5 객체 로딩
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/config/db-config.xml"
})
public class FreeBoardMapperTest {

	@Autowired
	private IFreeBoardMapper mapper;
	
	//단위 테스트 (unit text) - 가장 작은 단위의 테스트
	//테스트 시나리오
	//- 단언 (Assertion) 기법
	
	@Test
	@DisplayName("Mapper 계층의 regist를 호출하면서 "
			+ "FreeBoardVO를 전달하면 데이터가 INSERT 된다.")
	void registTest() {
		//given - when - then 패턴을 따른다. (생략 가능)
		
		//given: 테스트를 위해 주어질 데이터 (ex: parameter)
		FreeBoardVO vo = new FreeBoardVO();
		vo.setTitle("첫번째 글");
		vo.setWriter("abc1234");
		vo.setContent("안녕 반갑~");
		
		//when: 테스트 실제 상황
		mapper.regist(vo);
		
		//then: 테스트 결과를 확인.
	}
	
	@Test
	@DisplayName("전체 글 목록을 조회하고, 조회된 글 갯수를 파악했을 때 하나가 조회될 것이다.")
	void getListTest() {
		
		List<FreeBoardVO> list = mapper.getList();

		/*
		for(FreeBoardVO vo : list) {
			System.out.println(vo);
		}  아래 코드와 같음. 
		*/
		
		list.forEach(vo -> System.out.println(vo)); //스트림 람다식 표기
		
		assertEquals(2, list.size()); // 리스트의 사이즈가 1일 것 이라고 단언한다.
		
	}
	@Test
	@DisplayName("글 번호가 2번인 글을 조회하면 글쓴이는 abc1234일 것이고 글 내용은 '안녕 반갑~' 일 것이다.")
	void getContentTest() {
		
		//given
		int bno = 2;
				
		//when 
		FreeBoardVO vo = mapper.getContent(bno); //맵퍼야 겟컨텐츠 해줘, 값은 bno야.
		
		//then
		assertEquals("abc1234", vo.getWriter());
		assertEquals("안녕 반갑~", vo.getContent());
//		assertTrue(vo.getContent().equals("안녕 반갑~")); //위처럼 써도되고 아래처럼 써도됨.
//		assertNull(vo); 
	
	}
	
	@Test
	@DisplayName("글 번호가 1번인 글의 제목과 내용을 수정 후 다시 조회했을 때"
			+ " 수정한 제목으로 바뀌었음을 단언한다.")
	void updateTest() {
		//given
		FreeBoardVO vo = new FreeBoardVO();
		vo.setBno(1);
		vo.setTitle("수정된 글 제목");
		vo.setContent("수정된 글 내용");
		
		//when 
		mapper.update(vo);
		
		//then
		assertEquals(vo.getTitle(), mapper.getContent(vo.getBno()).getTitle());
		
	}
	
	
	@Test
	@DisplayName("글 번호가 2번인 글을 삭제한 후에 리스트를 전체 조회했을 때"
			+ "글의 개수는 1개일 것이고, 2번 글을 조회했을 때 null이 반환되어야 한다.")
	void deleteTest() {
		//given 
		int bno = 2;
		
		//when
		mapper.delete(bno);
		
		//then
		assertEquals(1, mapper.getList().size()); //글의 개수는 1일것이고
		assertNull(mapper.getContent(bno)); // 2번글을 조회했을 때 null 반환.
	}
	
	
	
	
	
	
	
	
	
}
