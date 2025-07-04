package com.seti.webflux_test.infraestructure.drivenadapter;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.seti.webflux_test.domain.model.Branch;
import com.seti.webflux_test.domain.port.BranchRepository;
import com.seti.webflux_test.infraestructure.drivenadapter.data.BranchR2DBCEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

interface SpringDataBranchR2dbcRepository extends R2dbcRepository<BranchR2DBCEntity, Long> {

    public Flux<Branch> findByFranchiseId(Long id);

    Mono<Boolean> existsByName(String name);
}

@Repository
@RequiredArgsConstructor
public class BranchRepositoryImpl implements BranchRepository{
    
    private final SpringDataBranchR2dbcRepository springDataRepo;

    @Override
    public Mono<Branch> save(Branch branch) {
        return springDataRepo.save(BranchR2DBCEntity.fromDomain(branch))
                .map(BranchR2DBCEntity::toDomain);
    }

    @Override
    public Mono<Branch> findById(Long id) {
        return springDataRepo.findById(id).map(BranchR2DBCEntity::toDomain);
    }

    @Override
    public Flux<Branch> findAll() {
        return springDataRepo.findAll().map(BranchR2DBCEntity::toDomain);
    }

    @Override
    public Flux<Branch> findByFranchiseId(Long id) {
        return springDataRepo.findByFranchiseId(id);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return springDataRepo.existsByName(name);
    }
}
