package dictation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dictation.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	public List<Question> findAllByOrderByIdAsc();
	public Question findById(Long id);
}