package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QnARepository extends JpaRepository<QnA, Long> {

    //제품상세 안의 큐엔에이
    Optional<List<QnA>> findByProductGroupId(Long productGroupId);

    //마이페이지 안의 큐엔에이
    Optional<List<QnA>> findByCustomerId(Long customerId);
}
