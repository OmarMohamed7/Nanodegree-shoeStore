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
    var items = mutableListOf(Shoe("test" , 42.0 , "Company1" , "this is a test description" , listOf<String>("test1")), Shoe("test2" , 45.5 , "Company2" , "this is a test2 description" , listOf<String>("test2")))
    private var _shoelList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoelList

    private var _state = MutableLiveData<State>(State.None)
    val state: LiveData<State>
        get() = _state

    init {
        Timber.i("ShoeViewModel called")
    }


    private fun addShoe(name:String, size: Double, company: String, description:String, images:List<String> ){
        Timber.i("Inserting a new shoe")
        val item = Shoe(name , size ,company , description , mutableListOf())
        _shoelList.value?.add(item)
    }

    fun onEventSave(name:String , size: Double , company:String , description: String){
        Timber.i("onEventSave")
        addShoe(name,size , company , description , listOf())
        _state.value = State.Saved
    }

    fun onEventSaveComplete(){
        _state.value = State.None
    }

    override fun onCleared() {
        Timber.i("On cleared ")
        _shoelList.value?.clear()
        super.onCleared()


    }

}