package com.example.persistence.mock;

import com.example.domain.User;
import com.example.domain.UserWish;
import com.example.persistence.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Profile("test")
@Service
public class MockUserRepository implements UserRepository {

    @Override
    public <S extends User> S save(S entity) {
        if (entity.getId() == null)
            entity.setId("1");
        return entity;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(String s) {
        List<User> userList = new ArrayList<>(Arrays.asList(
                User.builder().id("1").name("Ivan").email("ivan@mail.ru").userWishes(new HashSet<>(Arrays.asList(
                        new UserWish().builder().id("1").userId("1").priceSettingsId("1").item2FindId("1").values("100;120").build(),
                        new UserWish().builder().id("2").userId("1").priceSettingsId("4").item2FindId("5").values("500").build()
                ))).build(),
                User.builder().id("2").name("Petr").email("petr@mail.ru").userWishes(new HashSet<>(Arrays.asList(
                        new UserWish().builder().id("3").userId("2").priceSettingsId("2").item2FindId("2").values("1000").build()
                ))).build(),
                User.builder().id("3").name("Maksim").email("maksim@mail.ru").build()
        ));
        return userList.stream().filter(user -> user.getId().equals(s)).findFirst();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        List<User> userList = new ArrayList<>(Arrays.asList(
                new User().builder().id("1").name("Ivan").email("ivan@mail.ru").userWishes(new HashSet<>(Arrays.asList(
                        new UserWish().builder().id("1").userId("1").priceSettingsId("1").item2FindId("1").values("100;120").build(),
                        new UserWish().builder().id("2").userId("1").priceSettingsId("4").item2FindId("5").values("500").build()
                ))).build(),
                new User().builder().id("2").name("Petr").email("petr@mail.ru").userWishes(new HashSet<>(Arrays.asList(
                        new UserWish().builder().id("3").userId("2").priceSettingsId("2").item2FindId("2").values("1000").build()
                ))).build()
        ));
        return userList;
    }

    @Override
    public Iterable<User> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
