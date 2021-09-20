package pages;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import response.Esperado;

public class UsuariosRequisicoes {
	Esperado responseEsperado = new Esperado();
	String url = "https://reqres.in/api/users";
	
	@BeforeMethod
	public void acessoAPI(){
		System.out.println("Acessando a API ");
		RestAssured.baseURI = url;
	}

	@Test
	public void validarListaUsuarios() {

		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "?page=2");

		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody, responseEsperado.getResponseBodyList());
		System.out.println("CT_06: Validar lista de usuários");
		System.out.println("A lista de usuários cadastrados é: " + responseBody);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("O statusCode é " + statusCode + "\n");

	}
	
	@Test
	public void buscarUsuarioID() {
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "2");

		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody, responseEsperado.getResponseBodyID());
		System.out.println("CT_04: Buscar usuário ID existente");
		System.out.println("O usuário encontrado é: " + responseBody);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("O statusCode é " + statusCode + "\n");

	}
	
	@Test
	public void buscarUsuarioInexistente() {
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "23");
		
		System.out.println("CT_01: Buscar usuário de ID inexistente");
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody, responseEsperado.getResponseUsuarioInexiste());
		System.out.println("Usuário inexistente");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 404);
		System.out.println("O statusCode é " + statusCode + "\n");

	}

	@Test
	public void criarUsuario() {
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject parametro = new JSONObject();
		parametro.put("name", "morpheus");
		parametro.put("job", "leader");

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(parametro.toJSONString());

		Response response = httpRequest.request(Method.POST);
		String responseBody = response.getBody().asString();
		System.out.println("CT_08: Criar usuário");
		System.out.println("O usuário criado é: " + responseBody);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
		System.out.println("O statusCode é " + statusCode + "\n");

	}

	@Test
	public void updateInfoUsuarioCompleto() {
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject updateInfo = new JSONObject();
		updateInfo.put("name", "morpheus");
		updateInfo.put("job", "zion resident");

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(updateInfo.toJSONString());
		Response response = httpRequest.request(Method.PUT, "4");
		String responseBody = response.getBody().asString();

		JsonPath novaInfo = response.jsonPath();
		String name = novaInfo.get("name");
		Assert.assertEquals(name, "morpheus");
		String job = novaInfo.get("job");
		Assert.assertEquals(job, "zion resident");
		System.out.println("CT_03: Update de Informação do usuário Completo");
		System.out.println("Dados atualizados: " + responseBody);
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("O statusCode é " + statusCode + "\n");

	}

	@Test
	public void updateInfoUsuario() {
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject updateInfo = new JSONObject();
		updateInfo.put("name", "morpheus");
		updateInfo.put("job", "zion resident");

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(updateInfo.toJSONString());
		Response response = httpRequest.request(Method.PATCH, "4");
		System.out.println("CT_07: Update de Informação do usuário");
		String responseBody = response.getBody().asString();

		JsonPath novaInfo = response.jsonPath();
		String name = novaInfo.get("name");
		Assert.assertEquals(name, "morpheus");
		String job = novaInfo.get("job");
		Assert.assertEquals(job, "zion resident");
		System.out.println("Dados atualizados: " + responseBody );
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("O statusCode é " + statusCode + "\n");

	}

	@Test
	public void deletarUsuario() {
		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.DELETE, "4");
		System.out.println("CT_05: Deletar usuário");

		System.out.println("Dados deletados");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 204);
		System.out.println("O statusCode é " + statusCode + "\n");

	}

	@Test
	public void delayedResponse() {

		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "?delay=3");

		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody, responseEsperado.getResponseBodyList2());
		System.out.println("CT_09: Delayed Response para apresentar Page 1 de lista de usuários");

		System.out.println("A lista de usuários é: " + responseBody);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("O statusCode é " + statusCode + "\n");
	}

	@Test
	public void deletarUsuarioIDInexistente() {
		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.DELETE, "56484");
		System.out.println("CT_02: Deletar usuário inexistente");

		System.out.println("Dados deletados");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 204);
		System.out.println("O statusCode é " + statusCode + "\n");
		

	}

}
