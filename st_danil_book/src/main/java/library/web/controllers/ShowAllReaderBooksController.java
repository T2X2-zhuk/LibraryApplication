package library.web.controllers;

import library.core.requests.GetAllReaderBooksRequest;
import library.core.requests.GetAllReadersRequest;
import library.core.responses.GetAllReaderBooksResponse;
import library.core.responses.GetAllReadersResponse;
import library.core.services.GetAllReaderBooksService;
import library.core.services.GetAllReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllReaderBooksController {

    @Autowired private GetAllReaderBooksService service;

    @GetMapping(value = "/showAllReaderBook")
    public String showAllReaderBooks(ModelMap modelMap) {
        GetAllReaderBooksResponse response = service.execute();
        modelMap.addAttribute("readerBooks", response.getReaderBooks());
        return "/showAllReaderBook";

    }
    }