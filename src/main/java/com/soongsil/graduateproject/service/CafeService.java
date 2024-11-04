package com.soongsil.graduateproject.service;

import com.soongsil.graduateproject.domain.Cafe;
import com.soongsil.graduateproject.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeService {

    private final CafeRepository cafeRepository;

    public List<Cafe> findAllCafe() {
        return cafeRepository.findAll();
    }

    @Transactional
    public void saveCafe(Cafe cafe) {
        cafeRepository.save(cafe);
    }
}
