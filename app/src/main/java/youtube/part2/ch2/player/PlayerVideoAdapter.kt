package youtube.part2.ch2.player

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fastcampus.part2.chapter12.R
import fastcampus.part2.chapter12.databinding.ItemVideoBinding
import fastcampus.part2.chapter12.databinding.ItemVideoHeaderBinding

class PlayerVideoAdapter(private val context: Context, private val onClick: (PlayerVideo) -> Unit) :
    ListAdapter<PlayerVideoModel, RecyclerView.ViewHolder>(diffUtil) {

    inner class HeaderViewHolder(private val binding: ItemVideoHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PlayerHeader) {
            binding.titleTextView.text = item.title
            binding.subTitleTextView.text = context.getString(
                R.string.header_sub_title_video_info, item.viewCount, item.dateText
            )
            binding.channelNameTextView.text = item.channelName
            binding.channelLogoImageView

            Glide.with(binding.channelLogoImageView)
                .load(item.channelThumb)
                .circleCrop()
                .into(binding.channelLogoImageView)
        }
    }

    inner class VideoViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlayerVideo) {
            binding.titleTextView.text = item.title
            binding.subTitleTextView.text = context.getString(
                R.string.sub_title_video_info, item.channelName, item.viewCount, item.dateText
            )

            Glide.with(binding.videoThumbnailImageView)
                .load(item.videoThumb)
                .into(binding.videoThumbnailImageView)

            Glide.with(binding.channelLogoImageView)
                .load(item.channelThumb)
                .circleCrop()
                .into(binding.channelLogoImageView)

            binding.root.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            HeaderViewHolder(
                ItemVideoHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            VideoViewHolder(
                ItemVideoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_HEADER) {
            (holder as HeaderViewHolder).bind(currentList[position] as PlayerHeader)
        } else {
            (holder as VideoViewHolder).bind(currentList[position] as PlayerVideo)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_VIDEO
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_VIDEO = 1

        val diffUtil = object : DiffUtil.ItemCallback<PlayerVideoModel>() {
            override fun areItemsTheSame(
                oldItem: PlayerVideoModel,
                newItem: PlayerVideoModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PlayerVideoModel,
                newItem: PlayerVideoModel
            ): Boolean {
                return if (oldItem is PlayerVideo && newItem is PlayerVideo) {
                    oldItem == newItem
                } else if (oldItem is PlayerHeader && newItem is PlayerHeader) {
                    oldItem == newItem
                } else {
                    false
                }
            }
        }
    }
}