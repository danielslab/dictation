package dictation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dictation.model.Record;
import dictation.repository.RecordRepository;

@Service
public class RecordService {
	@Autowired
	RecordRepository recordRepository;

	/**
	 * 全てのレコードを取得する
	 */
	public List<Record> getRecords() {
		return recordRepository.findAll();
	}

	/**
	 * レコードを保存する。
	 */
	public Record save(Record record) {
		return recordRepository.save(record);
	}
}
