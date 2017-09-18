package dictation.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import dictation.model.QuestionModel;

@Service
public class QuestionService {
	private static final String PROPERTY_KEY_AUDIO_PATH = "audioPath";
	private static final String PROPERTY_KEY_ANSWER_TEXT = "answerText";
	
	/**
	 * 指定問題番号の問題情報を取得する。
	 * @param questionNumber 問題番号
	 * @return 問題情報
	 */
	public QuestionModel getQuestion(int questionNumber) throws IOException {
		try (InputStream is = new ClassPathResource("questions/" + questionNumber + ".properties").getInputStream()) {
			Properties prop = new Properties();
			prop.load(is);
			QuestionModel question = new QuestionModel();
			question.setAudioPath(prop.getProperty(PROPERTY_KEY_AUDIO_PATH));
			question.setAnswerText(readFile(prop.getProperty(PROPERTY_KEY_ANSWER_TEXT)));
			return question;
		}
	}
	
	private String readFile(String path) throws IOException {
		try (InputStream is = new ClassPathResource(path).getInputStream()) {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String buf;
			StringBuilder result = new StringBuilder();
			while((buf = br.readLine()) != null) {
				result.append(buf).append(System.lineSeparator());
			}

			// 最後の改行を削除する。
			result = result.delete(result.length() - System.lineSeparator().length(), result.length());
			return result.toString();
		}
	}
}
