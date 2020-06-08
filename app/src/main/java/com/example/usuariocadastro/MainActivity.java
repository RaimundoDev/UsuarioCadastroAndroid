package com.example.usuariocadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCadastrar;
    Button btnAtualizar;
    Button btnDeletar;
    Button btnSelecionar;
    Button btnConsultar;

    EditText txtNome;
    EditText txtCPF;
    EditText txtEmpresa;
    EditText txtFuncao;

    SQLiteDatabase bcoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Criar o bando de dados
        Criar();

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnDeletar = findViewById(R.id.btnDeletar);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnSelecionar = findViewById(R.id.btnSelecionar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inserir();
            }
        });
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alterar();
            }
        });
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deletar();
            }
        });
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objInt = new Intent(MainActivity.this, ListaUsuario.class);
                startActivity(objInt);
            }
        });
        btnSelecionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Selecionar();
            }
        });

    }

    public void Criar()
    {
        String Sql;

        try {
            bcoDados = openOrCreateDatabase("ETEC", MODE_PRIVATE, null);
            Sql = "CREATE TABLE IF NOT EXISTS Usuario (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Nome Text, " +
                    "CPF TEXT, " +
                    "Empresa TEXT, " +
                    "Funcao Text)";

            bcoDados.execSQL(Sql);
            Toast.makeText(this, "Banco carregado com sucesso", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            //TODO: handle exception
            Toast.makeText(this, "Erro ao abrir o banco: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Inserir()
    {
        String Sql;

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        txtNome = findViewById(R.id.txtNome);
        txtCPF = findViewById(R.id.txtCPF);
        txtEmpresa = findViewById(R.id.txtEmpresa);
        txtFuncao =  findViewById(R.id.txtFuncao);

        String usuarioNome = txtNome.getText().toString();
        String usuarioCPF = txtCPF.getText().toString();
        String usuarioEmpresa = txtEmpresa.getText().toString();
        String usuarioFuncao = txtFuncao.getText().toString();

        try {

            bcoDados = openOrCreateDatabase("ETEC", MODE_PRIVATE, null);
            Sql = "INSERT INTO Usuario (Nome, CPF, Empresa, Funcao) VALUES ('"+usuarioNome+"', '"+usuarioCPF+"', '"+usuarioEmpresa+"', '"+usuarioFuncao+"')";

            bcoDados.execSQL(Sql);
            Toast.makeText(this, "Usuário inserido com sucesso", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, "Erro ao inserir usuário " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Selecionar()
    {
        String Sql;

        txtCPF = findViewById(R.id.txtCPF);

        String usuarioCPF = txtCPF.getText().toString();

        try {

            bcoDados = openOrCreateDatabase("ETEC", MODE_PRIVATE, null);
            Sql = "SELECT * FROM Usuario WHERE CPF='"+usuarioCPF+"'";

            Cursor resultado = bcoDados.rawQuery(Sql, null);

            while (resultado.moveToNext())
            {
                Toast.makeText(this, "Usuário: " + resultado.getString(1), Toast.LENGTH_LONG).show();
            }

            // bcoDados.execSQL(Sql);

        } catch (Exception e) {
            Toast.makeText(this, "Erro ao procurar usuário: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Alterar()
    {
        String Sql;

        txtNome = findViewById(R.id.txtNome);
        txtCPF = findViewById(R.id.txtCPF);
        txtEmpresa = findViewById(R.id.txtEmpresa);
        txtFuncao =  findViewById(R.id.txtFuncao);

        String usuarioNome = txtNome.getText().toString();
        String usuarioCPF = txtCPF.getText().toString();
        String usuarioEmpresa = txtEmpresa.getText().toString();
        String usuarioFuncao = txtFuncao.getText().toString();

        try {

            bcoDados = openOrCreateDatabase("ETEC", MODE_PRIVATE, null);
            Sql = "UPDATE Usuario SET Nome='"+usuarioNome+"', Empresa='"+usuarioEmpresa+"', Funcao='"+usuarioFuncao+"' WHERE CPF='"+usuarioCPF+"'";
            bcoDados.execSQL(Sql);
            Toast.makeText(this, "Usuário atualizado com sucesso", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, "Erro ao atualizar usuário: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Deletar()

    {
        String Sql;

        try {

            txtCPF = findViewById(R.id.txtCPF);
            String usuarioCPF = txtCPF.getText().toString();

            bcoDados = openOrCreateDatabase("ETEC", MODE_PRIVATE, null);
            Sql = "DELETE FROM Usuario WHERE CPF='"+usuarioCPF+"'";
            bcoDados.execSQL(Sql);
            Toast.makeText(this, "Usuário deletado com sucesso", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, "Erro ao deletar o usuário: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
