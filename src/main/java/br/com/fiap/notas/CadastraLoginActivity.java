package br.com.fiap.notas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;

import br.com.fiap.notas.util.ArquivoDB;

public class CadastraLoginActivity extends AppCompatActivity {

    private EditText etNome, etSobrenome, etNascimento, etEmail, etSenha;
    private RadioGroup rgSexo;
    private ArquivoDB arquivoDB;
    private HashMap<String, String> mapDados;

    private final String ARQ = "dados.txt";
    private final String SP = "dados";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_login);

        etNome = (EditText) findViewById(R.id.edtNome);
        etSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        etNascimento = (EditText) findViewById(R.id.edtNascimento);
        etEmail = (EditText) findViewById(R.id.edtEmail);
        etSenha = (EditText) findViewById(R.id.edtSenha);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);

        arquivoDB = new ArquivoDB();
        mapDados = new HashMap<>();
    }

    private boolean capturaDados(){
        String nome, sobrenome, nascimento, email, senha, sexo;
        boolean dadosOK = false;

        nome = etNome.getText().toString();
        sobrenome = etSobrenome.getText().toString();
        nascimento = etNascimento.getText().toString();
        email = etEmail.getText().toString();
        senha = etSenha.getText().toString();

        //getCheckedRadioButtonId retorna o id do RadioButon selecionado dentro
        // do RadioGroup rgSexo
        int sexoId = rgSexo.getCheckedRadioButtonId();
        RadioButton rbSexo = (RadioButton) findViewById(sexoId);

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                !TextUtils.isEmpty(nome) &&
                !TextUtils.isEmpty(sobrenome) &&
                !TextUtils.isEmpty(nascimento) &&
                !TextUtils.isEmpty(email) &&
                !TextUtils.isEmpty(senha) &&
                (sexoId != -1)){
            dadosOK = true;
            sexo = rbSexo.getText().toString();
            mapDados.put("usuario", email);
            mapDados.put("senha", senha);
            mapDados.put("nome", nome);
            mapDados.put("sobrenome", sobrenome);
            mapDados.put("nascimento", nascimento);
            mapDados.put("sexo", sexo);
        }else{
            Toast.makeText(this, R.string.dados_nok, Toast.LENGTH_SHORT).show();
        }
        return dadosOK;
    }


}
