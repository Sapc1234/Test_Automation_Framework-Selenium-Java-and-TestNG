package files;

public class Payload

{
	public static String addPlace()

	{
		String ap = "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
				+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91)9845610338\",\r\n" + "  \"address\": \"29, side layout,Chennai\",\r\n"
				+ "  \"types\": [\r\n" + "    \"Tidel park\",\r\n" + "    \"shop\"\r\n" + "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n" + "  \"language\": \"English\"\r\n" + "}";

		return ap;
	}

	public static String coursePrice()

	{
		String cp = "{\r\n" + "\r\n" + "\"dashboard\": {\r\n" + "\r\n" + "\"purchaseAmount\": 910,\r\n" + "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n" + "\r\n" + "},\r\n" + "\r\n" + "\"courses\": [\r\n"
				+ "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"Selenium Python\",\r\n" + "\r\n" + "\"price\": 50,\r\n"
				+ "\r\n" + "\"copies\": 6\r\n" + "\r\n" + "},\r\n" + "\r\n" + "{\r\n" + "\r\n"
				+ "\"title\": \"Cypress\",\r\n" + "\r\n" + "\"price\": 40,\r\n" + "\r\n" + "\"copies\": 4\r\n" + "\r\n"
				+ "},\r\n" + "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"RPA\",\r\n" + "\r\n" + "\"price\": 45,\r\n"
				+ "\r\n" + "\"copies\": 10\r\n" + "\r\n" + "}\r\n" + "\r\n" + "]\r\n" + "\r\n" + "}\r\n" + "\r\n" + "";
		return cp;

	}

	public static String add_Book(String isbn, String aisle)

	{
		String ab = "{\r\n" + "\"name\":\"Learn Appium Automation with Java\",\r\n" + "\"isbn\":\"" + isbn + "\",\r\n"
				+ "\"aisle\":\"" + aisle + "\",\r\n" + "\"author\":\"ACPC\"\r\n" + "}\r\n" + "";

		return ab;
	}

	public static String delete_Book(String isbn, String aisle)

	{
		String id = isbn + aisle;
		String db = "{\r\n" + "    \"ID\": \"" + id + "\"\r\n" + "}";

		return db;
	}
	
	public static String createJiraBug()
	
	{
		String cjb = "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Media Items are not working\",\r\n"
				+ "       \"issuetype\":\r\n"
				+ "        {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "   }\r\n"
				+ "}";
		
		return cjb;
	}

}
