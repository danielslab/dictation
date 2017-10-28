package dictation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dictation.model.Question;
import dictation.repository.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepository;

	/**
	 * 問題情報一覧を取得する
	 * @return 問題情報一覧
	 */
	public List<Question> getQuestions() {
		return questionRepository.findAll();
	}

	/**
	 * 指定問題番号の問題情報を取得する。
	 * @param questionNumber 問題ID
	 * @return 問題情報
	 */
	public Question getQuestion(long questionId) {
		return questionRepository.findById(questionId);
	}
}
