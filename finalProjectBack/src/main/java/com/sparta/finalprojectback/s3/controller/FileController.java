package com.sparta.finalprojectback.s3.controller;

import com.sparta.finalprojectback.s3.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    @PostMapping("user/plan/post/{postId}/image")
    public String uploadImage(@RequestPart MultipartFile file, @PathVariable Long postId,  @RequestParam String path) {
        fileService.deleteImage(postId);
        return fileService.uploadImage(file, postId, path);
    }

    @GetMapping("user/plan/post/{postId}/image")
    public String importImage(@PathVariable Long postId){
        return fileService.importImage(postId);
    }

    @DeleteMapping("user/plan/post/{postId}/image")
    public Long deleteImage(@PathVariable Long postId){
        return fileService.deleteImage(postId);

    }
}
