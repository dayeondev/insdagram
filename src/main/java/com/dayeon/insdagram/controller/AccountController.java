package com.dayeon.insdagram.controller;

import com.dayeon.insdagram.domain.Account;
import com.dayeon.insdagram.repository.AccountRepository;
import com.dayeon.insdagram.service.CustomUserDetail;
import com.dayeon.insdagram.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AccountController {

    @Value("${file.path}")
    private String fileRealPath;

    @Autowired
    private CustomUserDetailService customUserDetailService;

//    @Autowired
//    private AccountRepository accountRepository;



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

        customUserDetailService.signUpAccount(requestAccount);
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
        Optional<Account> optionalAccount = customUserDetailService.findByUsername(user.getUsername());

        model.addAttribute("username", optionalAccount.get().getUsername());
        model.addAttribute("name", optionalAccount.get().getName());
        model.addAttribute("bio", optionalAccount.get().getBio());
        model.addAttribute("website", optionalAccount.get().getWebsite());
        model.addAttribute("email", optionalAccount.get().getEmail());
        model.addAttribute("phoneNumber", optionalAccount.get().getPhoneNumber());
        return "/accounts/edit";
    }

    @PostMapping("/accounts/edit")
    public String profileEdit(@AuthenticationPrincipal CustomUserDetail user,
                              Account requestAccount){

        customUserDetailService.updateUserInfo(user.getAccount().getId(), requestAccount);
        return logOut();
    }

    @GetMapping("/user/{username}")
    public String profile(@PathVariable String username,
                          Model model) {

        Optional<Account> optionalAccount = customUserDetailService.findByUsername(username);

        System.out.println(username);
        if(username == null){
            return "/error";
        }
        else{

//            model.addAttribute("username", optionalAccount.get().getUsername());
//            model.addAttribute("name", optionalAccount.get().getName());
//            model.addAttribute("bio", optionalAccount.get().getBio());
//            model.addAttribute("website", optionalAccount.get().getWebsite());
//            model.addAttribute("email", optionalAccount.get().getEmail());
//            model.addAttribute("phoneNumber", optionalAccount.get().getPhoneNumber());
            model.addAttribute("account", optionalAccount.get());
            model.addAttribute("fileRealPath", fileRealPath + optionalAccount.get().getProfileImage());

        }

        return "/accounts/profile";
    }
}
