package com.example.vti.hospital.repositories;

import com.example.vti.hospital.models.CLientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientRequestRepository extends MongoRepository<CLientRequest, String> {
    Optional<CLientRequest> findById(long id);
    Optional<CLientRequest> findByClientRequest(String clientRequest);

}
