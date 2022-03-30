package br.com.me.san.visitcard.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.me.san.visitcard.data.BusinessCard
import br.com.me.san.visitcard.databinding.ItemBussinessCardBinding

class BussinessCardAdapter
    : ListAdapter<BusinessCard, BussinessCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BussinessCardAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBussinessCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BussinessCardAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemBussinessCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BusinessCard) {
            binding.tvNome.text = item.nome
            binding.tvTelefone.text = item.telefone
            binding.tvEmail.text = item.email
            binding.tvNomeEmpresa.text = item.empresa
            binding.cdContent.setCardBackgroundColor(Color.parseColor(item.fundoPersonalizado))
            binding.cdContent.setOnClickListener {
                listenerShare(it)
            }
        }
    }

}

class DiffCallback : DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem == newItem
    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        oldItem.id == newItem.id
}