package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Img;
import com.example.FORMANTO.repository.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImgService {

    private final ImgRepository imgRepository;

    public List<Img> findByProductGroupId(Long productGroupId){
        return imgRepository.findByProductGroupId(productGroupId)
                .orElseThrow(() -> new IllegalArgumentException("not found imgs"));
    }

    public List<String> getInfoImgSrc(Long productGroupId){

        List<String> list = new ArrayList<>();
        findByProductGroupId(productGroupId).forEach(img -> list.add(img.getSrc()));

        return list;
    }
}
