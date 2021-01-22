package com.dayeon.insdagram.controller;

import com.dayeon.insdagram.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    //    @GetMapping("/")
//    public String signIn(@RequestParam("name") String name, Model model){
//        model.addAttribute("name", name);
//        return "/index";
//    }
    private AccountService accountService;

    @GetMapping("/")
    public String signIn(){
        return "/index";
    }

    @GetMapping("/accounts/emailsignup")
    public String signUpForm(){
        return "/accounts/emailsignup";
    }

    @PostMapping("/accounts/emailsignup")
    public String signUp(AccountDto accountDto) {
        accountService.joinUser(accountDto);
        return "redirect:/";
    }

}
