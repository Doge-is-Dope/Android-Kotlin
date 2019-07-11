package com.chunchiehliang.kotlin.nestedrecyclerview

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.kotlin.nestedrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate view and obtain an instance of the binding class.
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = MovieViewModel()

        binding.lifecycleOwner = this
        binding.movieViewModel = viewModel


        val adapter = MovieAdapter(MovieAdapter.MovieListener { movie ->
            viewModel.onMovieClicked(movie)
        })
        binding.recyclerMovieList.adapter = adapter
        binding.recyclerMovieList.addItemDecoration(MarginItemDecoration((resources.getDimension(R.dimen.margin_normal)).toInt()))
    }

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
