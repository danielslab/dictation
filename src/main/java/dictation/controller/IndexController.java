package dictation.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String questions(Model model) throws IOException {
    	// TODO
        return "redirect:/questions";
    }
}
