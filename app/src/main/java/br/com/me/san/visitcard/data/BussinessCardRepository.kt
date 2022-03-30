package br.com.me.san.visitcard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class BussinessCardRepository (private val dao: BussinessCardDAO) {
    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(businessCard)
        }
    }

    fun getAll() = dao.getAll()
}