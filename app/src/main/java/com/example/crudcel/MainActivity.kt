package com.example.crudcel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_c_celular.*
import android.widget.AdapterView
import android.widget.ArrayAdapter



class MainActivity : AppCompatActivity() {

    lateinit var celular: Celular



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_celular)


        //inicializar array
        val marcasCelu = arrayOf("Lenovo", "Motorola","LG", "Samgsung","Xiomi", "Apple")

        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            marcasCelu // Array
        )

        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with adapter
        spinnerMarca.adapter = adapter;


        // Set an on item selected listener for spinner object
        spinnerMarca.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                //text_view.text = "Spinner selected : ${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }




    }

    fun save(view: View) {
        if (tvModelo.text.isNullOrEmpty())
            Toast.makeText(
                this,
                "O nome do modelo não pode ser vazio",
                Toast.LENGTH_LONG
            ).show()
        else {
            // instacia o objeto vazio
            celular = Celular()
            popObj()

            var intent = Intent()
            // envia o objeto com a chave da constante
            intent.putExtra(EXTRA_REPLY, celular)

            // envia o resultado da montagem do objeto para  intent
            setResult(Activity.RESULT_OK, intent)

            // fecha a activity
            finish()
        }
    }


    private fun popObj() {
        celular.modelo = tvModelo.text.toString()
        celular.marca = spinnerMarca.selectedItem.toString()
        celular.preco = tvPreco.text.toString().toDouble()
        celular.espaco = tvEspaco.text.toString().toInt()


    }

    // constate para acessar de qualquer classe do aplicaivo
    companion object {
        const val EXTRA_REPLY = "com.example.crudcel.REPLY"
        const val EXTRA_DELETE = "com.example.crudcel.DELETE"
    }


}