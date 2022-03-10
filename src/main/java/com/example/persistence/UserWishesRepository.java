package com.example.persistence;

import com.example.domain.UserWish;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public interface UserWishesRepository extends CrudRepository<UserWish, String> {

}
