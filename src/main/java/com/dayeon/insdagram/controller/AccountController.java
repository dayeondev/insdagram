package com.dayeon.insdagram.controller;

import com.dayeon.insdagram.domain.Account;
import com.dayeon.insdagram.repository.AccountRepository;
import com.dayeon.insdagram.service.CustomUserDetail;
import com.dayeon.insdagram.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    @Autowired
    private CustomUserDetailService myUserDetailService;

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
    public String signUp(Account requestAccount) {

        try{
            accountRepository.findByUsername(requestAccount.getUsername());
            System.out.println("중복 계정임을 알려주는 기능을 넣어야 합니다.");
        }
        catch (IllegalStateException e){
            requestAccount.setPassword(passwordEncoder.encode(requestAccount.getPassword()));
            System.out.println("here");
            accountRepository.save(requestAccount);
        }

        return "redirect:/";
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
    public String profileEditForm(@AuthenticationPrincipal CustomUserDetail user,
                                  Model model){

        Account account = accountRepository.findByUsername(user.getUsername());

        model.addAttribute("username", account.getUsername());
        model.addAttribute("name", account.getName());
        model.addAttribute("bio", account.getBio());
        model.addAttribute("website", account.getWebsite());
        model.addAttribute("email", account.getEmail());
        model.addAttribute("phoneNumber", account.getPhoneNumber());
        return "/accounts/edit";
    }

    @PostMapping("/accounts/edit")
    public String profileEdit(@AuthenticationPrincipal CustomUserDetail user,
                              Account requestAccount){
        Account account = accountRepository.findByUsername(user.getUsername());
        System.out.println(user.getUsername());
        account.setUsername(requestAccount.getUsername());
//        account.setProfileImage(requestAccount.getProfileImage());
        account.setName(requestAccount.getName());
        account.setWebsite(requestAccount.getWebsite());
        account.setBio(requestAccount.getBio());
        account.setEmail(requestAccount.getEmail());
        account.setPhoneNumber(requestAccount.getPhoneNumber());

        accountRepository.save(account);

        return logOut();
    }

    @GetMapping("/user/{username}")
    public String profile(@PathVariable String username,
                          Model model) {
        Account account = accountRepository.findByUsername(username);
        if(account.getUsername() == null){
            return "/error";
        }
        else{
            model.addAttribute("username", account.getUsername());
            model.addAttribute("name", account.getName());
            model.addAttribute("bio", account.getBio());
            model.addAttribute("website", account.getWebsite());
            model.addAttribute("email", account.getEmail());
            model.addAttribute("phoneNumber", account.getPhoneNumber());
        }

        return "/accounts/profile";
    }
}
