package com.example.persistence.mock;

import com.example.domain.UserWish;
import com.example.persistence.UserWishesRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile("test")
public class MockUserWishesRepository implements UserWishesRepository {
    @Override
    public <S extends UserWish> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserWish> Iterable<S> saveAll(Iterable<S> entities) {
        int num = 1;
        for (S userWish : entities) {
            if (userWish.getId() == null)
                userWish.setId(String.valueOf(num++));
        }
        return entities;
    }

    @Override
    public Optional<UserWish> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<UserWish> findAll() {
        return null;
    }

    @Override
    public Iterable<UserWish> findAllById(Iterable<String> strings) {
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
    public void delete(UserWish entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserWish> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
