package com.sparta.finalprojectback.s3.controller;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.s3.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Api(tags = "이미지 업로드 기능")
@RequiredArgsConstructor
@RestController
public class FileController {
    private final FileService fileService;
    @ApiOperation("게시물 이미지 생성 기능")
    @PostMapping("user/plan/post/{postId}/image")
    public String uploadImage(@RequestPart MultipartFile file, @PathVariable Long postId,  @RequestParam String path) {
        fileService.deleteImage(postId);
        return fileService.uploadImage(file, postId, path);
    }
    @ApiOperation("프로필 이미지 생성 기능")
    @PostMapping("user/profile")
    public String uploadProfile(@RequestPart MultipartFile file, @AuthenticationPrincipal Member member) {
//        fileService.deleteImage(member);
        return fileService.uploadImage(file, member, "profile");
    }
    @ApiOperation("이미지 불러오는 기능")
    @GetMapping("user/plan/post/{postId}/image")
    public String importImage(@PathVariable Long postId){
        return fileService.importImage(postId);
    }
    @ApiOperation("이미지 삭제 기능")
    @DeleteMapping("user/plan/post/{postId}/image")
    public Long deleteImage(@PathVariable Long postId){
        return fileService.deleteImage(postId);
    }
}
