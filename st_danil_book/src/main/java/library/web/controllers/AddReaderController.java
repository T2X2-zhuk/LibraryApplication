package library.web.controllers;

import library.core.requests.AddReaderRequest;
import library.core.responses.AddReaderResponse;
import library.core.services.AddReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddReaderController {

    @Autowired private AddReaderService service;

    @GetMapping(value = "/addReader")
    public String showAddReaderPage(ModelMap modelMap){
        modelMap.addAttribute("request",new AddReaderRequest());
        return "addReader";
    }

    @PostMapping("/addReader")
    public String processAddReader(@ModelAttribute(value = "request") AddReaderRequest request , ModelMap modelMap){
        AddReaderResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addBookToList";
        } else {
            return "redirect:/";
        }
    }
}
