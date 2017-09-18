package dictation.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dictation.model.QuestionModel;
import dictation.service.QuestionService;

@Controller
public class QuestionController {
	
	@Autowired
	QuestionService questionService;

    @GetMapping("/questions")
    public String questions(Model model) throws IOException {
    	// TODO
        return "redirect:/questions/1";
    }

    @GetMapping("/questions/{id}")
    public String index(Model model, @PathVariable String id) throws IOException {
    	QuestionModel question = questionService.getQuestion(Integer.parseInt(id));
        model.addAttribute("question", question);

        return "question";
    }
}
