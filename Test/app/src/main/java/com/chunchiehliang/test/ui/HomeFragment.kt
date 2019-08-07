package com.chunchiehliang.test.ui


import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.test.R
import com.chunchiehliang.test.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val viewModel: PostListViewModel by lazy {
        ViewModelProviders.of(this).get(PostListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentHomeBinding.inflate(inflater)

        val adapter = PostListAdapter(PostClickListener() { post ->
            Toast.makeText(context, "${post.id}", Toast.LENGTH_SHORT).show()
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerList.adapter = adapter
//        binding.recyclerList.addItemDecoration(MarginItemDecoration((resources.getDimension(R.dimen.margin_normal)).toInt()))

        return binding.root
    }

    /**
    Handle the RecyclerView item's margin
     */
    private inner class MarginItemDecoration(val margin: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)

            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = margin
            }
            outRect.bottom = margin
            outRect.left = margin
            outRect.right = margin
        }
    }
}


