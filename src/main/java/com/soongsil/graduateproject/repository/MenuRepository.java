package com.soongsil.graduateproject.repository;

import com.soongsil.graduateproject.domain.Category;
import com.soongsil.graduateproject.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("select m from Menu m join fetch m.cafe c where c.id = :id and m.category = :category order by m.name desc")
    List<Menu> findMenuByCafeAndCategory(@Param("id") Long id, @Param("category") Category category);
}
