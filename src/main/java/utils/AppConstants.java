package utils;

public enum AppConstants

{
	//RahulSHetty Academy
	RSAQA_URL("https://sso.teachable.com/secure/9521/identity/login/password?force=true"),

	RSAUAT_URL("https://sso.teachable.com/secure/9521/identity/login/password?force=true"),

	RSAINT_URL("https://sso.teachable.com/secure/9521/identity/login/password?force=true"),

	RSAQA_USERNAME("sharanabasappapadashetty047@gmail.com"),

	RSAUAT_USERNAME("sharanabasappapadashetty047@gmail.com"),

	RSAINT_USERNAME("sharanabasappapadashetty047@gmail.com"),

	RSAQA_PASSWORD("Prime2025"),

	RSAUAT_PASSWORD("Prime2025"),

	RSAINT_PASSWORD("Prime2025"),

	// ECommerce

	ECOMQA_URL("https://rahulshettyacademy.com/client"),

	ECOMUAT_URL("https://rahulshettyacademy.com/client"),

	ECOMINT_URL("https://rahulshettyacademy.com/client"),

	ECOMQA_UserName("sharanabasappapadashetty047@gmail.com"),

	ECOMUAT_UserName("sharanabasappapadashetty047@gmail.com"),

	ECOMINT_UserName("sharanabasappapadashetty047@gmail.com"),

	ECOMQA_Password("Prime2025"),

	ECOMUAT_Password("Prime2025"),

	ECOMINT_Password("Prime2025"),

	QA_HOST("api.welbiltdigitalqa.com"),

	INT_HOST("api.welbiltdigitalintegration.com");

	private String value;

	private AppConstants(String value)

	{
		this.value = value;
	}

	public String getValue()

	{
		return value;
	}

	public enum httpMethods

	{
		POST,

		PUT,

		GET,

		DELETE;
	}

	public enum status

	{
		PASS,

		FAIL,

		INFO,

		ERROR;
	}

}
