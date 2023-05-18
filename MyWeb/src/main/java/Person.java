import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.ToString;

@Getter
@Service
@ToString
public class Person {
	private String name;
	private int age;
	private List<String> hobby;
}
