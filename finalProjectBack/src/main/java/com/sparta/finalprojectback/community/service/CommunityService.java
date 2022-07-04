package com.sparta.finalprojectback.community.service;

import com.sparta.finalprojectback.community.Community;
import com.sparta.finalprojectback.community.dto.CommunityRequestDto;
import com.sparta.finalprojectback.community.dto.CommunityResponseDto;
import com.sparta.finalprojectback.community.repository.CommunityRepository;
import com.sparta.finalprojectback.member.Member;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    // 등록
    @Transactional
    public Long createCommunity(CommunityRequestDto requestDto, Member member) {
        Community community = new Community(requestDto, member);
        return communityRepository.save(community).getId();
    }


    // 삭제
    @Transactional(rollbackFor = Exception.class)
    public void deleteCommunity(Long id, Member member) {
        Community community = communityRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 게시물이 없습니다."));
        communityRepository.deleteCommunityByIdAndMember(id, member);
    }


    // 수정
    @Transactional(rollbackFor = Exception.class)
    public void updateCommunity(Long id, CommunityRequestDto requestDto, Member member) {
        Community community = communityRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시물이 존재하지 않습니다.")
        );
        community.updateCommunity(id, requestDto, member);
        communityRepository.save(community);
    }


    // 모든 게시물 조회
    public List<CommunityResponseDto> getCommunityList() {
        List<Community> communityList = communityRepository.findAllByOrderByCreatedAtDesc();
        return getCommunityResponseDtos(communityList);
    }

    // 리팩토링
    @NotNull
    private List<CommunityResponseDto> getCommunityResponseDtos(List<Community> communityList) {
        List<CommunityResponseDto> communityResponseDtoList = new ArrayList<>();

        for (Community community : communityList) {
            communityResponseDtoList.add(new CommunityResponseDto(community.getId(), community.getTitle(),
                    community.getContent(), community.getMember().getNickname(), community.getCreatedAt(), community.getModifiedAt()));
        }

        return communityResponseDtoList;
    }


    // 상세 조회
    @Transactional
    public CommunityResponseDto communityDetail(Long id) {
        Community community = communityRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시물이 없습니다.")
        );
        CommunityResponseDto responseDto = new CommunityResponseDto();
        responseDto.setId(community.getId());
        responseDto.setTitle(community.getTitle());
        responseDto.setContent(community.getContent());
        responseDto.setWriter(community.getMember().getNickname());
        return responseDto;
    }


    // 나의 게시물 조회
    public List<CommunityResponseDto> getMyCommunityList(Member member) {

        List<Community> communityList = communityRepository.findAllByMemberId(member.getId());
        return getCommunityResponseDtos(communityList);
    }



    /*>=======================페이징 구현 =======================<*/


}