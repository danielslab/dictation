package dictation.service;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dictation.model.QuestionModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTest {
	
	@Autowired
	QuestionService sut;

	@Test
	public void testGetQuestions() throws IOException {
		List<QuestionModel> list = sut.getQuestions();
	}
}
