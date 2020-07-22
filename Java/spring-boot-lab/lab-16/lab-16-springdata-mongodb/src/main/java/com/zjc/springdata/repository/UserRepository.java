package com.zjc.springdata.repository;

import com.zjc.springdata.dataobject.UserDO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDO, Integer> {
}
