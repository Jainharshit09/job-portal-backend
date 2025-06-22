package com.jobportal.Repository;

import com.jobportal.Entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile,Long> {
}
