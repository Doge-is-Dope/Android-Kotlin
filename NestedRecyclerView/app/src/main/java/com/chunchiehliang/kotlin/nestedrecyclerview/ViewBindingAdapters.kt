package com.chunchiehliang.kotlin.nestedrecyclerview

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("listMovieData")
fun bindMovieList(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("genreTags")
fun bindGenreList(recyclerView: RecyclerView, data: List<Genre>?) {
    recyclerView.adapter = (recyclerView.adapter as? GenreAdapter ?: GenreAdapter())
        .apply {
            genres = data ?: emptyList()
        }
}

@BindingAdapter("genreTagTint")
fun tagTint(textView: TextView, genreId: Int) {
    // Tint the colored dot
    (textView.compoundDrawablesRelative[0] as? GradientDrawable)?.setColor(
        tagTintOrDefault(genreId, textView.context)
    )
}

fun tagTintOrDefault(genreId: Int, context: Context): Int {
    return ContextCompat.getColor(
        context, when (genreId) {
            28 -> R.color.tag_color_1
            12 -> R.color.tag_color_2
            16 -> R.color.tag_color_3
            35 -> R.color.tag_color_4
            80 -> R.color.tag_color_5
            99 -> R.color.tag_color_6
            18 -> R.color.tag_color_7
            10751 -> R.color.tag_color_8
            14 -> R.color.tag_color_9
            36 -> R.color.tag_color_10
            27 -> R.color.tag_color_11
            10402 -> R.color.tag_color_12
            10749 -> R.color.tag_color_13
            9648 -> R.color.tag_color_14
            878 -> R.color.tag_color_15
            10770 -> R.color.tag_color_16
            53 -> R.color.tag_color_17
            10752 -> R.color.tag_color_18
            37 -> R.color.tag_color_19
            else -> R.color.tag_color_default
        }
    )
}