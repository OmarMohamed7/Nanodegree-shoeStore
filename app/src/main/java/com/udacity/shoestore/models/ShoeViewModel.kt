package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

enum class State {
    Saved,
    None
}

class ShoeViewModel : ViewModel() {
    private var _shoelList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoelList

    private var _state = MutableLiveData<State>(State.None)
    val state: LiveData<State>
        get() = _state

    init {
        Timber.i("ShoeViewModel called")
        _shoelList.value = mutableListOf()
    }

}