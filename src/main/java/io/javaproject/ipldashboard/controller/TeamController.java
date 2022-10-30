package io.javaproject.ipldashboard.controller;

import java.time.LocalDate;
import java.util.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.javaproject.ipldashboard.model.Match;
import io.javaproject.ipldashboard.model.Team;
import io.javaproject.ipldashboard.repo.MatchRepo;
import io.javaproject.ipldashboard.repo.TeamRepo;

@CrossOrigin
@RestController
public class TeamController {

    private TeamRepo teamRepo;
    private MatchRepo matchRepo;

    public TeamController(TeamRepo teamRepo, MatchRepo matchRepo) {
        this.teamRepo = teamRepo;
        this.matchRepo = matchRepo;

    }

    @GetMapping("/team")
    public Iterable<Team> getAllTeams() {
        // System.out.print("Hello");
        return this.teamRepo.findAll();

    }

    @GetMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        // System.out.print("Hello");
        Team team = this.teamRepo.findByTeamName(teamName);
        team.setMatches(this.matchRepo.findLatestMatchByTeam(teamName, 4));
        return team;
    }

    @GetMapping("/teams/{teamName}/matches")
    public List<Match> getMatchesByYear(@PathVariable String teamName, @RequestParam int year) {

        LocalDate date1 = LocalDate.of(year, 1, 1);
        LocalDate date2 = LocalDate.of(year + 1, 1, 1);

        return this.matchRepo.getMatchesByTeamBetweenDates(teamName, date1,
                date2);

    }
}
