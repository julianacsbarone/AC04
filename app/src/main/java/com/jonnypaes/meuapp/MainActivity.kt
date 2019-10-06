package com.jonnypaes.meuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

//class MainActivity : AppCompatActivity() {
class MainActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener { onClickBotao() }
    }

    fun onClickBotao(){
        val texto_usuario = loginId.text.toString()
        val texto_senha = passId.text.toString()

        if (texto_usuario.isBlank() ) {
            Toast.makeText(this,"Adicione um nome", Toast.LENGTH_SHORT).show()
        }

        if (texto_usuario == "aluno" && texto_senha == "impacta" ) {
            Toast.makeText(this,"Seja bem-vindo, ${texto_usuario.capitalize()}", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, TelaInicial::class.java)

            intent.putExtra("nome_usuario", texto_usuario)
            startActivity(intent)
        } else {
            Toast.makeText(this,"Usuário ou Senha incorretos", Toast.LENGTH_SHORT).show()
        }
    }
}
