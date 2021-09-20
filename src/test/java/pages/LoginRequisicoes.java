package pages;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import response.Esperado;

public class LoginRequisicoes {
	Esperado responseEsperado = new Esperado();
	String url = "https://reqres.in/api/login";
	
	@BeforeMethod
	public void acessoAPI(){
		System.out.println("Acessando a API ");
		RestAssured.baseURI = url;
	}

	@Test
	public void sucessoLogin() {

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject parametro = new JSONObject();
		parametro.put("email", "eve.holt@reqres.in");
		parametro.put("password", "cityslicka");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(parametro.toJSONString());
		
		Response response = httpRequest.request(Method.POST);
		String responseBody = response.getBody().asString();
		System.out.println("CT_10: Realizar Login");
		System.out.println("Login realizado com sucesso!");
		JsonPath info = response.jsonPath();
		String token = info.get("token");
		Assert.assertEquals(token.toString(), "QpwL5tke4Pnpja7X4");	
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("O statusCode é " + statusCode + "\n");

	}
	
	@Test
	public void insucessoLoginSenha() {

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject parametro = new JSONObject();
		parametro.put("email", "peter@klaven");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(parametro.toJSONString());
		
		Response response = httpRequest.request(Method.POST);
		System.out.println("CT_11: Realizar Login sem informar a senha");
		String responseBody = response.getBody().asString();
		System.out.println("O ResponseBody é: " + responseBody);
		JsonPath info = response.jsonPath();
		String error = info.get("error");
		Assert.assertEquals(error.toString(), "Missing password");	
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);
		System.out.println("O statusCode é " + statusCode + "\n");

}
	
	@Test
	public void insucessoLoginUsuario() {

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject parametro = new JSONObject();
		parametro.put("password", "cityslicka");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(parametro.toJSONString());
		
		Response response = httpRequest.request(Method.POST);
		String responseBody = response.getBody().asString();
		System.out.println("CT_12: Realizar Login sem informar o email");
		System.out.println("O ResponseBody é: " + responseBody);
		JsonPath info = response.jsonPath();
		String error = info.get("error");
		Assert.assertEquals(error.toString(), "Missing email or username");	
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);
		System.out.println("O statusCode é " + statusCode + "\n");
}
	
	@Test
	public void loginSenhaIncorreta() {

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject parametro = new JSONObject();
		parametro.put("email", "eve.holt@reqres.in");
		parametro.put("password", "123");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(parametro.toJSONString());
		
		Response response = httpRequest.request(Method.POST);
		String responseBody = response.getBody().asString();
		System.out.println("CT_13: Realizar Login com senha incorreta");
		System.out.println("Login realizado com sucesso, porém a senha está incorreta!");
		JsonPath info = response.jsonPath();
		String token = info.get("token");
		Assert.assertEquals(token.toString(), "QpwL5tke4Pnpja7X4");	
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("O statusCode é " + statusCode + "\n");

	}
	
}


