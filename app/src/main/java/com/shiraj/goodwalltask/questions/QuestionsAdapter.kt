package com.shiraj.goodwalltask.questions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiraj.domain.model.QuestionEntity
import com.shiraj.goodwalltask.databinding.ItemRowQuestionBinding

class QuestionsAdapter(
    private val list: ArrayList<QuestionEntity>,
    private val onQuestionClick: (details: QuestionEntity, view: View, position: Int) -> Unit
) :
    RecyclerView.Adapter<QuestionsAdapter.QAHolder>() {
    inner class QAHolder(private val binding: ItemRowQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QuestionEntity) {
            binding.apply {
                this.item = item
                position = adapterPosition
                root.setOnClickListener {
                    onQuestionClick.invoke(item, binding.viewQn, adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QAHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowQuestionBinding.inflate(inflater, parent, false)
        return QAHolder(binding)
    }

    override fun onBindViewHolder(holder: QAHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun update(newList: List<QuestionEntity>) {
        list.clear()
        list.addAll(newList)
        notifyItemRangeChanged(0, itemCount)
    }
}