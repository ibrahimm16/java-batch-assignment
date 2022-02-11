package com.ibrahim.springtest.team_athlete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Team_AthleteRepository extends JpaRepository<Team_Athlete, Long> {
}
