package com.cmr.cloud.content.service;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cmr.cloud.content.domain.Content;
import com.cmr.cloud.content.domain.ContentType;

public class NetFlixRoletteContentServiceImpl implements ContentService {

	@Override
	public List<Content> getContent(ContentSearchCriteria contentSearchCriteria) {
		ObjectMapper mapper = new ObjectMapper();
		List<Content> contentList = null;
		try {
			Content content[] = mapper.readValue(NETFLIX_RESPONSE, Content[].class);
			contentList = Arrays.asList(content);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentList;
	}

	@Override
	public ContentType getContentType() {
		return ContentType.NETFLIXMOVIES;
	}

	private static final String NETFLIX_RESPONSE = "[\r\n" + 
			"   {\r\n" + 
			"      \"unit\":84,\r\n" + 
			"      \"show_id\":60032563,\r\n" + 
			"      \"show_title\":\"Kill Bill: Vol. 2\",\r\n" + 
			"      \"release_year\":\"2004\",\r\n" + 
			"      \"rating\":\"3.8\",\r\n" + 
			"      \"category\":\"Action & Adventure\",\r\n" + 
			"      \"show_cast\":\"Uma Thurman, David Carradine, Michael Madsen, Daryl Hannah, Gordon Liu, Michael Parks, Perla Haney-Jardine, Helen Kim, Claire Smithies, Clark Middleton\",\r\n" + 
			"      \"director\":\"Quentin Tarantino\",\r\n" + 
			"      \"summary\":\"The Bride has three left on her rampage list: Budd, Elle Driver and Bill himself. But when she arrives at Bill's house, she's in for a surprise.\",\r\n" + 
			"      \"poster\":\"http://netflixroulette.net/api/posters/60032563.jpg\",\r\n" + 
			"      \"mediatype\":0,\r\n" + 
			"      \"runtime\":\"137 min\"\r\n" + 
			"   },\r\n" + 
			"   {\r\n" + 
			"      \"unit\":87,\r\n" + 
			"      \"show_id\":60031236,\r\n" + 
			"      \"show_title\":\"Kill Bill: Vol. 1\",\r\n" + 
			"      \"release_year\":\"2003\",\r\n" + 
			"      \"rating\":\"3.8\",\r\n" + 
			"      \"category\":\"Action & Adventure\",\r\n" + 
			"      \"show_cast\":\"Uma Thurman, Lucy Liu, Vivica A. Fox, Daryl Hannah, David Carradine, Michael Madsen, Julie Dreyfus, Chiaki Kuriyama, Sonny Chiba, Gordon Liu\",\r\n" + 
			"      \"director\":\"Quentin Tarantino\",\r\n" + 
			"      \"summary\":\"An assassin is shot by her ruthless employer, Bill, and other members of their assassination circle. But she lives -- and plots her vengeance.\",\r\n" + 
			"      \"poster\":\"http://netflixroulette.net/api/posters/60031236.jpg\",\r\n" + 
			"      \"mediatype\":0,\r\n" + 
			"      \"runtime\":\"111 min\"\r\n" + 
			"   },\r\n" + 
			"   {\r\n" + 
			"      \"unit\":914,\r\n" + 
			"      \"show_id\":880640,\r\n" + 
			"      \"show_title\":\"Pulp Fiction\",\r\n" + 
			"      \"release_year\":\"1994\",\r\n" + 
			"      \"rating\":\"4.1\",\r\n" + 
			"      \"category\":\"Oscar-winning Movies\",\r\n" + 
			"      \"show_cast\":\"John Travolta, Samuel L. Jackson, Uma Thurman, Bruce Willis, Harvey Keitel, Tim Roth, Amanda Plummer, Ving Rhames, Eric Stoltz, Maria de Medeiros\",\r\n" + 
			"      \"director\":\"Quentin Tarantino\",\r\n" + 
			"      \"summary\":\"Weaving together three stories featuring a burger-loving hit man, his philosophical partner and a washed-up boxer, Quentin Tarantino influenced a generation of filmmakers with this crime caper's stylized, over-the-top violence and dark comic spirit.\",\r\n" + 
			"      \"poster\":\"http://netflixroulette.net/api/posters/880640.jpg\",\r\n" + 
			"      \"mediatype\":0,\r\n" + 
			"      \"runtime\":\"154 min\"\r\n" + 
			"   },\r\n" + 
			"   {\r\n" + 
			"      \"unit\":943,\r\n" + 
			"      \"show_id\":60010514,\r\n" + 
			"      \"show_title\":\"Jackie Brown\",\r\n" + 
			"      \"release_year\":\"1997\",\r\n" + 
			"      \"rating\":\"3.7\",\r\n" + 
			"      \"category\":\"Dramas\",\r\n" + 
			"      \"show_cast\":\"Pam Grier, Samuel L. Jackson, Robert Forster, Bridget Fonda, Michael Keaton, Robert De Niro, Michael Bowen, Chris Tucker, Lisa Gay Hamilton, Tommy 'Tiny' Lister\",\r\n" + 
			"      \"director\":\"Quentin Tarantino\",\r\n" + 
			"      \"summary\":\"Jackie Brown is an aging flight attendant who smuggles cash on the side. But when she's busted and pressured to help with an investigation, she plans to play the opposing forces against each other and walk away with the dough.\",\r\n" + 
			"      \"poster\":\"http://netflixroulette.net/api/posters/60010514.jpg\",\r\n" + 
			"      \"mediatype\":0,\r\n" + 
			"      \"runtime\":\"154 min\"\r\n" + 
			"   },\r\n" + 
			"   {\r\n" + 
			"      \"unit\":1151,\r\n" + 
			"      \"show_id\":902003,\r\n" + 
			"      \"show_title\":\"Reservoir Dogs\",\r\n" + 
			"      \"release_year\":\"1992\",\r\n" + 
			"      \"rating\":\"4.0\",\r\n" + 
			"      \"category\":\"Independent Movies\",\r\n" + 
			"      \"show_cast\":\"Harvey Keitel, Tim Roth, Michael Madsen, Steve Buscemi, Chris Penn, Lawrence Tierney, Edward Bunker, Quentin Tarantino, Randy Brooks, Kirk Baltz\",\r\n" + 
			"      \"director\":\"Quentin Tarantino\",\r\n" + 
			"      \"summary\":\"Quentin Tarantino's directorial debut is raw, violent, often mimicked -- and unforgettable. A botched robbery indicates a police informant, and the pressure mounts in the aftermath at a warehouse. Crime begets violence as the survivors unravel.\",\r\n" + 
			"      \"poster\":\"http://netflixroulette.net/api/posters/902003.jpg\",\r\n" + 
			"      \"mediatype\":0,\r\n" + 
			"      \"runtime\":\"99 min\"\r\n" + 
			"   },\r\n" + 
			"   {\r\n" + 
			"      \"unit\":1463,\r\n" + 
			"      \"show_id\":520179,\r\n" + 
			"      \"show_title\":\"Four Rooms\",\r\n" + 
			"      \"release_year\":\"1995\",\r\n" + 
			"      \"rating\":\"3.6\",\r\n" + 
			"      \"category\":\"Comedies\",\r\n" + 
			"      \"show_cast\":\"Tim Roth, Antonio Banderas, Jennifer Beals, Bruce Willis, Paul Calderon, Madonna, Marisa Tomei, Quentin Tarantino, Ione Skye, Lili Taylor\",\r\n" + 
			"      \"director\":\"Quentin Tarantino, Robert Rodriguez, Allison Anders, Alexandre Rockwell\",\r\n" + 
			"      \"summary\":\"One mad New Year's Eve, an overwhelmed bellboy copes with witches and diabolical children, gets caught in the middle of a sour relationship and settles a bloody bet for members of a superstar's entourage.\",\r\n" + 
			"      \"poster\":\"http://netflixroulette.net/api/posters/520179.jpg\",\r\n" + 
			"      \"mediatype\":0,\r\n" + 
			"      \"runtime\":\"98 min\"\r\n" + 
			"   }\r\n" + 
			"]";
}
