package br.ce.daniel.rest;

/*aqui remover o (get) por asteriscos que ele pega toda a biblioteca*/
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlamundoTeste {
	@Test // metodo 1
	public void testOlaMundo() {
		/* aqui ele criou as requisi��es */
		Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");

		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode() == 200);
		/* aqui ele espera receber 200 mas esta recebendo 201 */
		Assert.assertEquals(200, response.statusCode());

		/* aquie ele da um errro se caso eu mudar o valor de 200 para 201 */
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}

	@Test
	/* aqui temos uma forma mais enxuta */
	public void conhecerOutraFormasRest() {
		Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);

		/* aqui fazendo o uso de ctr + shift + m, para integrar */
		get("https://restapi.wcaquino.me/ola").then().statusCode(200);

		/*
		 * tentando a metodo Given when Then = dado, quando, ent�o dado = precondi��o
		 * que eu queria aplicar quando = quando eu fizer a requisi��o ent�o = s�o as
		 * acertivas
		 */

		/* Given when Then -> sempre bem visiveis */
		given().// pre condi��es
				when().// a��o
				get("https://restapi.wcaquino.me/ola").then()// Assertivas
				.statusCode(200);

	}

	@Test// aula 9 hamcrest
	public void devoConhecerMatcgersHamcrest() {
		/* aqui estou comparando os nomes para ver se s�o equivalentes */
		assertThat("maria", Matchers.is("maria"));
		assertThat(128, Matchers.is(128));

		// para compara classe coloca se um a -> isA
		Assert.assertThat(128, Matchers.isA(Integer.class));
		Assert.assertThat(128d, Matchers.isA(Double.class));

		// para saber ser 128 � maior que
		Assert.assertThat(128d, Matchers.greaterThan(120d));
		Assert.assertThat(128d, Matchers.lessThan(130d));

		/* fazendo assert Com listas */
		// para saber se a lista � de 5 elementos
		List<Integer> impares = Arrays.asList(1, 3, 5, 7, 9);
		// analisando se o Array tem 5 elementos
		/* verifique que a cole��o impares tem o tamanho de 5 */
		assertThat(impares, hasSize(5));
		assertThat(impares, contains(1, 3, 5, 7, 9));

		/* para compara lo sem que esteja em ordem fa�o o uso containsInAnyOrder */
		assertThat(impares, containsInAnyOrder(1, 3, 5, 9, 7));

		/* para verificar apenas um elemento -> hasItem */
		assertThat(impares, hasItem(1));

		/* para verificar varios elementos -> hasItemS */
		assertThat(impares, hasItems(1, 5));

		/* Matchers alinhados */
		// maria n�o � jo�o -> fazendo igualdade
		assertThat("maria", not("joao"));

		/* fazendo um ou */
		assertThat("joaquina", anyOf(is("maria"), is("joaquina")));

		/* fazendo o & */
		assertThat("joaquina", allOf(startsWith("joa"), endsWith("ina"), containsString("qui")));

		/* ver documenta��o completa http://hamcrest.org/JavaHamcrest/javadoc/2.2/ */

	}
	//aula 10 - validar body 
	@Test
	public void devoValidarBody() {
		given().
		when().
		get("https://restapi.wcaquino.me/ola").
		then().//-> dentro do then eu posso ter quantas verifica��es eu quiser
		statusCode(200)
		.body(is("Ola Mundo!"))
		.body(containsString("Mundo"))
		.body(is(not(nullValue())));
		
		
		
	}

}
