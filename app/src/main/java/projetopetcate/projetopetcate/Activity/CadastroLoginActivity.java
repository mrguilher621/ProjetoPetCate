package projetopetcate.projetopetcate.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import projetopetcate.projetopetcate.DAO.ConfigFireBase;
import projetopetcate.projetopetcate.Entidades.Usuarios;
import projetopetcate.projetopetcate.Helper.Base64Custom;
import projetopetcate.projetopetcate.Helper.Preferencias;
import projetopetcate.projetopetcate.R;

public class CadastroLoginActivity extends AppCompatActivity {

    private EditText edtCadEmail;
    private EditText edtCadSenha;
    private EditText edtCadConfirmarSenha;
    private Button btnGravar;
    private Usuarios usuarios;
    private FirebaseAuth autenticaçao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);

        edtCadEmail = (EditText) findViewById(R.id.edtcadEmail);
        edtCadSenha = (EditText) findViewById(R.id.edtcadSenha);
        edtCadConfirmarSenha = (EditText) findViewById(R.id.edtcadConfirmarSenha);
        btnGravar = (Button) findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtCadSenha.getText().toString().equals(edtCadConfirmarSenha.getText().toString())){

                    usuarios = new Usuarios();
                    usuarios.setEmail(edtCadEmail.getText().toString());
                    usuarios.setSenha(edtCadSenha.getText().toString());

                    cadastrarUsuario();

                }else {
                    Toast.makeText(CadastroLoginActivity.this, "AS senhas não são correspondentes",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cadastrarUsuario(){

        autenticaçao = ConfigFireBase.getFirebaseAutenticaçao();
        autenticaçao.createUserWithEmailAndPassword(usuarios.getEmail(),usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(CadastroLoginActivity.this, "Usuario cadastrado com sucesso!",Toast.LENGTH_LONG).show();

                    String identificadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setId(identificadorUsuario);
                    usuarios.salvar();

                    Preferencias preferencias = new Preferencias(CadastroLoginActivity.this);
                    preferencias.salvarUsuarioPreferencias(identificadorUsuario,usuarios.getNome());

                    abrirLoginUsuario();
                }else {
                    String erroExcecao = "";

                    try{
                        throw task.getException();
                    }
                    catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digite uma senha mais forte, contendo no minimo 8 caracteres de letras e números";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "E-Mail digitado é invalido, digite um novo e-mail";
                    }
                    catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Este e-mail já está cadastrado no sistema";
                    }
                    catch (Exception e){
                        erroExcecao = "Erro ao efetuar o cadastro";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroLoginActivity.this, "Erro:" + erroExcecao,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroLoginActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
