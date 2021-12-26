package br.ce.daniel.rest;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class UserJsonTest {
   
	@Test//Junit
	public void deveVerificarPrimeiroNivel() {
	given().
	when().
		 get("https://restapi.wcaquino.me/users/1").
	then().
		  statusCode(200).
		  body("id",is(1)).
		  body("name", containsString("Silva")).
		  body("age", greaterThan(18));//ferifica se a idade é maior de 18
	}
		             
		@SuppressWarnings("deprecation")
		@Test
		public void primeiroNivelOutraForma() {
			Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users/1");
			
			// path
			Assert.assertEquals(new Integer(1), response.path("id"));
			Assert.assertEquals(new Integer(1), response.path("%s", "id"));
			
			//Jsonpath
			JsonPath jpath = new JsonPath(response.asString());
			Assert.assertEquals(1,jpath.getInt("id"));
			
			//from
			int id = JsonPath.from(response.asString()).getInt("id");
			Assert.assertEquals(1, id);
		}
}
