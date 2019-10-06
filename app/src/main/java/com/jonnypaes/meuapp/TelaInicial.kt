package com.jonnypaes.meuapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*
import com.google.android.material.navigation.NavigationView

class TelaInicial  : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        val params = intent.extras
        val nome = params?.getString("nome_usuario")
        val n = params?.getInt("numero")
//        Toast.makeText(this,"Bem vindo $nome", Toast.LENGTH_SHORT)


        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Inicio"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        // configuração no menu lateral
        configuraMenuLateral()
    }

    private fun configuraMenuLateral() {
// ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()
        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_buscar){
            Toast.makeText(this, "Clicou em buscar",Toast.LENGTH_SHORT).show()
        } else if (id == R.id.action_adicionar) {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(this, "Clicou em Atualizar",Toast.LENGTH_SHORT).show()
        } else if (id == R.id.action_config) {
            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        } else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_diciplinas -> {
                Toast.makeText(this, "Clicou Disciplinas", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_mensagens -> {
                Toast.makeText(this, "Clicou Mensagens", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_forum -> {
                Toast.makeText(this, "Clicou Forum", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_localizacao -> {
                Toast.makeText(this, "Clicou Localização", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Config", Toast.LENGTH_SHORT).show()
            }
        }
// fecha menu depois de tratar o evento
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

}
