package br.com.teamofsix.scepe

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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

            val intent = Intent(this, TelaInicialActivity::class.java)

            intent.putExtra("nome_usuario", texto_usuario)
            startActivity(intent)
        } else {
            Toast.makeText(this,"Usuário ou Senha incorretos", Toast.LENGTH_SHORT).show()
        }
    }
}

