package mx.unam.aragon.ico.te.DeportesMVC.controladores;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }


}
