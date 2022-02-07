package com.example.persistence;

import com.example.domain.UserWishes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWishesRepository extends CrudRepository<UserWishes, String> {

}
