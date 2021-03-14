# How To Run & Test
1. Import the project as existing project in any IDE like STS or Eclipse.
2. Navigate to the main class : com.sample.controller.AssignmentApplication and run as java application.
3. In postman or browser enter below urls to get desired output:
	a. http://localhost:8080/cityCodes :: To fetch all city codes present with their number of records.
	b. http://localhost:8080/topTwo?city=CityName :: To fetch top two city code candidates for a given city.
	c. http://localhost:8080/transformData :: To fetch transformed data as asked in Question2 of assignment.
