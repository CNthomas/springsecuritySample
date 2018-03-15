package com.test.thomas.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.test.thomas.config.config.security.ValidateCodeHandle;
import com.test.thomas.config.vo.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by thomas on 2018/2/28.
 */
@Controller
public class LoginController {

    @RequestMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @RequestMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/400")
    public String badRequest() {
        return "error/400";
    }

    @GetMapping("/404")
    public String notFound() {
        return "error/404";
    }
    @GetMapping("/403")
    public String forbiden() {
        return "error/403";
    }

    @GetMapping("/500")
    public String serverError() {
        return "error/500";
    }
    @GetMapping("/405")
    public String noallow() {
        return "error/405";
    }

    @ResponseBody
    @GetMapping("generateCode")
    public CommonResult generateCode(HttpServletRequest request)throws Exception{
        CommonResult result = new CommonResult();
        ValidateCodeHandle.save(request.getSession().getId(),"12345");
        ObjectMapper mapper = new ObjectMapper();
        result.setCode(HttpStatus.OK.value());
        result.setData("12345");
        result.setMessage("success");
        return result;
    }
}
