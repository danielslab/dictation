package dictation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dictation.model.Record;
import dictation.service.RecordService;

@RestController
@RequestMapping("/records")
public class RecordController {
	
	@Autowired
	RecordService recordService;

	@PostMapping("")
	public Record createRecord(@RequestBody Record record) {
	    return recordService.save(record);
	}

}
