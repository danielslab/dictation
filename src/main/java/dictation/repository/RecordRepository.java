package dictation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dictation.model.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {
	public List<Record> findByUserUsernameOrderByCreatedDateDesc(String username);
}