package com.example.crudcel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.example.crudcelular.Repository.CelularRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

//vai ajudar a trazer os dados entre a UI e o repository
//Isto sobrevive a configuração de mudança de dados e pega e faz o viewmodel atual reconecatar com
//uma nova instancia do criador


/*
* Why use ViewModel?
 - The ViewModel is lifecycle aware so that it will survive the configuration change. It will outlive the Activity or Fragment.
 - Easier communications between fragments, instead of relying on the hosting Activity passing the communications.
- Works pretty well with LiveData, an observable data holder.
- You can use RxJava instead of LiveData.
*
* */


class CelularViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CelularRepository

    val celulares: LiveData<List<Celular>>


    private val parentJob = Job()
    private val corountineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(corountineContext)

    init {
        val celularDAO = HelperDatabase.getDatabase(application).celularDao()
        repository = CelularRepository(celularDAO)

        celulares = repository.allCelulares
    }

    fun insert(celular: Celular) = scope.launch(Dispatchers.IO){
        repository.insert(celular)
    }

    fun update(celular: Celular) = scope.launch(Dispatchers.IO){
        repository.update(celular)
    }

    fun delete(celular: Celular) = scope.launch(Dispatchers.IO){
        repository.delete(celular)
    }

}