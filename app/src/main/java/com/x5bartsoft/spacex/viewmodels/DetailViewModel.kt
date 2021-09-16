package com.x5bartsoft.spacex.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.x5bartsoft.spacex.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {

    /** RETROFIT */







}
