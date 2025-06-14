package library.web.controllers;

import library.core.requests.DeleteReaderBookRequest;
import library.core.requests.RemoveReaderRequest;
import library.core.responses.DeleteReaderBookResponse;
import library.core.responses.RemoveReaderResponse;
import library.core.services.DeleteReaderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteReaderBookController {

    @Autowired private DeleteReaderBookService service;

    @GetMapping(value = "/deleteReaderBook")
    public String showDeleteReaderBookPage(ModelMap modelMap){
        modelMap.addAttribute("request",new DeleteReaderBookRequest());
        return "deleteReaderBook";
    }

    @PostMapping("/deleteReaderBook")
    public String processDeleteReaderBook(@ModelAttribute(value = "request") DeleteReaderBookRequest request, ModelMap modelMap){
        DeleteReaderBookResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "deleteReaderBook";
        } else {
            return "redirect:/";
        }
    }
}
