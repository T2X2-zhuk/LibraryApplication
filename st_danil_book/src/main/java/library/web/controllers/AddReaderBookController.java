package library.web.controllers;

import library.core.requests.AddBookRequest;
import library.core.requests.AddReaderBookRequest;
import library.core.responses.AddBookResponse;
import library.core.responses.AddReaderBookResponse;
import library.core.services.AddBookService;
import library.core.services.AddReaderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddReaderBookController {

    @Autowired private AddReaderBookService service;

    @GetMapping(value = "/addReaderBook")
    public String showAddReaderBookPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddReaderBookRequest());
        return "addReaderBook";
    }

    @PostMapping("/addReaderBook")
    public String processAddReaderBookRequest(@ModelAttribute(value = "request") AddReaderBookRequest request, ModelMap modelMap) {
        AddReaderBookResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addReaderBook";
        } else {
            return "redirect:/";
        }
    }
}
