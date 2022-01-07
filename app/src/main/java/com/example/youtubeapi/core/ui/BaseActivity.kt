package com.example.youtubeapi.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    protected abstract fun inflateVB(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateVB(layoutInflater);
        setContentView(binding.root)
        initView()
        initViewModel()
        initListener()
    }
    open fun initView() {}
    open fun initViewModel() {}
    open fun initListener() {}
}