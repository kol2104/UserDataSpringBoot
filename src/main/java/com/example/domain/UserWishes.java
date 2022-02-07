package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "user_wishes")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWishes {
    @Id
    @GeneratedValue
    private String id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    private User user;
    @Column(name = "price_settings_id", nullable = false)
    private String priceSettingsId;
    @Column(name = "item2find_id", nullable = false)
    private String item2FindId;
    private String values;

    @Transient
    private String userId;





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserWishes that = (UserWishes) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
