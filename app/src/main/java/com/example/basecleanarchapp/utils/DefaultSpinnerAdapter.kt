package com.example.basecleanarchapp.utils


import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import com.skydoves.powerspinner.PowerSpinnerInterface
import com.skydoves.powerspinner.PowerSpinnerView
import com.skydoves.powerspinner.databinding.ItemDefaultPowerSpinnerLibraryBinding

/** definition of the non-value of Int type. */
internal const val NO_INT_VALUE: Int = Int.MIN_VALUE

/** definition of the non-selected index value. */
internal const val NO_SELECTED_INDEX: Int = -1
/** DefaultSpinnerAdapter is a default adapter composed of string items. */
class  DefaultSpinnerAdapter<T>(
    powerSpinnerView: PowerSpinnerView
) : RecyclerView.Adapter<DefaultSpinnerAdapter.DefaultSpinnerViewHolder>(),
    PowerSpinnerInterface<T> {

    override var index: Int = powerSpinnerView.selectedIndex
    override val spinnerView: PowerSpinnerView = powerSpinnerView
    override var onSpinnerItemSelectedListener: OnSpinnerItemSelectedListener<T>? = null

    private val spinnerItems: MutableList<T> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultSpinnerViewHolder {
        val binding =
            ItemDefaultPowerSpinnerLibraryBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        return DefaultSpinnerViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                notifyItemSelected(position)
            }
        }
    }

    override fun onBindViewHolder(holder: DefaultSpinnerViewHolder, position: Int) {
        val item = this.spinnerItems[position].toString()
        holder.bind(item, spinnerView)

    }

    override fun setItems(itemList: List<T>) {
        this.spinnerItems.clear()
        this.spinnerItems.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun notifyItemSelected(index: Int) {
        if (index == NO_SELECTED_INDEX) return
        val oldIndex = this.index
        this.index = index
        this.spinnerView.notifyItemSelected(index, spinnerItems[index].toString())
        this.onSpinnerItemSelectedListener?.onItemSelected(
            oldIndex = oldIndex,
            oldItem = null,/*oldIndex.takeIf { it != NO_SELECTED_INDEX }?.let {
                spinnerItems[oldIndex]
                                                                       }*/
            newIndex = index,
            newItem = spinnerItems[index]
        )
    }

    override fun getItemCount() = spinnerItems.size

    class DefaultSpinnerViewHolder(private val binding: ItemDefaultPowerSpinnerLibraryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharSequence, spinnerView: PowerSpinnerView) {
            binding.itemDefaultText.apply {
                text = item
                typeface = spinnerView.typeface
                gravity = spinnerView.gravity
                setTextSize(TypedValue.COMPLEX_UNIT_PX, spinnerView.textSize)
                setTextColor(spinnerView.currentTextColor)
            }
            binding.root.setPadding(
                spinnerView.paddingLeft,
                spinnerView.paddingTop,
                spinnerView.paddingRight,
                spinnerView.paddingBottom
            )
        }
    }
}
