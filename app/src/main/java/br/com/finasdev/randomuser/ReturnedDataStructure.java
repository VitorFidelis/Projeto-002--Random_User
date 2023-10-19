package br.com.finasdev.randomuser;

import java.util.List;

/*
* representação dos dados retornados pela chamada de API
* */
public class ReturnedDataStructure {

    //representa uma lista de objetos do tipo Results
    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    //classe interna que representa os dados de um único usuário aleatório.
    public class Results {
        //Um objeto da classe Name que representa o nome do usuário
        private Name name;
        //Uma string que representa o email do usuário
        private String email;
        //Um objeto da classe Picture que contém a URL da imagem do usuário
        private Picture picture;
        //Um objeto da classe Dob que contém a idade do usuário.
        private Dob dob;

        public Name getName() {
            return name;
        }

        public Picture getPicture(){
            return picture;
        }

        public String getEmail() {
            return email;
        }

        public Dob getDob(){
            return dob;
        }
    }

    //representar o nome do usuário e contém os campos first (primeiro nome) e last (sobrenome)
    public class Name {
        private String first;
        private String last;

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }
    }

    //representa a URL da imagem do usuário
    public class Picture{
        private String large;

        public  String getLarge(){
            return large;
        }
    }

    //representa a idade do usuário
    public class Dob{
        private int age;

        public int getAge(){
            return age;
        }
    }
}
