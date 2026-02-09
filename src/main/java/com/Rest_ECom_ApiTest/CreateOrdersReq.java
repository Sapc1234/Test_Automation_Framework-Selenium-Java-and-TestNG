package com.Rest_ECom_ApiTest;

import java.util.List;

public class CreateOrdersReq

{
	private List<CreateOrderDetailsReq> orders;

	public List<CreateOrderDetailsReq> getOrders() 
	
	{
		return orders;
	}

	public void setOrders(List<CreateOrderDetailsReq> orders)
	
	{
		this.orders = orders;
	}
}
