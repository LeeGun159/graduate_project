package com.soongsil.graduateproject.service;

import com.soongsil.graduateproject.domain.Category;
import com.soongsil.graduateproject.domain.Menu;
import com.soongsil.graduateproject.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;

    public void saveMenu(Menu menu, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        String filePath = System.getProperty("user.die") + "/src/main/resources/static/files/";

        UUID uuid = UUID.randomUUID();

        String savedFileName = uuid + "_" + fileName;

        File savedFile = new File(filePath, savedFileName);

        file.transferTo(savedFile);

        menu.setImage(savedFileName);

        menuRepository.save(menu);
    }

    public List<Menu> findMenu(Long cafe_id, Category category) {
        return menuRepository.findMenuByCafeAndCategory(cafe_id, category);
    }
}
