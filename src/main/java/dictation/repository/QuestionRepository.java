package dictation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dictation.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	public Question findById(Long id);
}