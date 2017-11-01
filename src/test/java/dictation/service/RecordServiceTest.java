package dictation.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import dictation.model.Question;
import dictation.model.Record;
import dictation.model.User;
import dictation.repository.RecordRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTest {
	
	@Autowired
	QuestionService questionService;

	@Autowired
	RecordRepository recordRepository;

	@Autowired
	RecordService sut;

	@Before
	public void before() {
        // 許可ロールの設定
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        // SpringSecurity認証 - ユーザID/パスワード認証を設定する - パスワードはpassword固定
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        "taka_2", "password", authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Test
	public void testGetLatestRecord() throws IOException {
		User user1 = new User();
		user1.setUsername("taka_2");
		Question question = questionService.getQuestion(1);
		Record record1 = new Record();
		record1.setUser(user1);
		record1.setQuestion(question);
		record1.setElapsedTime(1111L);
		record1.setCreatedDate(new java.util.Date());
		recordRepository.save(record1);

		Record record2 = new Record();
		record2.setUser(user1);
		record2.setQuestion(question);
		record2.setElapsedTime(2222L);
		record2.setCreatedDate(new java.util.Date());
		recordRepository.save(record2);

		User user2 = new User();
		user2.setUsername("taka_2_test");
		Record record3 = new Record();
		record3.setUser(user2);
		record3.setQuestion(question);
		record3.setElapsedTime(3333L);
		record3.setCreatedDate(new java.util.Date());
		recordRepository.save(record3);

		Record actual = sut.getLatestRecord("taka_2");
		assertThat(actual.getElapsedTime(), is(2222L));
	}
}
