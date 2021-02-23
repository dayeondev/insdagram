package com.dayeon.insdagram.controller;


import com.dayeon.insdagram.domain.Account;
import com.dayeon.insdagram.domain.Image;
import com.dayeon.insdagram.repository.AccountRepository;
import com.dayeon.insdagram.repository.ImageRepository;
import com.dayeon.insdagram.service.CustomUserDetail;
import com.dayeon.insdagram.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ImageController {

    @Value("${file.path}")
    private String fileRealPath;

    @Autowired
    ImageRepository imageRepository;


    @Autowired
    private CustomUserDetailService customUserDetailService;


    @GetMapping("/image/upload")
    public String imageUpload() {
        return "image/imageUpload";
    }


    @PostMapping("/image/uploadProc")
    public String imageUploadProc(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestParam("file")MultipartFile file
    ) throws IOException{

//        UUID uuid = UUID.randomUUID();
//        String uuidFilename = uuid + "_" + file.getOriginalFilename();
//        Path filePath = Paths.get(fileRealPath + uuidFilename);
//        Files.write(filePath, file.getBytes());
//
//        Account principal = userDetail.getAccount();
//        System.out.println(principal.getWebsite());
//
//
//
//        Image image = new Image();
//
//        image.setFile(file.getBytes());
//        image.setAccount(principal);
//
//        imageRepository.save(image);


//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(file.getContentType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getOriginalFilename() + "\"")
//                .body(new ByteArrayResource(file.getBytes()));

        return "redirect:/";
    }

    @PostMapping("/image/profileUpload")
    public String userProfileUpload(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestParam("profileImage") MultipartFile file

    ) throws IOException {


//        // 파일 처리
//        UUID uuid = UUID.randomUUID();
//        String uuidFilename = uuid + "_" + file.getOriginalFilename();
//        Path filePath = Paths.get(fileRealPath + uuidFilename);
//        Files.write(filePath, file.getBytes());
//
//        Account principal = userDetail.getAccount();
//
////        // 영속화
////        Optional<Account> optionalAccount = accountRepository.findById(principal.getId());
////        Account account = optionalAccount.get();
//
//        Image image = new Image();
//
//        image.setFile(file.getBytes());
//        image.setAccount(principal);
//
//        imageRepository.save(image);
//
//
//        // 값 변경
//        principal.setProfileImage(uuidFilename);
//
//        // 다시 영속화 및 저장
//        accountRepository.save(principal);
//        System.out.println("redirect:/user/" + principal.getUsername());
//        return "redirect:/user/" + principal.getUsername();

//        UUID uuid = UUID.randomUUID();
//        String uuidFilename = uuid + "_" + file.getOriginalFilename();
//
//        Account principal = userDetail.getAccount();
//
//        try{
//
//            file.transferTo(new File(fileRealPath + uuidFilename));
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Image image = new Image();
//
//        image.setFile(file.getBytes());
//        image.setAccount(principal);
//
//        imageRepository.save(image);
//
//        principal.setProfileImage(uuidFilename);
//        accountRepository.save(principal);
//


//        https://private.tistory.com/59
        Account principal = userDetail.getAccount();
        String sourceFileName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String uuidFilename = uuid + "_" + sourceFileName;
        File destinationFile;

        Image image = new Image();
        image.setDirectory(uuidFilename);
        image.setAccount(principal);

        destinationFile = new File(fileRealPath + uuidFilename);
        destinationFile.getParentFile().mkdirs();
        file.transferTo(destinationFile);

        principal.setProfileImage(uuidFilename);

        customUserDetailService.updateUserInfo(principal.getId(), principal);
        imageRepository.save(image);

        return "redirect:/user/" + principal.getUsername();


    }
}
