package projetopetcate.projetopetcate.Entidades;

/**
 * Created by guilh on 27/09/2017.
 */

public class Usuarios {

    private String Id;
    private String Email;
    private String Senha;
    private String Nome;
    private String Sobrenome;
    private String CPF;
    private String NomeAnimal;
    private String Endereço;

    public Usuarios() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNomeAnimal() {
        return NomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        NomeAnimal = nomeAnimal;
    }

    public String getEndereço() {
        return Endereço;
    }

    public void setEndereço(String endereço) {
        Endereço = endereço;
    }
}
