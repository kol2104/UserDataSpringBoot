package com.example.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "user_wish")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWish {
    @Id
    private String id;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "price_settings_id", nullable = false)
    private String priceSettingsId;
    @Column(name = "item2find_id", nullable = false)
    private String item2FindId;
    private String values;
}
