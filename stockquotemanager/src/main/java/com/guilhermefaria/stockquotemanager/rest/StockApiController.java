package com.guilhermefaria.stockquotemanager.rest;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.ResponseBody;
import retrofit2.Call;

@Service
public class StockApiController {

	private final StockApiInterface.IStockApiInterface serviceApi;

	public StockApiController() {
		// TODO Auto-generated constructor stub
		serviceApi = StockApiInterface.getApiClient();
	}

	public boolean containStock(String id) {
		boolean flag = false;

		Call<ResponseBody> call = serviceApi.readStock();

		try {
			ResponseBody response = call.execute().body();
			String json = response.string();

			List<Stock> stock = new ArrayList<>();

			Gson gson = new Gson();

			Type listType = new TypeToken<ArrayList<Stock>>() {
			}.getType();
			List<Stock> list = new Gson().fromJson(json, listType);

			flag = list.stream().anyMatch(h -> h.getId().equals(id));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;

	}

	public void notification() {

		Call<ResponseBody> call = serviceApi.notification(new Identification("localhost", "8081"));
		System.out.println("notification");
		try {
			ResponseBody response = call.execute().body();
			System.out.println(response.string());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
