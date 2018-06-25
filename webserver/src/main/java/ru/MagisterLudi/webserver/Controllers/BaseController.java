package ru.MagisterLudi.webserver.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.MagisterLudi.webserver.Pages.MainPage;

import javax.servlet.http.HttpServletResponse;

//@RestController
public class BaseController {

    @RequestMapping("/")
    public void getPage(HttpServletResponse response){
        System.out.println(response + "1");
    }

    @RequestMapping("/test")
    public void getPageTho(HttpServletResponse response){
        System.out.println(response + "2");
    }

}
