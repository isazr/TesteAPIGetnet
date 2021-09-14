package pages;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import response.Esperado;

public class RegisterRequisicoes {
	Esperado responseEsperado = new Esperado();
	String url = "https://reqres.in/api/register";

	@Test
	public void sucessoRegistroUsuario() {
		RestAssured.baseURI = url;

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject parametro = new JSONObject();
		parametro.put("email", "eve.holt@reqres.in");
		parametro.put("password", "pistol");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(parametro.toJSONString());
		
		Response response = httpRequest.request(Method.POST);
		String responseBody = response.getBody().asString();
		System.out.println("CT_16: Realizar registro de usu�rio");
		System.out.println("O ResponseBody �: " + responseBody);
		JsonPath info = response.jsonPath();
		String token = info.get("token");
		Assert.assertEquals(token.toString(), "QpwL5tke4Pnpja7X4");	
		int id = info.get("id");
		Assert.assertEquals(id, 4);	
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("O statusCode � " + statusCode + "\n");

	}
	
	@Test
	public void insucessoRegistroUsuario() {
		RestAssured.baseURI = url;

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject parametro = new JSONObject();
		parametro.put("email", "sydney@fife");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(parametro.toJSONString());
		
		Response response = httpRequest.request(Method.POST);
		String responseBody = response.getBody().asString();
		System.out.println("CT_14: Realizar registro de usu�rio sem informar a senha");
		System.out.println("O ResponseBody �: " + responseBody);
		JsonPath info = response.jsonPath();
		String error = info.get("error");
		Assert.assertEquals(error.toString(), "Missing password");	

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);
		System.out.println("O statusCode � " + statusCode + "\n");
}
	
	@Test
	public void registroUsuarioEmailIncorreto() {
		RestAssured.baseURI = url;

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject parametro = new JSONObject();
		parametro.put("email", "123@");
		parametro.put("password", "pistol");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(parametro.toJSONString());
		
		Response response = httpRequest.request(Method.POST);
		System.out.println("CT_15: Realizar login informando email incorreto");
		String responseBody = response.getBody().asString();
		System.out.println("O ResponseBody �: " + responseBody);
		JsonPath info = response.jsonPath();
		String error = info.get("error");
		Assert.assertEquals(error.toString(), "Note: Only defined users succeed registration");	
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);
		System.out.println("O statusCode � " + statusCode + "\n");


	}
}
