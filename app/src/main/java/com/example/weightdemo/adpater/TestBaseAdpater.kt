import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weightdemo.databinding.AdpaterTvBinding

class TestBaseAdpater(val context: Context, list: List<String>) :
    BaseRecyclerAdpater<String, AdpaterTvBinding>(context, list) {


    override fun conver(holder: BaseViewHolder<AdpaterTvBinding>, t: String, position: Int) {
        holder.viewBinding.textview.text = t
        if (position % 2 == 0) {
            holder.viewBinding.textview.visibility = View.GONE
        } else {
            holder.viewBinding.textview.visibility = View.VISIBLE
        }

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): AdpaterTvBinding {
        return AdpaterTvBinding.inflate(inflater, parent, false)
    }

}