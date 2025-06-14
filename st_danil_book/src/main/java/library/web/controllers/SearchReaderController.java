package library.web.controllers;

import library.core.requests.SearchBooksRequest;
import library.core.requests.SearchReadersRequest;
import library.core.responses.SearchBooksResponse;
import library.core.responses.SearchReaderResponse;
import library.core.services.SearchReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchReaderController {

    @Autowired private SearchReadersService service;

    @GetMapping(value = "/searchReaders")
    public String showSearchReaderPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchReadersRequest());
        return "searchReaders";
    }

    @PostMapping("/searchReaders")
    public String processSearchReadersRequest(@ModelAttribute(value = "request") SearchReadersRequest request, ModelMap modelMap) {
        SearchReaderResponse response = service.execute(request);
        modelMap.addAttribute("readers", response.getReaderList());
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        }
        return "searchReaders";
    }
}
