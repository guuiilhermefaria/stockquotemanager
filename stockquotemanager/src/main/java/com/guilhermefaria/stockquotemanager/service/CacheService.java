package com.guilhermefaria.stockquotemanager.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guilhermefaria.stockquotemanager.rest.Stock;
import com.guilhermefaria.stockquotemanager.rest.StockApiInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;

@Service
public class CacheService {

	private final StockApiInterface.IStockApiInterface serviceApi;

	private List<Stock> list;

	public CacheService() {
		list = new ArrayList<Stock>();
		serviceApi = StockApiInterface.getApiClient();
	}

	public List<Stock> getList() {
		return list;
	}

	public void setList(List<Stock> list) {
		this.list = list;
	}

	public boolean containStock(String id) {
		boolean flag = false;
		if (list.isEmpty()) {
			Call<ResponseBody> call = serviceApi.readStock();
			try {
				ResponseBody response = call.execute().body();
				String json = response.string();

				Gson gson = new Gson();
				Type listType = new TypeToken<ArrayList<Stock>>() {
				}.getType();

				List<Stock> listRest = new Gson().fromJson(json, listType);

				this.list.addAll(listRest);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		flag = this.list.stream().anyMatch(h -> h.getId().equals(id));
		return flag;

	}
	
	public void clearCache() {
		list.clear();
	}
}
