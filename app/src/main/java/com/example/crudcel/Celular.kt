package com.example.crudcel


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import org.jetbrains.annotations.NotNull

@Entity(tableName = "celular_tb")
class Celular (

    @NotNull
    @ColumnInfo(name = "modelo_cl")
    var modelo: String = "",

    @NotNull
    @ColumnInfo(name = "marca_cl")
    var marca: String = "",

    @NotNull
    @ColumnInfo(name = "preco_cl")
    var preco: Double = 0.0,

    @NotNull
    @ColumnInfo(name = "espaco_cl")
    var espaco: Int = 0



): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
