package library.web.controllers;

import library.core.requests.SearchBooksRequest;
import library.core.responses.SearchBooksResponse;
import library.core.services.SearchBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchBookController {

    @Autowired private SearchBooksService service;

    @GetMapping(value = "/searchBooks")
    public String showSearchBooksPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchBooksRequest());
        return "searchBooks";
    }

    @PostMapping("/searchBooks")
    public String processSearchBooksRequest(@ModelAttribute(value = "request") SearchBooksRequest request, ModelMap modelMap) {
        SearchBooksResponse response = service.execute(request);
        modelMap.addAttribute("books", response.getBooks());
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        }
        return "searchBooks";
    }
}
