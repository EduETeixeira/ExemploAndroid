package com.example.crudcel

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.system.Os.remove
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

import java.lang.Exception

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_lista.*


@Suppress("DEPRECATION")




class ListaCelularesActivity : AppCompatActivity(){

    // variável imutavel para enviar o código de request do cadastro
    val REQUEST_CODE = 12




    // objeto da view model que será instanciado posteriormente
    private lateinit var celularViewModel: CelularViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adaptador: CelularAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adaptador = CelularAdapter(this)

        recyclerView = recycler_celulares

        montaLista(recyclerView)

        fbNovo.setOnClickListener {
            val intet = Intent(this, MainActivity::class.java)

            // abre a activity, porém aguarda o resultado com o objeto par salvar
            startActivityForResult(intet, REQUEST_CODE)
        }



        //comeca aqui o desliza pra apagar entrada

        // Add the functionality to swipe items in the
        // recycler view to delete that item
        val helper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    val position = viewHolder.adapterPosition
                    val celular = adaptador.getCelularAtPosition(position)


                    // Delete the word
                    celularViewModel.delete(celular)
                }
            })

        helper.attachToRecyclerView(recyclerView)



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // caso o código enviado e o código recebido sejam os esperados, verificar o dado recebido
        if(requestCode == REQUEST_CODE &&
            resultCode == Activity.RESULT_OK){
            // caso o valor recebido seja diferente de nulo, executa o bloco de ação
            data?.let { resultado ->
                try{

                    // recebe o objeto da intent, faz o cast para o objeto Fornecedor
                    val celular: Celular = resultado.extras?.
                        get(MainActivity.EXTRA_REPLY) as Celular

                    // caso o objeto seja diferente de vazio, envia para o viewmodel
                    celular.let {
                        celularViewModel.insert(celular)
                    }
                } catch (e: Exception){
                    Log.d("TAG: ", e.message)
                }
            }
        }

    }




    private fun montaLista(recyclerView: RecyclerView){

        val adapter = CelularAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        celularViewModel = ViewModelProviders.of(this).
            get(CelularViewModel::class.java)


        celularViewModel.celulares.observe(this,
            Observer { celularLista ->
                celularLista?.let { lista ->
                    adapter.setFornecedorLista(lista)
                }
            })
    }

    override fun onRestart() {
        super.onRestart()

        montaLista(recyclerView)

    }


}