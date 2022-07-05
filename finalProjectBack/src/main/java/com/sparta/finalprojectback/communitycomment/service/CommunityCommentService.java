package com.sparta.finalprojectback.communitycomment.service;


import com.sparta.finalprojectback.community.Community;
import com.sparta.finalprojectback.community.repository.CommunityRepository;
import com.sparta.finalprojectback.communitycomment.dto.CommunityCommentRequestDto;
import com.sparta.finalprojectback.communitycomment.dto.CommunityCommentResponseDto;
import com.sparta.finalprojectback.communitycomment.model.CommunityComment;
import com.sparta.finalprojectback.communitycomment.repository.CommunityCommentRepository;
import com.sparta.finalprojectback.member.Member;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityCommentService {

    private final CommunityCommentRepository communityCommentRepository;
    private final CommunityRepository communityRepository;

    // 댓글 작성
    @Transactional
    public Long createComment(Long communityId, CommunityCommentRequestDto requestDto, Member member) {
        Community community = communityRepository.findById(communityId).orElseGet(null);
        CommunityComment communityComment = new CommunityComment(requestDto, community, member);

//        community.addComment(communityComment);
        return communityCommentRepository.save(communityComment).getId();
    }


     // 조회
    public List<CommunityCommentResponseDto> getCommunityComments(Long communityId) {
        List<CommunityComment> communityCommentList = communityCommentRepository.findAllByCommunity_Id(communityId);
        return getCommunityCommentResponseDtos(communityCommentList);
    }

    @NotNull
    private List<CommunityCommentResponseDto> getCommunityCommentResponseDtos(List<CommunityComment> communityCommentList) {
        List<CommunityCommentResponseDto> communityCommentResponseDtoList = new ArrayList<>();

        for (CommunityComment communityComment : communityCommentList) {
            communityCommentResponseDtoList.add(new CommunityCommentResponseDto(communityComment.getId(),
                    communityComment.getComment(), communityComment.getMember().getNickname(),
                    communityComment.getCreatedAt()));
        }
        return communityCommentResponseDtoList;
    }

    // 삭제
    @Transactional(rollbackFor = Exception.class)
    public void deleteCommunityComment(Long id, Member member) {

        communityCommentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 댓글이 없습니다."));
        communityCommentRepository.deleteByIdAndMember(id, member);
    }


    @Transactional(rollbackFor = Exception.class)
    public void deleteAllCommunityComments(Long id) {
        communityCommentRepository.deleteAllByCommunity_Id(id);
    }
}