package com.hpl.restservice.helpers;

import com.hpl.restservice.objects.Teams;
import com.hpl.restservice.objects.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service public class TeamHelper {

    @Autowired TeamsRepository teamsRepository;
    @Autowired MongoOperations mongoOperations;

    public ResponseEntity<Map<String, Object>> getTeams(String sortElement, String sortOrder, String name, int score, int page, int size) {
        List<Teams> teams = null;
        Page<Teams> pageTuts;
        Pageable paging = getPageRequest(sortElement, sortOrder, page, size);
        if (name.equals("") && score <=0){
            pageTuts = teamsRepository.findAll(paging);
        }
        else if(score > 0) {
            pageTuts = teamsRepository.findByScore(score, paging);
        }
        else {
            pageTuts = teamsRepository.findByName(name, paging);
        }

        teams = pageTuts.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("teams", teams);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Pageable getPageRequest(String sortElement, String sortOrder, int page, int size) {
        Sort sort = null;
        if (!sortElement.equals("") && !sortOrder.equals(""))
            if(sortOrder.equals("ascending"))
                sort = Sort.by(sortElement).ascending();
            else if (sortOrder.equals("descending"))
                sort = Sort.by(sortElement).descending();
        if(sort==null)
            return PageRequest.of(page, size);
        else
            return PageRequest.of(page, size, sort);
    }

    public ResponseEntity<String> createTeam(Teams teams) {
        Teams savedObject = teamsRepository.save(teams);
        return new ResponseEntity<String>(savedObject.toString(), HttpStatus.OK);
    }

    public ResponseEntity<String> updateTeams(List<Teams> teams) {
        for (Teams team: teams) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(team.getId()));
            Teams currTeam =  mongoOperations.findOne(query, Teams.class);
            getNewObject(currTeam, team);
            mongoOperations.save(currTeam);
        }
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    private void getNewObject(Teams currTeam, Teams team) {
        //if(currTeam.getWins()<team.getWins())
        currTeam.setWins(currTeam.getWins()+team.getWins());
        currTeam.setLosses(currTeam.getLosses()+team.getLosses());
        currTeam.setTies(currTeam.getTies()+team.getTies());
        currTeam.setScore(currTeam.getWins()*3+currTeam.getTies());
    }


    public ResponseEntity<List<Teams>> getTeamsByName(String name) {
        List<Teams>teams = teamsRepository.findTeamByName(name);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }
}
