package br.com.me.san.visitcard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.me.san.visitcard.data.BusinessCard
import br.com.me.san.visitcard.data.BussinessCardRepository

class MainViewModel(private val bussinessCardRepository: BussinessCardRepository) : ViewModel() {

    fun insert(businessCard: BusinessCard) {
        bussinessCardRepository.insert(businessCard)
    }

    fun getAll(): LiveData<List<BusinessCard>> {
        return bussinessCardRepository.getAll()
    }

}

class MainViewModelFactory(private val repository: BussinessCardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}