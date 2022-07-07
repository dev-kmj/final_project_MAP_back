package com.sparta.finalprojectback.community.service;

import com.sparta.finalprojectback.community.dto.CommunityRequestDto;
import com.sparta.finalprojectback.community.dto.CommunityResponseDto;
import com.sparta.finalprojectback.community.model.Community;
import com.sparta.finalprojectback.community.repository.CommunityRepository;
import com.sparta.finalprojectback.member.Member;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    // 삭제
    @Transactional(rollbackFor = Exception.class)
    public void deleteCommunity(Long id, Member member) {
        Community community = communityRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 게시물이 없습니다."));
        communityRepository.deleteCommunityByIdAndMember(id, member);
    }


    // 수정
//    @Transactional(rollbackFor = Exception.class)
//    public void updateCommunity(Long id, CommunityRequestDto requestDto, Member member) {
//        Community community = communityRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("해당하는 게시물이 존재하지 않습니다.")
//        );
//        community.updateCommunity(id, requestDto, member);
//        communityRepository.save(community);
//    }


    // 상세 조회
//    @Transactional
//    public CommunityResponseDto communityDetail(Long id) {
//        Community community = communityRepository.findById(id).orElseThrow(
//                () -> new NullPointerException("게시물이 없습니다.")
//        );
//        CommunityResponseDto responseDto = new CommunityResponseDto();
//        responseDto.setPostId(community.getId());
//        responseDto.setTitle(community.getTitle());
//        responseDto.setContent(community.getContent());
//        responseDto.setMember(community.getMember().getNickname());
//        return responseDto;
//    }


    // 나의 게시물 조회
//    public List<CommunityResponseDto> getMyCommunityList(Member member) {
//
//        List<Community> communityList = communityRepository.findAllByMemberId(member.getId());
//        List<CommunityResponseDto> communityResponseDtoList = new ArrayList<>();
//
//        for (Community community : communityList) {
//            communityResponseDtoList.add(new CommunityResponseDto(community.getId(), community.getTitle(),
//                    community.getContent(), community.getMember().getNickname(), community.getCreatedAt(), community.getModifiedAt()));
//        }
//        return communityResponseDtoList;
//    }



    /**
     * 엔티티부터 모든 클래스 코드 리팩토링
     * 페이징 처리
     */

    // 게시물 등록
    @Transactional
    public Long createCommunity(CommunityRequestDto requestDto, Member member) {
        Community community = new Community(requestDto, member);
        return communityRepository.save(community).getId();
    }


    // 조회
    @Transactional(readOnly = true)
    public List<CommunityResponseDto> getCommunityList() {

        // CommunityRepository에서 반환받은건 List<Community>형태
        // .stream().map()을 이용해 ResponseDto로 변환한 후
        // 리스트로 반환
        return communityRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
    }


    // 페이징처리 (조회)
    public Page<CommunityResponseDto> getAllCommunities(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return communityRepository.findAllByOrderByCreatedAtDesc(pageable).map(CommunityResponseDto::new);
    }

}