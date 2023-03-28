package com.pristinebs.searchimageyourimage;

public class ApiUtils {


    private static final String Base_url="https://serpapi.com";
    public static ApiInterface getData(){

        return RetrofitClient.getClient(Base_url).create(ApiInterface.class);
    }

}
