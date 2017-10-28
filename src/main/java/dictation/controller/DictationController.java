package dictation.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dictation.model.Question;
import dictation.service.QuestionService;

@Controller
public class DictationController {
	
	@Autowired
	QuestionService questionService;

    @GetMapping("/dictations/{id}")
    public String index(Model model, @PathVariable String id, Principal principal) throws IOException {
    	long longId = Long.parseLong(id);
    	
    	Question question = questionService.getQuestion(longId);
    	if(question == null) {
    		// 次の問題がない場合は、問題一覧へ戻る。
    		return "redirect:/questions";
    	}
    	model.addAttribute("principal", principal);
        model.addAttribute("question", question);
        return "dictation";
    }
}
