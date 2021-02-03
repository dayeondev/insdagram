package com.dayeon.insdagram.controller;

import com.dayeon.insdagram.domain.Account;
import com.dayeon.insdagram.dto.AccountDto;
import com.dayeon.insdagram.repository.AccountRepository;
import com.dayeon.insdagram.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String signIn(@RequestParam(value = "error", required = false) String error,
                         @RequestParam(value = "exception", required = false) String exception,
                         Model model) {
        if(true){ //세션이 없을 때 동작
            model.addAttribute("error", error);
            model.addAttribute("exception", exception);
            return "/index";
        }
        else{ //세션이 있을 때 동작
            return null;
        }
    }

    @GetMapping("/accounts/emailsignup")
    public String signUpForm() {
        return "/accounts/emailsignup";
    }

    @PostMapping("/accounts/emailsignup")
    public String signUp(Account account) {
        validateDuplicateMember(account.getUsername());
//        passwordEncoder = new BCryptPasswordEncoder();
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return "redirect:/";
    }
    @ExceptionHandler
    private void validateDuplicateMember(String username){

        accountRepository.findByUsername(username);

    }

    @GetMapping("/newposts")
    public String newPosts() {
        return "/newposts";
    }

    @GetMapping("/logout")
    public String logOut(){
        return "/accounts/logout";
    }

    @GetMapping("/accounts/edit")
    public String profileEditForm(@AuthenticationPrincipal Account account){
        // 프로필 페이지 수정하는 곳.
        System.out.println(account.getUsername());
        return "/accounts/edit";
    }

    @PostMapping("/accounts/edit")
    public String profileEdit(){
//        return "redirect:/" +
        return null;
    }

    @GetMapping("/{username}")
    public String profile(@PathVariable String username,
                          Model model) {
        Account account = accountRepository.findByUsername(username);
        if(account.getUsername() == null){
            return "/error";
        }
        else{
            model.addAttribute("username", account.getUsername());
            model.addAttribute("bio", account.getBio());
        }

        return "/accounts/profile";
    }
}
