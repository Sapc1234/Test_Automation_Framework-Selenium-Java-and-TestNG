package utils;

import java.util.Objects;

import RsaECommerece_Page.OrderPage;

import RsaECommerece_Page.ConfirmTheOrderforTheProduct;
import RsaECommerece_Page.ErrorValidation;

import RSA_Application_MainPage.Login;
import RSA_Application_MainPage.SearchProduct;
import RsaECommerece_Page.EcommAppLogin;



public class ObjectClass

{
	// The Singleton Pattern is a design pattern that ensures only one instance of a
	// class is created and provides a global access point to that instance.
	// It is commonly used for managing shared resources, configuration settings,
	// logging, or database connections in an application.

	private static CommonActions commonActions;
	private static Reports reports;
	private static Login login;
	private static SearchProduct searchProduct; // Single instance

	// Ecomm
	private static EcommAppLogin ecommApplogin;
	private static ConfirmTheOrderforTheProduct confirmTheOrderForTheProduct;
	private static ErrorValidation errorValidation;
	private static OrderPage orderPage;

	// Admin

	
	// private static AdminSoftwareManagement adminSoftwareManagement;

	private ObjectClass() // Private constructor

	{

	}

	public static CommonActions getCommonActions()

	{
		if (Objects.isNull(commonActions))

		{
			commonActions = new CommonActions();
		}
		return commonActions;
	}

	public static Login getLogin()

	{
		if (login == null)

		{
			login = new Login();// Instantiating only once

		}
		return login;
	}

	public static SearchProduct getSearchProducts()

	{

		if (searchProduct == null)

		{
			searchProduct = new SearchProduct();// Instantiating only once

		}

		return searchProduct;
	}

	public static Reports getReports()

	{
		if (reports == null)

		{
			reports = new Reports();
		}
		return reports;
	}

	
	public static EcommAppLogin getEcommAppLogin()

	{
		if (ecommApplogin == null)

		{
			ecommApplogin = new EcommAppLogin();// Instantiating only once

		}
		return ecommApplogin;
	}

	public static ConfirmTheOrderforTheProduct getConfirmTheOrderForTheProduct()

	{
		if (Objects.isNull(confirmTheOrderForTheProduct))

		{
			confirmTheOrderForTheProduct = new ConfirmTheOrderforTheProduct();
		}
		return confirmTheOrderForTheProduct;
	}

	public static ErrorValidation getErrorValidation()

	{
		if (errorValidation == null)

		{
			errorValidation = new ErrorValidation();// Instantiating only once
		}

		return errorValidation;
	}

	public static OrderPage getOrderPage()

	{
		if (orderPage == null)

		{
			orderPage = new OrderPage();
		}

		return orderPage;
	}

}