package com.soongsil.graduateproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id @GeneratedValue
    @Column(name = "menu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    private String name;

    private int price;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String imageName;

    private String imagePath;

    public void setImage(String fileName) {
        this.imageName = fileName;
        this.imagePath = "/files/" + fileName;
    }
}
