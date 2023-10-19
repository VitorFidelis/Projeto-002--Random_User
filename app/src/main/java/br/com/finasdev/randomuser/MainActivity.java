package br.com.finasdev.randomuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/*
* Importa a biblioteca Glide, que é uma biblioteca amplamente usada no desenvolvimento Android
* para carregar e exibir imagens de forma eficiente.
* */
import com.bumptech.glide.Glide;

/*
* Importa classes e interfaces relacionadas à biblioteca Retrofit 2,
* que é usada para fazer chamadas de API HTTP em aplicativos Android
* */
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Declaração de componentes de UI
    private TextView txtNome,txtSobrenome,txtEmail,txtIdade;
    private ImageView imgvUser;
    private Button btnObterUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       /*
       * associação de elementos da interface do usuário (views)
       * com as variáveis declaradas anteriormente na classe MainActivity.
       * */
        txtNome = findViewById(R.id.txtNome);
        txtSobrenome = findViewById(R.id.txtSobrenome);
        txtEmail = findViewById(R.id.txtEmail);
        imgvUser = findViewById(R.id.imgvUser);
        txtIdade = findViewById(R.id.txtIdade);
        btnObterUsuario = findViewById(R.id.btnObterUsuario);


        /*
        * essa parte do código configura o Retrofit com a URL base e o conversor apropriado (Gson)
        * para lidar com solicitações e respostas da API.
        * Essa instância do Retrofit será usada mais tarde para realizar chamadas de API HTTP
        * para obter dados de usuário aleatório da URL base fornecida.
        * */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RandomuserServiceRequest.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        /*
        * Essa parte do código cria uma implementação da interface RandomuserServiceRequest
        * usando a instância do Retrofit que foi configurada anteriormente.
        * */
        RandomuserServiceRequest service = retrofit.create(RandomuserServiceRequest.class);


        //ouvinte de clique para esse botão
        btnObterUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                * cria uma chamada (Call) que representa uma solicitação
                * para obter um usuário aleatório da API
                * */
                Call<ReturnedDataStructure> call = service.obterUsuarioAleatorio();

                /*
                *  inicia a execução assíncrona da chamada de API representada pela instância call.
                * Ela define um callback para lidar com a resposta da solicitação e possíveis erros
                * */
                call.enqueue(new Callback<ReturnedDataStructure>() {

                    @Override
                    public void onResponse(Call<ReturnedDataStructure> call, Response<ReturnedDataStructure> response) {
                        /*
                        * Esta linha verifica se a solicitação da API não foi bem-sucedida,
                        * com base no código de status HTTP da resposta.
                        * */
                        if (!response.isSuccessful()) {
                            /*
                            * registrando uma mensagem de erro no log,
                            * que inclui o código de status HTTP da resposta.
                            * */
                            Log.i("TAG", "Erro" + response.code());

                        } else {
                            /*
                            * atualiza os elementos da interface do usuário
                            * com as informações obtidas:
                            * */
                            ReturnedDataStructure dataStructure = response.body();
                            String firstName = dataStructure.getResults().get(0).getName().getFirst();
                            String lastName = dataStructure.getResults().get(0).getName().getLast();
                            String email = dataStructure.getResults().get(0).getEmail();
                            String pictureUrl = dataStructure.getResults().get(0).getPicture().getLarge();
                            int age = dataStructure.getResults().get(0).getDob().getAge();
                            txtNome.setText("Nome: " + firstName);
                            txtSobrenome.setText("Sobrenome: " + lastName);
                            txtEmail.setText("Email: " + email);
                            Glide.with(MainActivity.this).load(pictureUrl).into(imgvUser);
                            txtIdade.setText("Idade: " + age);
                        }
                    }
                    /*
                    * Este trecho de código lida com falhas na solicitação da API
                    * dentro do método onFailure.
                    * */
                    @Override
                    public void onFailure(Call<ReturnedDataStructure> call, Throwable t) {

                        Log.e("TAG", "Erro na chamada de rede: " + t.getMessage());
                    }
                });
            }
        });
    }
}
