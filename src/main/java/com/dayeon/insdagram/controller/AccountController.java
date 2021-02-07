package com.dayeon.insdagram.controller;

import com.dayeon.insdagram.domain.Account;
import com.dayeon.insdagram.repository.AccountRepository;
import com.dayeon.insdagram.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NonUniqueResultException;
import java.security.Principal;
import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String signIn(@RequestParam(value = "error", required = false) String error,
                         @RequestParam(value = "exception", required = false) String exception,
//                            Principal principal,
//                         Authentication authentication,
                         Model model) {
//        System.out.println("here" + principal.getName());
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        System.out.println(userDetails.getUsername());


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
    public String profileEditForm(@AuthenticationPrincipal User user,
                                  Model model){
        // 프로필 페이지 수정하는 곳.
//        Account account = accountRepository.findByUsername(user.getUsername());

//        System.out.println(account.getUsername());

//        model.addAttribute("my_home", "@{/user/" + account.getUsername() + "}");

        return "/accounts/edit";
    }

    @PostMapping("/accounts/edit")
    public String profileEdit(@AuthenticationPrincipal User user,
                              Account requestAccount){
        Account account = accountRepository.findByUsername(user.getUsername());
        System.out.println(user.getUsername());
        account.setUsername(requestAccount.getUsername());
        account.setBio(requestAccount.getBio());

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
            model.addAttribute("bio", account.getBio());
        }

        return "/accounts/profile";
    }
}
