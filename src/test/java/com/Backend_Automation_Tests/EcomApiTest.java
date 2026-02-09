package com.Backend_Automation_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Rest_ECom_ApiTest.CreateOrderDetailsReq;
import com.Rest_ECom_ApiTest.CreateOrderResponse;
import com.Rest_ECom_ApiTest.CreateOrdersReq;
import com.Rest_ECom_ApiTest.LoginRequest;
import com.Rest_ECom_ApiTest.LoginResponse;
import com.Rest_ECom_ApiTest.ViewOrdersMainPage;
import com.Utils.API_ReuseableMethods;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EcomApiTest

{
	@Test

	public void eCommerceApiTest()

	{
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		LoginRequest lr = new LoginRequest();
		lr.setUserEmail("sharanabasappapadashetty047@gmail.com");
		lr.setUserPassword("Prime2025");

		RequestSpecification reqLogin = given().relaxedHTTPSValidation().spec(req).body(lr);
		LoginResponse logRes = reqLogin.when().post("/api/ecom/auth/login").then().log().all().statusCode(200).extract()
				.response().as(LoginResponse.class);
		String token = logRes.getToken();
		System.out.println(token);
		String userId = logRes.getUserId();
		System.out.println(userId);

		// ***************************//
		System.out.println("****Add Product****");
		RequestSpecification addProdReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();

		RequestSpecification reqAddProduct = given().spec(addProdReq).param("productName", "Google pixel  Mobile")
				.param("productAddedBy", userId).param("productCategory", "Learning")
				.param("productSubCategory", "Gadgets").param("productPrice", "15200").param("productDescription", "hp")
				.param("productFor", "Both Men and Women").multiPart("productImage",
						new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Mobile Image.jpg"));

		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all()
				.extract().response().asString();

		JsonPath jp = API_ReuseableMethods.rawToJson(addProductResponse);
		String prod_Id = jp.get("productId");
		System.out.println(prod_Id);

		// *********************//
		System.out.println("****Create Order****");
		RequestSpecification createOrderBAseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();

		CreateOrderDetailsReq orderDetail = new CreateOrderDetailsReq();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(prod_Id);

		List<CreateOrderDetailsReq> ordeDetailList = new ArrayList<CreateOrderDetailsReq>();
		ordeDetailList.add(orderDetail);

		CreateOrdersReq orders = new CreateOrdersReq();
		orders.setOrders(ordeDetailList);

		RequestSpecification createOrderReq = given().log().all().spec(createOrderBAseReq).body(orders);

		/*
		 * String resCreteOrder =
		 * createOrderReq.when().post("/api/ecom/order/create-order").then().log().all()
		 * .extract().response().asString(); System.out.println(resCreteOrder); JsonPath
		 * js1 = ReuseableMethods.rawToJson(resCreteOrder); String OrderId =
		 * js1.get("orders[0]"); System.out.println(OrderId);
		 */

		CreateOrderResponse resCreteOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log()
				.all().extract().response().as(CreateOrderResponse.class);
		String OrderId = resCreteOrder.getOrders().get(0);
		System.out.println(OrderId);
		String prodOrderId = resCreteOrder.getProductOrderId().get(0);
		System.out.println(prodOrderId);
		System.out.println(resCreteOrder.getMessage());

		// ***************//
		System.out.println("****View OrderDetails Page****");

		RequestSpecification viewOrderDetailsReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();

		RequestSpecification vod = given().log().all().spec(viewOrderDetailsReq).queryParam("id", OrderId);

		/*
		 * String ordeDetailsPage =
		 * vod.when().get("/api/ecom/order/get-orders-details/").then().log().all().
		 * extract() .response().asString(); JsonPath jp1 =
		 * ReuseableMethods.rawToJson(ordeDetailsPage);
		 * System.out.println(jp1.getString("message"));
		 * System.out.println(jp1.getString("data.country"));
		 */

		ViewOrdersMainPage odp = vod.when().get("/api/ecom/order/get-orders-details").then().log().all().extract()
				.response().as(ViewOrdersMainPage.class);

		System.out.println(odp.getMessage());
		System.out.println(odp.getData().get__v());
		System.out.println(odp.getData().get_id());
		System.out.println(odp.getData().getCountry());
		System.out.println(odp.getData().getOrderBy());
		System.out.println(odp.getData().getProductDescription());
		System.out.println(odp.getData().getProductName());
		System.out.println(odp.getData().getProductImage());

		System.out.println("****Delete The Product****");
		RequestSpecification deleteProdBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();

		RequestSpecification deletProdReq = given().log().all().spec(deleteProdBaseReq).pathParam("productId", prod_Id);

		String deleteProdResponse = deletProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then()
				.log().all().extract().response().asString();

		JsonPath js = API_ReuseableMethods.rawToJson(deleteProdResponse);
		String expectedMsg = js.get("message");
		String actualMsg = "Product Deleted Successfully";
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg, expectedMsg);

	}
}
