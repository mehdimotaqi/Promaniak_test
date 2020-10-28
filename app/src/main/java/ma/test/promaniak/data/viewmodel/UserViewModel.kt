package ma.test.promaniak.data.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ma.test.promaniak.data.Repository
import ma.test.promaniak.data.entities.User
import ma.test.promaniak.ui.DataLoadingState

class UserViewModel(application: Application): AndroidViewModel(application) {
    private var disposable: Disposable? = null

    private val repository = Repository()

    val dataLoadingState = MutableLiveData<DataLoadingState>()

    fun getAllUsers(): LiveData<List<User>>{
        val allUser = MutableLiveData<List<User>>()
        dataLoadingState.postValue(DataLoadingState.LOADING)
        disposable = repository.gatAllUser
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    dataLoadingState.postValue(DataLoadingState.LOADED)
                    allUser.postValue(result)
                },
                { error ->
                    dataLoadingState.postValue(DataLoadingState.ERROR)
                    Log.d("ERROR", error.localizedMessage)
                })
        return allUser
    }

    fun updateUser(id: String, user: User){
        disposable = repository.editUser(id, user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun deleteUser(id: String){
        disposable = repository.deleteUser(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun addNewUser(user: User){
        disposable = repository.addNewUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()


    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}