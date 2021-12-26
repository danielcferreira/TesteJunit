package br.ce.daniel.rest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class Main {

	public static void main(String[] args) {
		/*aqui ele criou as requisições */
		Response response = RestAssured.
		request(Method.GET,"https://restapi.wcaquino.me/ola" );
		
		System.out.println(response.getBody().asString().equals("Ola Mundo!"));
         System.out.println(response.statusCode() == 200);
         
         /*aquie ele da um errro se caso eu mudar o valor de 200 para 201*/
         ValidatableResponse validacao = response.then(); 
         validacao.statusCode(200);
	}

}
