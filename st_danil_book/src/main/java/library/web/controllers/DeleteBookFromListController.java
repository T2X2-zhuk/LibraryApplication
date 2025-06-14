package library.web.controllers;

import library.core.requests.AddBookRequest;
import library.core.requests.RemoveBookRequest;
import library.core.requests.RemoveReaderRequest;
import library.core.responses.RemoveBookResponse;
import library.core.services.RemoveBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteBookFromListController {

    @Autowired private RemoveBookService service;

    @GetMapping(value = "/deleteBookFromList")
    public String showDeleteBookPage(ModelMap modelMap){
        modelMap.addAttribute("request",new RemoveBookRequest());
        return "deleteBookFromList";
    }

    @PostMapping("/deleteBookFromList")
    public String processDeleteBook(@ModelAttribute(value = "request") RemoveBookRequest request, ModelMap modelMap){
        RemoveBookResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "deleteBookFromList";
        } else {
            return "redirect:/";
        }
    }
}
