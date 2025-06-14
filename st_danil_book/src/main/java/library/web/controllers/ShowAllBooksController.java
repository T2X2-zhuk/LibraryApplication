package library.web.controllers;

import library.core.requests.GetAllBooksRequest;
import library.core.responses.GetAllBooksResponse;
import library.core.services.GetAllBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllBooksController {

    @Autowired
    private GetAllBooksService getAllBooksService;

    @GetMapping(value = "/showAllBooks")
    public String showAllBooks(ModelMap modelMap) {
        GetAllBooksResponse response = getAllBooksService.execute(
                new GetAllBooksRequest()
        );
        modelMap.addAttribute("books", response.getBooks());
        return "/showAllBooks";
    }
}
