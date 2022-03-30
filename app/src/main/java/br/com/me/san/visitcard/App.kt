package br.com.me.san.visitcard

import android.app.Application
import br.com.me.san.visitcard.data.AppDatabase
import br.com.me.san.visitcard.data.BussinessCardRepository

class App: Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BussinessCardRepository(database.businessDao()) }
}