package com.dayeon.insdagram.controller;

import com.dayeon.insdagram.dto.AccountDto;
import com.dayeon.insdagram.repository.AccountRepository;
import com.dayeon.insdagram.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@AllArgsConstructor
public class AccountController {
    //    @GetMapping("/")
//    public String signIn(@RequestParam("name") String name, Model model){
//        model.addAttribute("name", name);
//        return "/index";
//    }
    private AccountService accountService;
    private final AccountRepository accountRepository;

    @GetMapping("/")
    public String signIn(@RequestParam(value = "error", required = false) String error,
                         @RequestParam(value = "exception", required = false) String exception,
                         Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/index";
    }

    @GetMapping("/accounts/emailsignup")
    public String signUpForm() {
        return "/accounts/emailsignup";
    }

    @PostMapping("/accounts/emailsignup")
    public String signUp(AccountDto accountDto) {
        validateDuplicateMember(accountDto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        return "redirect:/";
    }
    private void validateDuplicateMember(AccountDto accountDto){
        accountRepository.findByUsername(accountDto.getUsername()).ifPresent(m -> {
            throw new IllegalStateException("Username already exists.");
        });
    }

    @GetMapping("/newposts")
    public String newPosts() {
        return "/newposts";
    }

    @GetMapping("/logout")
    public String logOut(){
        return "/accounts/logout";
    }

    @GetMapping("/accounts/edit/")
    public String profileEdit(){
        return "/accounts/edit";
    }
}
