package com.guilhermefaria.stockquotemanager.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class StockApiInterface {
	private static StockApiInterface.IStockApiInterface apiInterface;

	public static StockApiInterface.IStockApiInterface getApiClient() {
		if (apiInterface == null) {
			Retrofit client = Api.getClientApi();
			apiInterface = client.create(StockApiInterface.IStockApiInterface.class);
		}
		return apiInterface;
	}

	public interface IStockApiInterface {

		@GET("/stock")
		public Call<ResponseBody> readStock();

		@POST("/notification")
		public Call<ResponseBody> notification(@Body Identification identification);
	}

}
