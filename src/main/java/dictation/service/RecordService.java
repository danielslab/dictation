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
	 * 指定ユーザの一番直近のレコードを取得する
	 * @param username ユーザ
	 * @return レコードがない場合はnullを返す。
	 */
	public Record getLatestRecord(String username) {
		List<Record> records = recordRepository.findByUserUsernameOrderByCreatedDateDesc(username);
		if(records.size() == 0) {
			return null;
		} else {
			return records.get(0);
		}
	}

	/**
	 * レコードを保存する。
	 */
	public Record save(Record record) {
		return recordRepository.save(record);
	}
}
