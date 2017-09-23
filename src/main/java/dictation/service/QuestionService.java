package dictation.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import dictation.model.QuestionModel;
import dictation.util.FileUtil;

@Service
public class QuestionService {
	private static final String QUESTION_FILE_PATH = "/questions/questions.json";
	private static final String PROPERTY_KEY_AUDIO_PATH = "audioPath";
	private static final String PROPERTY_KEY_ANSWER_TEXT = "answerText";

	/**
	 * 問題情報一覧を取得する
	 * @return 問題情報一覧
	 * @throws IOException
	 */
	public List<QuestionModel> getQuestions() throws IOException {
		JsonParser jsonParser = JsonParserFactory.getJsonParser();
		List<Object> list = jsonParser.parseList(FileUtil.readFile(QUESTION_FILE_PATH));
		List<QuestionModel> resultList = new ArrayList<QuestionModel>();
		for(Object o : list) {
			Map<String, String> map = (Map<String, String>)o;
			QuestionModel model = new QuestionModel();
			model.setAudioPath(map.get(PROPERTY_KEY_AUDIO_PATH));
			model.setAnswerText(FileUtil.readFile(map.get(PROPERTY_KEY_ANSWER_TEXT)));
			resultList.add(model);
		}
		
		return resultList;
	}

	/**
	 * 問題数を取得する
	 * @return 問題数
	 * @throws IOException
	 */
	public int getQuestionCount() throws IOException {
		return getQuestions().size();
	}

	/**
	 * 指定問題番号の問題情報を取得する。
	 * @param questionNumber 問題番号
	 * @return 問題情報
	 */
	public QuestionModel getQuestion(int questionNumber) throws IOException {
		int questionIndex = questionNumber;
		List<QuestionModel> questions = getQuestions();
		if(questionNumber >= questions.size()) {
			questionIndex = 0;
		}
		return questions.get(questionIndex);
	}
}
