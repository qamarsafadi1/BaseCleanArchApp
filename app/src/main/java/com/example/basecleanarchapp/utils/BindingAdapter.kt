package com.example.basecleanarchapp.utils


import android.content.res.ColorStateList
import android.graphics.*
import android.os.Build
import android.os.CountDownTimer
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.github.chrisbanes.photoview.PhotoView
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.skydoves.elasticviews.ElasticImageView


@BindingAdapter("bind_imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let { view.setPhoto(it, view.context) }

}

@BindingAdapter("bind_imageUrl")
fun loadImage(view: PhotoView, imageUrl: String?) {
    imageUrl?.let { view.setPhoto(it, view.context) }

}


fun addGradient(originalBitmap: Bitmap): Bitmap? {
    val width = originalBitmap.width
    val height = originalBitmap.height
    val updatedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(updatedBitmap)
    canvas.drawBitmap(originalBitmap, 0f, 0f, null)
    val paint = Paint()
    val shader: LinearGradient =
        LinearGradient(0f, 0f, 0f, height.toFloat(), -0xf2dae, -0xf8cfb, Shader.TileMode.CLAMP)
    paint.setShader(shader)
    paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    return updatedBitmap
}


@BindingAdapter("strike_textview")
fun strikeText(view: TextView, isStrike: String) {
    if (isStrike == "true")
        view.strikethrough()

}

@BindingAdapter("bind_background_color")
fun btnColor(view: MaterialButton, color: String?) {
    color?.let {
        view.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
    }
}

@BindingAdapter("bind_text_color")
fun btnColor(view: TextView, color: String?) {
    color?.let {
        view.setTextColor(Color.parseColor(color))
    }
}

@BindingAdapter("bind_background_color")
fun btnColor(view: View, color: String?) {
    color?.let {
        view.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
    }

}

@BindingAdapter("bind_imageUrl")
fun loadImage(view: ShapeableImageView, imageUrl: String?) {
    imageUrl?.let {
        view.shapeAppearanceModel = view.shapeAppearanceModel
            .toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED, 10f)
            .setTopLeftCorner(CornerFamily.ROUNDED, 10f)
            .build()
        view.setPhoto(it, view.context)
    }

}

@BindingAdapter("bind_res")
fun loadImage(view: ImageView, imgRes: Int) {
    view.setImageResource(imgRes)

}

@BindingAdapter("bind_price_currency")
fun setPriceCurrency(view: TextView, price: Float?) {
    view.text = Common.withCurrency(view.context, price ?: 0f)
}

@BindingAdapter("bind_price")
fun setPrice(view: TextView, price: Float?) {
    view.text = Common.setPrice(price ?: 0f)
}


@BindingAdapter("bind_currency")
fun setCurrency(view: TextView, currency: Any?) {
    // view.text = LocalData.configs()
//    if (LocalData.getAppLocale() == "ar")
//        view.text = LocalData.configs?.currencyAr
//    else view.text = LocalData.configs?.currencyEn
}

@BindingAdapter("bind_htmlText")
fun setHtmlText(view: TextView, text: String?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        view.text = Html.fromHtml(text ?: "", Html.FROM_HTML_MODE_LEGACY)
            .toString()
    } else {
        view.text = Html.fromHtml(text ?: "")
            .toString()
    }
}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListLatest(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.latest_item,
//        onClick = { position: Int, _: Any ->
//            onClick()
//
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as LatestItemBinding
//            binding.cartBtn.onClick {
//                onClick()
//            }
//            when (position) {
//                1 -> binding.img.setImageResource(R.drawable.p1)
//                2 -> binding.img.setImageResource(R.drawable.p2)
//                3 -> binding.img.setImageResource(R.drawable.p3)
//            }
//        })
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListNotification(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.notification_item,
//        onClick = { position: Int, _: Any ->
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as NotificationItemBinding
//            when (position) {
//                0 -> binding.notificationLayout1.rootLt.visible()
//                1 -> binding.notificationLayout1.rootLt.visible()
//                else -> {
//                    binding.notificationLayout1.rootLt.gone()
//                    binding.notificationLayout2.rootLt.visible()
//                }
//            }
//
//        })
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListFav(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.fav_item,
//        onClick = { position: Int, _: Any ->
//
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as FavItemBinding
//            binding.remove.onClick {
//
//                onClick()
//            }
//        })
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initLisCart(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var amount = 0
//
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.cart_item,
//        onClick = { position: Int, _: Any ->
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as CartItemBinding
//            amount = 1
//
//            binding.controlQuantityCounter(amount)
//            binding.deleteBtn.onClick {
//                onClick()
//            }
//        })
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//private fun CartItemBinding.controlQuantityCounter(_amount: Int) {
//    var amount = _amount
//    quantityTxt.text = String.format("%02d", amount)
//    incBtn.setOnClickListener {
//        if (amount < 99) {
//            amount += 1
//            quantityTxt.text = String.format("%02d", amount)
//        }
//    }
//    decBtn.setOnClickListener {
//        if (amount > 1) {
//            amount -= 1
//            quantityTxt.text = String.format("%02d", amount)
//        }
//    }
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListSize(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.size_item,
//        onClick = { position: Int, _: Any ->
//
//            adapter?.selectedPosition = position
//            adapter?.notifyDataSetChanged()
//            onClick()
//
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as SizeItemBinding
//            if (position == adapter?.selectedPosition) {
//                binding.rootLt.backgroundTintList =
//                    ColorStateList.valueOf(view.context.findColor(R.color.colorAccent))
//                binding.sizeTxt.setTextColor(view.context.findColor(R.color.white))
//            } else {
//                binding.rootLt.backgroundTintList =
//                    ColorStateList.valueOf(view.context.findColor(R.color.bg))
//                binding.sizeTxt.setTextColor(view.context.findColor(R.color.colorTextDark))
//            }
//        })
//    adapter.selectedPosition = 0
//
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListCommentRate(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.comment_rate_item,
//        onClick = { position: Int, _: Any ->
//
//            onClick()
//
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as CommentRateItemBinding
//
//            when (position) {
//                1 -> binding.commentTxt.visible()
//                2 -> binding.commentTxt.visible()
//                else -> binding.commentTxt.gone()
//            }
//        })
//    adapter.selectedPosition = 0
//
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListLatestProducts(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.latest_product_item,
//        onClick = { position: Int, _: Any ->
//            onClick()
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as LatestProductItemBinding
//        })
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListPurshase(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.purchase_item,
//        onClick = { position: Int, _: Any ->
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as PurchaseItemBinding
//            if (position == 0) {
//                binding.rateBtn.backgroundTintList =
//                    ColorStateList.valueOf(Color.parseColor("#5EBC9C"))
//                binding.rateBtn.cornerRadius = 5f
//                binding.rateBtn.onClick { onClick() }
//
//            }
//        })
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListOrders(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.order_item,
//        onClick = { position: Int, _: Any ->
//            onClick()
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as OrderItemBinding
//            if (position == 0 || position == 1) {
//                binding.statusTxt.text = view.context.getString(R.string.waitong)
//                binding.statusTxt.setTextColor(view.context.findColor(R.color.colorAccent))
//            }
//        })
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListOrderAddresses(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.address_item_order,
//        onClick = { position: Int, _: Any ->
//            adapter?.selectedPosition = position
//            adapter?.notifyDataSetChanged()
//            onClick()
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as AddressItemOrderBinding
//            if (position == adapter?.selectedPosition) {
//                binding.selected.setImageResource(R.drawable.rbselect)
//            } else {
//                binding.selected.setImageResource(R.drawable.ic_rbunselect)
//            }
//        })
//
//    adapter.selectedPosition = 0
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListPayment(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.payment_item,
//        onClick = { position: Int, _: Any ->
//            adapter?.selectedPosition = position
//            adapter?.notifyDataSetChanged()
//            onClick()
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as PaymentItemBinding
//            when (position) {
//                0 -> {
//                    binding.img.setImageResource(R.drawable.ic_keynet)
//                    binding.txt.text = view.context.getString(R.string.key_net)
//                }
//                1 -> {
//                    binding.img.setImageResource(R.drawable.ic_cod)
//                    binding.txt.text = view.context.getString(R.string.cod)
//                }
//                else -> {
//                    binding.img.setImageResource(R.drawable.ic_visa)
//                    binding.txt.text = view.context.getString(R.string.visa)
//                }
//            }
//            if (position == adapter?.selectedPosition) {
//                binding.card.strokeWidth = 2
//                bind.img.imageTintList =
//                    ColorStateList.valueOf(view.context.getColor(R.color.colorAccent))
//            } else {
//                binding.card.strokeWidth = 0
//                bind.img.imageTintList =
//                    ColorStateList.valueOf(view.context.getColor(R.color.colorPrimaryDark))
//            }
//        })
//
//    adapter.selectedPosition = 0
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListOrderProducts(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.order_products_item,
//        onClick = { position: Int, _: Any ->
//
//            onClick()
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as OrderProductsItemBinding
//        })
//
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListProductsRate(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.order_rate_item,
//        onClick = { position: Int, _: Any ->
//
//            onClick()
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as OrderRateItemBinding
//            binding.rating.isEnabled = true
//
//            binding.rating.setOnRatingBarChangeListener { ratingBar, rate, b ->
//                if (rate < 3)
//                    binding.rateReason.visible()
//                else binding.rateReason.gone()
//            }
//            bind.add.onClick {
//                binding.rateReason.gone()
//                binding.commentTxt.visible()
//            }
//        })
//
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListAddress(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.address_item,
//        onClick = { position: Int, _: Any ->
//            adapter?.selectedPosition = position
//            adapter?.notifyDataSetChanged()
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as AddressItemBinding
//            if (position == adapter?.selectedPosition) {
//                binding.card.strokeWidth = 2
//            }
//        })
//    view.adapter = adapter
//    adapter.selectedPosition = 1
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListMostRated(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.most_rated_item,
//        onClick = { position: Int, _: Any ->
//            onClick()
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as MostRatedItemBinding
//        })
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListCategory(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.category_item,
//        onClick = { position: Int, _: Any ->
//            onClick()
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as CategoryItemBinding
//        })
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListCategoryProducts(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.category_filter,
//        onClick = { position: Int, _: Any ->
//            adapter?.selectedPosition = position
//            onClick()
//            adapter?.notifyDataSetChanged()
//        },
//        onBind = { position: Int, item: Any, _: View, bind: ViewDataBinding ->
//            val binding = bind as CategoryFilterBinding
//            bind.selectedCheck.gone()
//            if (position==0){
//                bind.catName.text = view.context.getString(R.string.show_all)
//            }else{
//                bind.catName.text = view.context.getString(R.string.temp_cat)
//            }
//            if (position == adapter?.selectedPosition) {
//                binding.rootLt.setBackgroundResource(R.drawable.solid_shape)
//                binding.selectedCheck.imageTintList =
//                    ColorStateList.valueOf(view.context.findColor(R.color.colorPrimaryDark))
//            } else {
//                binding.rootLt.setBackgroundResource(R.drawable.stroked_shape)
//                binding.selectedCheck.imageTintList =
//                    ColorStateList.valueOf(view.context.findColor(R.color.gray_bg2))
//            }
//        })
//    adapter.selectedPosition = 0
//    view.adapter = adapter
//    adapter.items = list?.toMutableList()!!
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListPerfumeType(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.category_home_item,
//        onClick = { position, item ->
//        },
//        onBind = { position, item, itemView, binding ->
//            val bind = binding as CategoryHomeItemBinding
//            bind.itemBtn.onClick {
//                adapter?.selectedPosition = position
//                onClick()
//                adapter?.notifyDataSetChanged()
//            }
//            if (position == adapter?.selectedPosition) {
//                bind.card.setCardBackgroundColor(view.context.findColor(R.color.colorAccent))
//            } else {
//                bind.card.setCardBackgroundColor((view.context.findColor(R.color.transparent2)))
//            }
//        })
//
//    view.adapter = adapter
//    if (list != null) {
//        adapter.items = list
//    }
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListSize(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    val bold = Typeface.createFromAsset(
//        view.context.assets,
//        "fonts/regular.ttf"
//    )
//    val light = Typeface.createFromAsset(
//        view.context.assets,
//        "fonts/light.ttf"
//    )
//    adapter = Adapter(
//        R.layout.size_item,
//        onClick = { position, item ->
//        },
//        onBind = { position, item, itemView, binding ->
//            val bind = binding as SizeItemBinding
//            bind.itemBtn.onClick {
//                adapter?.selectedPosition = position
//                onClick()
//                adapter?.notifyDataSetChanged()
//            }
//            if (position == adapter?.selectedPosition) {
//                bind.sizeText.typeface = bold
//                bind.sizeType.typeface = bold
//                bind.card.setCardBackgroundColor(view.context.findColor(R.color.colorAccent))
//                bind.sizeText.setTextColor(view.context.findColor(R.color.colorPrimaryDark))
//                bind.sizeType.setTextColor(view.context.findColor(R.color.colorPrimaryDark))
//            } else {
//                bind.sizeText.typeface = light
//                bind.sizeType.typeface = light
//                bind.card.setCardBackgroundColor((view.context.findColor(R.color.transparent2)))
//                bind.sizeText.setTextColor(view.context.findColor(R.color.white))
//                bind.sizeType.setTextColor(Color.parseColor("#A1A1A1"))
//            }
//        })
//
//    view.adapter = adapter
//    if (list != null) {
//        adapter.items = list
//    }
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListSizeDetails(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    val bold = Typeface.createFromAsset(
//        view.context.assets,
//        "fonts/regular.ttf"
//    )
//    val light = Typeface.createFromAsset(
//        view.context.assets,
//        "fonts/light.ttf"
//    )
//    adapter = Adapter(
//        R.layout.size_item_details,
//        onClick = { position, item ->
//        },
//        onBind = { position, item, itemView, binding ->
//            val bind = binding as SizeItemDetailsBinding
//            bind.itemBtn.onClick {
//                adapter?.selectedPosition = position
//                onClick()
//                adapter?.notifyDataSetChanged()
//            }
//            if (position == adapter?.selectedPosition) {
//                bind.sizeText.typeface = bold
//                bind.sizeType.typeface = bold
//                bind.card.setCardBackgroundColor(view.context.findColor(R.color.colorAccent))
//                bind.sizeText.setTextColor(view.context.findColor(R.color.colorPrimaryDark))
//                bind.price.setTextColor(view.context.findColor(R.color.colorAccent))
//                bind.currency.setTextColor(view.context.findColor(R.color.colorAccent))
//                bind.sizeType.setTextColor(view.context.findColor(R.color.colorPrimaryDark))
//            } else {
//                bind.sizeText.typeface = light
//                bind.sizeType.typeface = light
//                bind.price.setTextColor(view.context.findColor(R.color.white))
//                bind.currency.setTextColor(view.context.findColor(R.color.white))
//                bind.card.setCardBackgroundColor((view.context.findColor(R.color.transparent2)))
//                bind.sizeText.setTextColor(view.context.findColor(R.color.white))
//                bind.sizeType.setTextColor(Color.parseColor("#A1A1A1"))
//            }
//        })
//
//    view.adapter = adapter
//    if (list != null) {
//        adapter.items = list
//    }
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListRelated(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    adapter = Adapter(
//        R.layout.related_item,
//        onClick = { position, item ->
//        },
//        onBind = { position, item, itemView, binding ->
//            val bind = binding as RelatedItemBinding
//        })
//
//    view.adapter = adapter
//    if (list != null) {
//        adapter.items = list
//    }
//}
//
//@BindingAdapter(value = ["list", "onClick"])
//fun initListPerfumeRegion(
//    view: RecyclerView, list: MutableList<Any>? = mutableListOf(),
//    onClick: () -> Unit
//) {
//    var adapter: Adapter<Any>? = null
//    val bold = Typeface.createFromAsset(
//        view.context.assets,
//        "fonts/regular.ttf"
//    )
//    val light = Typeface.createFromAsset(
//        view.context.assets,
//        "fonts/light.ttf"
//    )
//    adapter = Adapter(
//        R.layout.perfume_region_item,
//        onClick = { position, item ->
//        },
//        onBind = { position, item, itemView, binding ->
//            val bind = binding as PerfumeRegionItemBinding
//            bind.itemBtn.onClick {
//                adapter?.selectedPosition = position
//                onClick()
//                adapter?.notifyDataSetChanged()
//            }
//            if (position == adapter?.selectedPosition) {
//                bind.sizeText.typeface = bold
//                bind.card.setCardBackgroundColor(view.context.findColor(R.color.colorAccent))
//                bind.sizeText.setTextColor(view.context.findColor(R.color.colorPrimaryDark))
//            } else {
//                bind.sizeText.typeface = light
//                bind.card.setCardBackgroundColor((view.context.findColor(R.color.transparent2)))
//                bind.sizeText.setTextColor(view.context.findColor(R.color.white))
//            }
//        })
//
//    view.adapter = adapter
//    if (list != null) {
//        adapter.items = list
//    }
//}

@BindingAdapter("bind_visibility")
fun setVisibility(view: LinearLayout, isVisible: Int?) {
    if (isVisible == 0)
        view.gone()
    else view.visible()
}

@BindingAdapter("bind_visibility_text")
fun setVisibility(view: TextView, isVisible: Int?) {
    if (isVisible == 0)
        view.gone()
    else view.visible()
}

@BindingAdapter("bind_share")
fun share(view: ElasticImageView, isVisible: Int?) {
    view.onClick {
        view.context.share()
    }
}

@BindingAdapter("bind_empty_text")
fun setTextEmpty(view: TextView, text: Any?) {
    if (text == null)
        view.text = ""
    else view.text = text.toString()
}

@BindingAdapter("bind_decimal")
fun setDecimal(view: TextView, count: Int) {
    view.text = Common.setDecimal(count)
}

@BindingAdapter("bind_color")
fun setColor(view: TextView, color: String) {
    try {
        view.setTextColor(Color.parseColor(color))

    } catch (ex: RuntimeException) {
        ex.printStackTrace()
    }

}
