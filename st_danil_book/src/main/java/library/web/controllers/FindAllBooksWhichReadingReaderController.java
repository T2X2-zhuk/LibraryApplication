package library.web.controllers;

import library.core.requests.FindAllBooksWhichReadingReaderRequest;
import library.core.requests.SearchReadersRequest;
import library.core.responses.FindAllBooksWhichReadingReaderResponse;
import library.core.responses.SearchReaderResponse;
import library.core.services.FindAllBooksWhichReadingReaderService;
import library.core.services.SearchReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindAllBooksWhichReadingReaderController {

    @Autowired private FindAllBooksWhichReadingReaderService service;

    @GetMapping(value = "/findAllBooksWhichReadingReader")
    public String showSearchBooksPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new FindAllBooksWhichReadingReaderRequest());
        return "findAllBooksWhichReadingReader";
    }

    @PostMapping("/findAllBooksWhichReadingReader")
    public String processSearchBooksWhichReadingReaderRequest(@ModelAttribute(value = "request") FindAllBooksWhichReadingReaderRequest request, ModelMap modelMap) {
        FindAllBooksWhichReadingReaderResponse response = service.execute(request);
        modelMap.addAttribute("books", response.getBooksList());
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        }
        return "findAllBooksWhichReadingReader";
    }
}
