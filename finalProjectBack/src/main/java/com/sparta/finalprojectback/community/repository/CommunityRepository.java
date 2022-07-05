package com.sparta.finalprojectback.community.repository;

import com.sparta.finalprojectback.community.Community;
import com.sparta.finalprojectback.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findAllByOrderByCreatedAtDesc();

    List<Community> deleteCommunityByIdAndMember(Long id, Member member);

    List<Community> findAllByMemberId(Long memberId);

    // 페이징
//    Page<Community> findAllByOrderByCreatedAtDesc();
}