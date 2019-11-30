package com.example.crudcelular.Repository

import androidx.lifecycle.LiveData
import com.example.crudcel.Celular
import com.example.crudcel.CelularDAO


//esta classe é onde checa se precisa trazer os dados de uma api local ou um banco de dados local
//serve para trazer a logica do banco de dados para esta classe


class CelularRepository(private val celularDAO: CelularDAO) {

    val allCelulares: LiveData<List<Celular>> = celularDAO.getAll()

    fun insert(celular: Celular){
        celularDAO.insert(celular)
    }

    fun update(celular: Celular){
        celularDAO.update(celular)
    }

    fun delete(celular: Celular){
        celularDAO.delete(celular)
    }

}