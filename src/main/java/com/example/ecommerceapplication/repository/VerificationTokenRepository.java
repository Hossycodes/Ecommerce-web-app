package com.example.ecommerceapplication.repository;

import com.example.ecommerceapplication.entitites.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends ListCrudRepository<VerificationToken, Long> {

}
