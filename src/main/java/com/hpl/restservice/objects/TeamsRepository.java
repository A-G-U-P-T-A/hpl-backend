package com.hpl.restservice.objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TeamsRepository extends MongoRepository<Teams, String> {

    @Query("{name:'?0'}") List<Teams> findTeamByName(String name);
    //@Query("{name:'?0'}") List<Teams> findCustomByAddress(String name);
    Page<Teams> findAll(Pageable pageable);
    Page<Teams> findByScore(int score, Pageable pageable);
    Page<Teams> findByName(String name, Pageable pageable);

}
