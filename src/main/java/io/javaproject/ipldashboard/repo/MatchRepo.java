package io.javaproject.ipldashboard.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.javaproject.ipldashboard.model.Match;

public interface MatchRepo extends CrudRepository<Match, Long> {

    default List<Match> findLatestMatchByTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, 4));
    }

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :date1 and :date2 order by date desc")
    List<Match> getMatchesByTeamBetweenDates(@Param("teamName") String teamName, @Param("date1") LocalDate date1,
            @Param("date2") LocalDate date2);
    // List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
    // String teamName1, LocalDate date1, LocalDate date2,
    // String teamName2, LocalDate date3, LocalDate date4);

}
