package com.Utils;

public class API_Payloads

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
		String cb = "{\r\n" + "    \"fields\": {\r\n" + "       \"project\":\r\n" + "       {\r\n"
				+ "          \"key\": \"SAP\"\r\n" + "       },\r\n"
				+ "       \"summary\": \"All websites links are not working\",\r\n" + "       \"issuetype\":\r\n"
				+ "        {\r\n" + "          \"name\": \"Bug\"\r\n" + "        }\r\n" + "   }\r\n" + "}\r\n" + "\r\n"
				+ "";

		return cb;
	}

	public static String gQuery(int charId, int episode_Id)

	{
		String gb = "{\"query\":\"query($characterId:Int!,$episodeId :Int!)\\n\\n{\\n  character(characterId: $characterId) \\n  \\n  {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  \\n  location(locationId:16342)\\n  \\n  {\\n    name\\n    id\\n    type\\n    dimension\\n  }\\n  \\n  episode(episodeId:$episodeId)\\n  \\n  {\\n    name\\n    air_date\\n    episode\\n  }\\n  \\n  characters(filters:{name:\\\"Virat Kohli\\\"})\\n  \\n  {\\n   info\\n    \\n    {\\n      count\\n      pages\\n    }\\n    \\n    result\\n    \\n    {\\n      id\\n      type\\n    }\\n    \\n   \\n  }\\n  \\n  episodes(filters:{episode:\\\"hulu\\\"})\\n  \\n  {\\n    info\\n    \\n    {\\n      count\\n      pages\\n      next\\n    }\\n    \\n    result\\n    \\n    {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":"
				+ charId + ",\"episodeId\":" + episode_Id + "}}";
		return gb;
	}

	public static String gMutation(String typpe,String dia)

	{
		String gm = "{\"query\":\"mutation($locationNmae:String!,$characterName:String!,$episodeName:String!)\\n\\n{\\n  createLocation(location:{name:$locationNmae,type:\\\""+typpe+"\\\",dimension:\\\""+dia+"\\\"},)\\n\\n  {\\n    id\\n  }\\n  \\n  createCharacter(character:{name:$characterName,type:\\\"Macho\\\",status:\\\"dead\\\",species:\\\"fantasy\\\",gender:\\\"Male\\\",image:\\\"png\\\",originId:26345,locationId:26345})\\n  \\n  {\\n    id\\n  }\\n  \\n  createEpisode(episode:{name:$episodeName,air_date:\\\"1950 June\\\",episode:\\\"Prime\\\"})\\n  \\n  {\\n    id\\n  }\\n  \\n  deleteLocations(locationIds:[26352,26350])\\n  \\n  {\\n    locationsDeleted\\n  }\\n}\",\"variables\":{\"locationNmae\":\"RSA\",\"characterName\":\"Sapc\",\"episodeName\":\"Panchayat\"}}";
		return gm;
	}

}
