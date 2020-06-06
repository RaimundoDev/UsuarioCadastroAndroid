package com.example.usuariocadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaUsuario extends AppCompatActivity {

    ListView listaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario);

        // 1)  Iniciando os elementos da tela
        listaUsuario = (ListView) findViewById(R.id.ListaUsuario);

        // 2) Lista para receber os valores
        ArrayList<String> listaDoBanco = new ArrayList<String>();

        // 3) Iniciando Banco de dados
        SQLiteDatabase bcoDados01 = openOrCreateDatabase("ETEC",MODE_PRIVATE,null);

        // 4) Criar cursor para receber os dados do select
        Cursor resultado = bcoDados01.rawQuery("SELECT * FROM Usuario",null);

        // 5) Percorrer o cursor para inserir os dados no ArrayList
        while(resultado.moveToNext()){
            listaDoBanco.add(resultado.getString(1));

        }

        // 6) Adaptar o ArrayList
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(ListaUsuario.this,
                android.R.layout.simple_list_item_1,listaDoBanco);
        listaUsuario.setAdapter(adaptador);
    }
}
