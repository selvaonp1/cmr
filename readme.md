## Project Setup
```sh 
proj.basedir > mvn install:install-file -Dfile=./spring-boot-project-user/src/main/resources/sqlite-dialect-0.1.0.jar 
proj.basedir > mvn clean install (or) mvn clean install -DskipTests
proj.basedir > cd spring-boot-project-user
proj.basedir/spring-boot-project-user > mvn exec:java
```
  repeat steps 3 and 4 for each projects in a new terminal

## List of services in the project:

#### (1) **spring-boot-project-user** : 
	User service for getting user information.
	End points: Get: /user/{username}
	Table: User
#### (2) **spring-boot-project-content**: 
	Content servie returns the netfix api as a json. The actual API was not working at the time of development
	End Points: Get/content
#### (3) **spring-boot-project-content-rating**: 
	Service that hosts the Cognizant movie rating and also computes average rating. Initial rating is got from the Netfix API provided rating.
    End Points: Get /contentrating
				  Post /contentrating 
	Table: Content-Rating
#### (4) **spring-boot-project-user-content-rating**: 
	Service for posting user ratings. Prevents users from posting second time.
	End Points: Get /user/{username}/contentrating
				  Post /user/{username}/contentrating
	Table: User-Content-Rating
#### (5) **spring-boot-project-discovoery**: 
	Service Discovery Service: Eureka instances are registered as clients and discovered as Spring-managed beans 
#### (6) spring-boot-project-gateway: 
	This is the orchestration layer. Service that integrates, Ribbon, Feign and Zuul for load balancing, Rest templating and reverse proxy for all micro services.
	End Points: get /gateway/user/{username}/content
		  		  post /gateway/user/{username}/contentrating

## Sequence of classes involved in a regular invocation:
	- Controller -> Service -> ServiceDelegate -> Dao/Repository : Complex calls involving business logic
	- Controller -> Service -> Dao/Repository : Simple calls
	
## Tests
	- Integration Tests: Tests are in the src/test/java folder and the package is identical to the controller package
	- Unit tests: Tests are in package identical to the src/java service package
	- Test and Dev have difference profiles hence test data wont pollute the dev database
	
## Test Scenarios:
#### (I) Success Scenarios: 

##### (a) Endpoint for gettig movie content
- Get:
- http://localhost:9016/gateway/user/user2/content
- Status: Ok 200
```json 
[{"contentType":null,"unit":"84","rating":3.8,"category":"Action & Adventure","director":"Quentin Tarantino","summary":"The Bride has three left on her rampage list: Budd, Elle Driver and Bill himself. But when she arrives at Bill's house, she's in for a surprise.","poster":"http://netflixroulette.net/api/posters/60032563.jpg","runtime":"137 min","myRating":0.0,"show_id":"60032563","mediatype":0,"show_title":"Kill Bill: Vol. 2","release_year":"2004","show_cast":"Uma Thurman, David Carradine, Michael Madsen, Daryl Hannah, Gordon Liu, Michael Parks, Perla Haney-Jardine, Helen Kim, Claire Smithies, Clark Middleton"},{"contentType":null,"unit":"87","rating":3.8,"category":"Action & Adventure","director":"Quentin Tarantino","summary":"An assassin is shot by her ruthless employer, Bill, and other members of their assassination circle. But she lives ... and plots her vengeance.","poster":"http://netflixroulette.net/api/posters/60031236.jpg","runtime":"111 min","myRating":0.0,"show_id":"60031236","mediatype":0,"show_title":"Kill Bill: Vol. 1","release_year":"2003","show_cast":"Uma Thurman, Lucy Liu, Vivica A. Fox, Daryl Hannah, David Carradine, Michael Madsen, Julie Dreyfus, Chiaki Kuriyama, Sonny Chiba, Gordon Liu"},{"contentType":null,"unit":"914","rating":4.1,"category":"Oscar-winning Movies","director":"Quentin Tarantino","summary":"Weaving together three stories featuring a burger-loving hit man, his philosophical partner and a washed-up boxer, Quentin Tarantino influenced a generation of filmmakers with this crime caper's stylized, over-the-top violence and dark comic spirit.","poster":"http://netflixroulette.net/api/posters/880640.jpg","runtime":"154 min","myRating":0.0,"show_id":"880640","mediatype":0,"show_title":"Pulp Fiction","release_year":"1994","show_cast":"John Travolta, Samuel L. Jackson, Uma Thurman, Bruce Willis, Harvey Keitel, Tim Roth, Amanda Plummer, Ving Rhames, Eric Stoltz, Maria de Medeiros"},{"contentType":null,"unit":"943","rating":3.7,"category":"Dramas","director":"Quentin Tarantino","summary":"Jackie Brown is an aging flight attendant who smuggles cash on the side. But when she's busted and pressured to help with an investigation, she plans to play the opposing forces against each other and walk away with the dough.","poster":"http://netflixroulette.net/api/posters/60010514.jpg","runtime":"154 min","myRating":0.0,"show_id":"60010514","mediatype":0,"show_title":"Jackie Brown","release_year":"1997","show_cast":"Pam Grier, Samuel L. Jackson, Robert Forster, Bridget Fonda, Michael Keaton, Robert De Niro, Michael Bowen, Chris Tucker, Lisa Gay Hamilton, Tommy 'Tiny' Lister"},{"contentType":null,"unit":"1151","rating":4.0,"category":"Independent Movies","director":"Quentin Tarantino","summary":"Quentin Tarantino's directorial debut is raw, violent, often mimicked ... and unforgettable. A botched robbery indicates a police informant, and the pressure mounts in the aftermath at a warehouse. Crime begets violence as the survivors unravel.","poster":"http://netflixroulette.net/api/posters/902003.jpg","runtime":"99 min","myRating":0.0,"show_id":"902003","mediatype":0,"show_title":"Reservoir Dogs","release_year":"1992","show_cast":"Harvey Keitel, Tim Roth, Michael Madsen, Steve Buscemi, Chris Penn, Lawrence Tierney, Edward Bunker, Quentin Tarantino, Randy Brooks, Kirk Baltz"},{"contentType":null,"unit":"1463","rating":6.8500004,"category":"Comedies","director":"Quentin Tarantino, Robert Rodriguez, Allison Anders, Alexandre Rockwell","summary":"One mad New Year's Eve, an overwhelmed bellboy copes with witches and diabolical children, gets caught in the middle of a sour relationship and settles a bloody bet for members of a superstar's entourage.","poster":"http://netflixroulette.net/api/posters/520179.jpg","runtime":"98 min","myRating":10.1,"show_id":"520179","mediatype":0,"show_title":"Four Rooms","release_year":"1995","show_cast":"Tim Roth, Antonio Banderas, Jennifer Beals, Bruce Willis, Paul Calderon, Madonna, Marisa Tomei, Quentin Tarantino, Ione Skye, Lili Taylor"}]
```
##### (b) Endpoint for posting rating, where user is an existing user in the system.
- Post:
- http://localhost:9016/gateway/user/user2/contentrating
- Status: 201 Created
```json 
{
	"contentType": "Netflix Roulette",
	"contentId": "60032563",
	"rating": 10.1
}
```
##### (c ) Get content to verify the posted rating is reflected in average and myrating for user2

- Get: 
- http://localhost:9016/gateway/user/user2/content
- Status: 200 OK

```json 
[{"contentType":null,"unit":"84","rating":6.9500003,"category":"Action & Adventure","director":"Quentin Tarantino","summary":"The Bride has three left on her rampage list: Budd, Elle Driver and Bill himself. But when she arrives at Bill's house, she's in for a surprise.","poster":"http://netflixroulette.net/api/posters/60032563.jpg","runtime":"137 min","myRating":10.1,"show_id":"60032563","mediatype":0,"show_title":"Kill Bill: Vol. 2","release_year":"2004","show_cast":"Uma Thurman, David Carradine, Michael Madsen, Daryl Hannah, Gordon Liu, Michael Parks, Perla Haney-Jardine, Helen Kim, Claire Smithies, Clark Middleton"},{"contentType":null,"unit":"87","rating":3.8,"category":"Action & Adventure","director":"Quentin Tarantino","summary":"An assassin is shot by her ruthless employer, Bill, and other members of their assassination circle. But she lives ... and plots her vengeance.","poster":"http://netflixroulette.net/api/posters/60031236.jpg","runtime":"111 min","myRating":0.0,"show_id":"60031236","mediatype":0,"show_title":"Kill Bill: Vol. 1","release_year":"2003","show_cast":"Uma Thurman, Lucy Liu, Vivica A. Fox, Daryl Hannah, David Carradine, Michael Madsen, Julie Dreyfus, Chiaki Kuriyama, Sonny Chiba, Gordon Liu"},{"contentType":null,"unit":"914","rating":4.1,"category":"Oscar-winning Movies","director":"Quentin Tarantino","summary":"Weaving together three stories featuring a burger-loving hit man, his philosophical partner and a washed-up boxer, Quentin Tarantino influenced a generation of filmmakers with this crime caper's stylized, over-the-top violence and dark comic spirit.","poster":"http://netflixroulette.net/api/posters/880640.jpg","runtime":"154 min","myRating":0.0,"show_id":"880640","mediatype":0,"show_title":"Pulp Fiction","release_year":"1994","show_cast":"John Travolta, Samuel L. Jackson, Uma Thurman, Bruce Willis, Harvey Keitel, Tim Roth, Amanda Plummer, Ving Rhames, Eric Stoltz, Maria de Medeiros"},{"contentType":null,"unit":"943","rating":3.7,"category":"Dramas","director":"Quentin Tarantino","summary":"Jackie Brown is an aging flight attendant who smuggles cash on the side. But when she's busted and pressured to help with an investigation, she plans to play the opposing forces against each other and walk away with the dough.","poster":"http://netflixroulette.net/api/posters/60010514.jpg","runtime":"154 min","myRating":0.0,"show_id":"60010514","mediatype":0,"show_title":"Jackie Brown","release_year":"1997","show_cast":"Pam Grier, Samuel L. Jackson, Robert Forster, Bridget Fonda, Michael Keaton, Robert De Niro, Michael Bowen, Chris Tucker, Lisa Gay Hamilton, Tommy 'Tiny' Lister"},{"contentType":null,"unit":"1151","rating":4.0,"category":"Independent Movies","director":"Quentin Tarantino","summary":"Quentin Tarantino's directorial debut is raw, violent, often mimicked ... and unforgettable. A botched robbery indicates a police informant, and the pressure mounts in the aftermath at a warehouse. Crime begets violence as the survivors unravel.","poster":"http://netflixroulette.net/api/posters/902003.jpg","runtime":"99 min","myRating":0.0,"show_id":"902003","mediatype":0,"show_title":"Reservoir Dogs","release_year":"1992","show_cast":"Harvey Keitel, Tim Roth, Michael Madsen, Steve Buscemi, Chris Penn, Lawrence Tierney, Edward Bunker, Quentin Tarantino, Randy Brooks, Kirk Baltz"},{"contentType":null,"unit":"1463","rating":6.8500004,"category":"Comedies","director":"Quentin Tarantino, Robert Rodriguez, Allison Anders, Alexandre Rockwell","summary":"One mad New Year's Eve, an overwhelmed bellboy copes with witches and diabolical children, gets caught in the middle of a sour relationship and settles a bloody bet for members of a superstar's entourage.","poster":"http://netflixroulette.net/api/posters/520179.jpg","runtime":"98 min","myRating":10.1,"show_id":"520179","mediatype":0,"show_title":"Four Rooms","release_year":"1995","show_cast":"Tim Roth, Antonio Banderas, Jennifer Beals, Bruce Willis, Paul Calderon, Madonna, Marisa Tomei, Quentin Tarantino, Ione Skye, Lili Taylor"}]
```
## (II) Failure Scenarios: 
##### (d) User is not allowed to rate the movie more than once
- Post:
- http://localhost:9016/gateway/user/user2/contentrating
- Status: 405 Method Not Allowed
```json 
{
	"contentType": "Netflix Roulette",
	"contentId": "60032563",
	"rating": 10.1
} 
```
##### (e) 404 is returned when user is not valid
- Get:
- http://localhost:9016/gateway/user/user11/content
- Status 404 Not Found


