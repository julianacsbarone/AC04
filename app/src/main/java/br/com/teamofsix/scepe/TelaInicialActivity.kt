package br.com.teamofsix.scepe

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var disciplinas = listOf<Disciplina>()
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        // acessar parametros da intnet
        // intent é um atributo herdado de Activity
        val args:Bundle? = intent.extras
        // recuperar o parâmetro do tipo String

        //val nome = args?.getString("nome")

        // recuperar parâmetro simplificado
        //val numero = intent.getIntExtra("nome",0)

        //Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        //Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()

        // colocar toolbar
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = "Disciplinas"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

            // configurar cardview
            recyclerDisciplinas?.layoutManager = LinearLayoutManager(context)
            recyclerDisciplinas?.itemAnimator = DefaultItemAnimator()
            recyclerDisciplinas?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
         taskDisciplinas()
    }

    fun taskDisciplinas() {

        Thread {
            this.disciplinas = DisciplinaService.getDisciplinas(context)
            runOnUiThread {
                // Código para atualizar a UI com a lista de disciplinas
                recyclerDisciplinas?.adapter = DisciplinaAdapter(this.disciplinas) { onClickDisciplina(it) }
            }
        }.start()

    }

    fun onClickDisciplina(disciplina: Disciplina) {
        Toast.makeText(context, "Clicou disciplina ${disciplina.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, DisciplinaActivity::class.java)
        intent.putExtra("disciplina", disciplina)
        startActivityForResult(intent, REQUEST_REMOVE)
    }

    // configuraçao do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {
       var toogle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
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
                val intent = Intent(this, ConfigActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_deslogar -> {
                finish()
            }
            R.id.nav_sair -> {
                finishAffinity()
            }
        }

         layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_adicionar) {
            val intent = Intent(context, DisciplinaCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        } else if (id == R.id.action_exit) {
            finishAffinity()
        } else if (id == R.id.action_config) {
            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        } else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    // esperar o retorno do cadastro da disciplina
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
            // atualizar lista de disciplinas
            taskDisciplinas()
        }
    }


}
