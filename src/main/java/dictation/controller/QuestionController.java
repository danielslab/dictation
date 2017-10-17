package dictation.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dictation.service.QuestionService;

@Controller
public class QuestionController {
	
	@Autowired
	QuestionService questionService;

    @GetMapping("/questions")
    public String questions(Model model) throws IOException {
    	model.addAttribute("questions", questionService.getQuestions());
    	return "questions";
    }

}
