package io.javaproject.ipldashboard.repo;

import org.springframework.data.repository.CrudRepository;

import io.javaproject.ipldashboard.model.Team;

public interface TeamRepo extends CrudRepository<Team, Long> {

    Team findByTeamName(String teamName);
}
