package dictation.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dictation.service.QuestionService;
import dictation.service.RecordService;

@Controller
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	RecordService recordService;

    @GetMapping("/questions")
    public String questions(Model model, Principal principal) throws IOException {
    	// 最後に実施した記録を取得する。
    	model.addAttribute("latestRecord", recordService.getLatestRecord(principal.getName()));
    	model.addAttribute("questions", questionService.getQuestions());
    	return "questions";
    }

}
