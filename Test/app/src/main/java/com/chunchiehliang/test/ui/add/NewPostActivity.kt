package com.chunchiehliang.test.ui.add

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chunchiehliang.test.R
import com.chunchiehliang.test.databinding.ActivityNewPostBinding
import timber.log.Timber

class NewPostActivity : AppCompatActivity() {

    private val viewModel: NewPostViewModel by lazy {
        ViewModelProviders.of(this).get(NewPostViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("New Post")

        val binding: ActivityNewPostBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_post)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToHome.observe(this, Observer {
            it?.let {
                Timber.d("Clicked: $it")
                if(it) {
                    finish()
                }
                viewModel.onHomeActivityNavigated()
            }
        })
    }
}
