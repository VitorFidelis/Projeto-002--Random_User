package br.com.finasdev.randomuser;

import retrofit2.Call;
import retrofit2.http.GET;

//declarando uma interface chamada RandomuserServiceRequest
public interface RandomuserServiceRequest {

    /*define uma constante chamada Base_URL
    que contém a URL base da API que será usada para fazer as chamadas.*/
    public static  final String Base_URL = "https://randomuser.me/api/";


    /* Este método é uma declaração de método que usa a biblioteca Retrofit
    para fazer uma chamada GET à URL definida. */
    @GET("?results")
    Call<ReturnedDataStructure> obterUsuarioAleatorio();
}
