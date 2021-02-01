package com.dayeon.insdagram.controller;

import com.dayeon.insdagram.domain.Account;
import com.dayeon.insdagram.dto.AccountDto;
import com.dayeon.insdagram.repository.AccountRepository;
import com.dayeon.insdagram.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/accounts/edit")
    public String profileEdit(){
        // 프로필 페이지 수정하는 곳.
        return "/accounts/edit";
    }

    @GetMapping("/{username}")
    public String profile(@PathVariable String username,
                          Model model) {
        Account account = accountRepository.findByUsername(username).get();
        if(account.getUsername() == null){
            return "/error";
        }
        else{
            model.addAttribute("username", account.getUsername());
        }

        return "/accounts/profile";
    }
}
