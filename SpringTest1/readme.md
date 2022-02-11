Database
	Tables
		- Team((pk) id, name)
		- Athlete((pk) id, name)
		- Team_Athlete((pk) id, (fk)team_id, (fk)athlete_id)

Rest
	Controllers
		- Team Controller
			- getAllTeams() GET /teams
			- getTeam() GET /teams/{id}
			- getAthletes() GET /teams/{id}/athletes
			- addTeam() POST /teams RequestBody Team
			- addAthlete() PUT /teams/{id}/{athleteId}
			- updateTeam() PUT /teams/{id} RequestBody Team
			- deleteTeam() DELETE /teams/{id}
		- Athlete Controller
			- getAllAthletes() GET /athletes
			- getAthlete() GET /athletes/{id}
			- getTeams() GET /athletes/{id}/teams
			- addAthlete() POST /athletes RequestBody Athlete
			- addTeam() PUT /athletes/{id}/{teamId}
			- updateAthlete() PUT /athletes/{id} RequestBody Athlete
			- deleteAthlete() DELETE /athletes/{id}
			