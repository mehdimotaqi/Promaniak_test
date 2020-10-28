package ma.test.promaniak.network

import io.reactivex.disposables.Disposable
import ma.test.promaniak.data.entities.User

class ApiManager {

    private val apiServe by lazy {
        Api.create()
    }

    fun getAllUsers() = apiServe.getAllUsers()
    fun getUserByPhone(phone: String) = apiServe.getUserByPhone(phone)
    fun addNewUser(user: User) = apiServe.addNewUser(user)
    fun editUser(id: String, user: User) = apiServe.editUser(id, user)
    fun deletedUser(id: String) = apiServe.deletedUser(id)
}