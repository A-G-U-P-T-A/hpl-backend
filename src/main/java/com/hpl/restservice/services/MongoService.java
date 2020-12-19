package com.hpl.restservice.services;

import com.hpl.restservice.objects.Teams;
import com.hpl.restservice.objects.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class MongoService {

    @Autowired TeamsRepository teamsRepository;

    public String addNewTeam(Teams teams) {
        teamsRepository.save(teams);
        return null;
    }
    public List<Teams> getAllTeams() {
        return teamsRepository.findAll();
    }
}
