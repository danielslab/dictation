package dictation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dictation.model.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {
}