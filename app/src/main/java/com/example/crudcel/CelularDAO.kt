package com.example.crudcel

import androidx.lifecycle.LiveData
import androidx.room.*

//operações crud vão ser definidas aqui

@Dao
interface CelularDAO {

    @Insert
    fun insert(celular: Celular)

    @Update
    fun update(celular: Celular)

    @Delete
    fun delete(celular: Celular)



    //vem da biblioteca lifecycle, que observa alterações de dados
    //significa que vai atualizar o componenete no qual vai estar ativo no estado lifecycle
    @Query("SELECT * FROM celular_tb ORDER BY modelo_cl ASC")
    fun getAll(): LiveData<List<Celular>>

    @Query("SELECT * FROM celular_tb WHERE id = :id_")
    fun getByID(id_: Int): LiveData<Celular>

    @Query("DELETE FROM celular_tb")
    fun deleteAll()


}