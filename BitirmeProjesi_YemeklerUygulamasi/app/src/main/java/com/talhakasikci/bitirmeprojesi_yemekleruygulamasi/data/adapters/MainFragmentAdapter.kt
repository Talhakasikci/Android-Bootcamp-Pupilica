import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.R
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.models.Yemek
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.databinding.YemeklerRowMainFragmentBinding

class MainFragmentAdapter(
    private val yemekListesi: List<Yemek>,
    private val onItemClick: (Yemek) -> Unit,
    private val onItemAddClick: (Yemek) -> Unit
) : RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        val binding = YemeklerRowMainFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        val yemek = yemekListesi[position]
        holder.bind(yemek, onItemAddClick)

        // Set click listener
        holder.itemView.setOnClickListener {
            onItemClick(yemek)
        }
    }

    override fun getItemCount(): Int = yemekListesi.size

    class MainFragmentViewHolder(private val binding: YemeklerRowMainFragmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(yemek: Yemek, onItemAddClick: (Yemek) -> Unit) {
            binding.YemekIsmiTextViewRV.text = yemek.yemek_adi
            binding.YemekFiyatTextViewRV.text = "${yemek.yemek_fiyat} TL"

            // Glide ile resim yükleme
            val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
            Glide.with(binding.root.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageViewRV)

            binding.addButton.setOnClickListener {
                // Tıklama olayını tetikle
                onItemAddClick(yemek)
            }
        }


    }
}