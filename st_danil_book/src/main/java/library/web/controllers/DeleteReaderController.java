package library.web.controllers;

import library.core.requests.RemoveBookRequest;
import library.core.requests.RemoveReaderRequest;
import library.core.responses.RemoveBookResponse;
import library.core.responses.RemoveReaderResponse;
import library.core.services.RemoveReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteReaderController {

    @Autowired private RemoveReaderService service;

    @GetMapping(value = "/deleteReader")
    public String showDeleteReaderPage(ModelMap modelMap){
        modelMap.addAttribute("request",new RemoveReaderRequest());
        return "deleteReader";
    }

    @PostMapping("/deleteReader")
    public String processDeleteReader(@ModelAttribute(value = "request") RemoveReaderRequest request, ModelMap modelMap){
        RemoveReaderResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "deleteReader";
        } else {
            return "redirect:/";
        }
    }
}
