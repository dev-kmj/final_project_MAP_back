package com.sparta.finalprojectback.community;

import com.sparta.finalprojectback.member.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommunityService {

    private final CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }


    // 삭제
    @Transactional(rollbackFor = Exception.class)
    public void deleteCommunity(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 게시물이 없습니다."));
        communityRepository.deleteCommunityById(id);
    }


    // 수정
    @Transactional(rollbackFor = Exception.class)
    public void updateCommunity(Long id, CommunityRequestDto requestDto) {
        Community community = communityRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시물이 존재하지 않습니다.")
        );
        community.updateCommunity(id,requestDto);
        communityRepository.save(community);
    }
}
