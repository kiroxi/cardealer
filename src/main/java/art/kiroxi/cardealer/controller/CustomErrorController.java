package art.kiroxi.cardealer.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletResponse response) {
        int code = response.getStatus();
        if ( code == HttpStatus.FORBIDDEN.value() ) {
            return "errors/403";
        } else if ( code == HttpStatus.NOT_FOUND.value() ) {
            return "errors/404";
        } else if ( code == HttpStatus.INTERNAL_SERVER_ERROR.value() ) {
            return "errors/500";
        }
        return "errors/500";
    }

}
