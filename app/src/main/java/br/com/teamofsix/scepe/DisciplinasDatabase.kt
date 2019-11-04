package br.com.teamofsix.scepe

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

class DisciplinasDatabase {
    // anotação define a lista de entidades e a versão do banco
    @Database(entities = arrayOf(Disciplina::class), version = 1)
    abstract class LMSDatabase: RoomDatabase() {
        abstract fun disciplinaDAO(): DisciplinaDAO
    }
}