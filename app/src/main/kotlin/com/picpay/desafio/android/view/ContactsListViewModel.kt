package com.picpay.desafio.android.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.base.BaseViewModel
import com.picpay.desafio.android.data.PicpayRepository
import com.picpay.desafio.android.data.model.User
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContactsListViewModel(
    ioScope: CoroutineContext,
    private val picpayRepository: PicpayRepository
) : BaseViewModel(ioScope) {

    private val _userContactsData = MutableLiveData<List<User>>()

    fun getData(): LiveData<List<User>> {

        backgroundScope.launch {
            val data = picpayRepository.getUsers()
            _userContactsData.postValue(data)
        }

        return _userContactsData
    }
}