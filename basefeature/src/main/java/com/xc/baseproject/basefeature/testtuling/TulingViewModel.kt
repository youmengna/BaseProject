package com.xc.baseproject.basefeature.testtuling

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TulingViewModel : ViewModel() {
    private val mDisposable = CompositeDisposable()
    /**
     * Map <send -> true | receive -> false,message>
     */
    val mChatData = MutableLiveData<ArrayList<Pair<Boolean, String>>>()

    init {
        mChatData.value = ArrayList()
    }

    fun sendMessage(message: String) {
        val chatData = mChatData.value
        val disposable = TulingRepository.getTulingResponse(message).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    chatData?.add(Pair(true, message))
                    mChatData.value = chatData
                }
                .subscribe(
                        { result ->
                            chatData?.add(Pair(false, result?.result?.text ?: "something wrong"))
                            mChatData.value = chatData
                        },
                        { e -> Log.e("TulingViewModel", "sendMessage", e) }
                )
        mDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.dispose()
    }
}