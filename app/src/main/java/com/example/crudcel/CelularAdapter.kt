package com.example.crudcel


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


import kotlinx.android.synthetic.main.item_lista.view.*


//resposavel por mostrar os dados na tela



class CelularAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<CelularAdapter.ViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)


    private var listCelu = listOf<Celular>()
    private var itens = emptyList<Celular>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CelularAdapter.ViewHolder {
        val view = inflater.inflate(
            com.example.crudcel.R.layout.item_lista,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = itens.count()

    override fun onBindViewHolder(holder: CelularAdapter.ViewHolder, position: Int) {
        val item = itens[position]
        holder.itemModelo.text = item.modelo
        holder.itemMarca.text = item.marca
        holder.itemPreco.text = item.preco.toString()
        holder.itemEspaco.text = item.espaco.toString()

    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val itemModelo: TextView = itemView.itemModelo
        val itemMarca: TextView = itemView.itemMarca
        val itemPreco: TextView = itemView.itemPreco
        val itemEspaco: TextView = itemView.itemEspaco

    }

    // método para atualizar os dados da lista
    fun setFornecedorLista(celulares: List<Celular>) {
        this.itens = celulares
        notifyDataSetChanged()
    }

    fun getCelularAtPosition(position: Int): Celular{
        return listCelu[position]
    }


}