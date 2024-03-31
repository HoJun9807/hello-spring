package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // localhost:8080/ -> home.html
    public String home() {
        return "home";
    } // home.html 파일을 찾아서 렌더링
}
