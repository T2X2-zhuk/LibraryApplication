package library.web.controllers;

import library.core.requests.GetAllBooksRequest;
import library.core.requests.GetAllReadersRequest;
import library.core.responses.GetAllBooksResponse;
import library.core.responses.GetAllReadersResponse;
import library.core.services.GetAllReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllReadersController {

    @Autowired private GetAllReadersService service;

    @GetMapping(value = "/showAllReaders")
    public String showAllBooks(ModelMap modelMap) {
       GetAllReadersResponse response = service.execute(
                new GetAllReadersRequest()
        );
        modelMap.addAttribute("readers", response.getReaderList());
        return "/showAllReaders";
    }
}
