package com.spring.myweb.user.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.command.UserVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/db-config.xml")
public class UserMapperTest {
	
	@Autowired
	private IUserMapper mapper;
	
	@Test
	@DisplayName("회원가입을 진행했을 때 회원가입에 성공해야 한다.")
	void registTest() {
		UserVO vo = new UserVO();
		vo.setUserId("abc1234");
		vo.setUserPw("aaa1111!");
		vo.setUserName("홍길동");
		
		mapper.join(vo);
	}
	
	@Test
	@DisplayName("존재하는 회원 아이디를 조회했을 시 1이 리턴이 되어야 한다.")
	void checkIdTest() {
		//given
		String id = "abc1234";
		
		//then
		assertEquals(1, mapper.idCheck(id)); //단언
	}
	
	@Test
	@DisplayName("존재하는 회원아이디와 올바른 비밀번호를 입력했을 시 회원의 정보가 리턴되어야 한다.")
	void loginTest() {
		
		String id = "abc1234";
		String pw = "aaa1111!";
		
		assertNotNull(mapper.login(id, pw));  // 널이 아님을 단언한다.
		
//		Map<String, String> data = new HashMap<>();
//		data.put("id", "abc1234");
//		data.put("pw", "aaa1111!");
//		assertNotNull(mapper.login(data));
		
		
	}
	
	@Test
	@DisplayName("존재하지 않는 회원의 아이디를 입력하면 null이 올 것이다.")
	void getInfoTest() {
		String id = "abc123";
		
		assertNull(mapper.getInfo(id));
	}
	
	@Test
	@DisplayName("id를 제외한 회원의 정보를 수정할 수 있다.")
	void updateTest() {
		UserVO vo = new UserVO();
		vo.setUserId("abc1234");  //abc1234의 아디 비번을 수정하겠다.
		vo.setUserPw("aaa1111!"); // NOT NULL 값이라 무조건 넣어줘야함
		vo.setUserName("김"); // NOT NULL 값이라 무조건 넣어줘야함
		
		mapper.updateUser(vo);
		
		assertEquals("김",mapper.getInfo("abc1234").getUserName());
	}
}
