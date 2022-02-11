# Database
	Tables
	
		1. Team((pk) id, name)
		2. Athlete((pk) id, name)
		3. Team_Athlete((pk) id, (fk)team_id, (fk)athlete_id)

# Rest
	Controllers
	
		1. Team Controller
			1. getAllTeams() GET /teams
			2. getTeam() GET /teams/{id}
			3. getAthletes() GET /teams/{id}/athletes
			4. addTeam() POST /teams RequestBody Team
			5. addAthlete() PUT /teams/{id}/{athleteId}
			6. updateTeam() PUT /teams/{id} RequestBody Team
			7. deleteTeam() DELETE /teams/{id}
			
		2. Athlete Controller
			1. getAllAthletes() GET /athletes
			2. getAthlete() GET /athletes/{id}
			3. getTeams() GET /athletes/{id}/teams
			4. addAthlete() POST /athletes RequestBody Athlete
			5. addTeam() PUT /athletes/{id}/{teamId}
			6. updateAthlete() PUT /athletes/{id} RequestBody Athlete
			7. deleteAthlete() DELETE /athletes/{id}
			
