package ma.test.promaniak.data

import io.reactivex.Observable
import ma.test.promaniak.data.entities.User
import ma.test.promaniak.network.ApiManager
import okhttp3.ResponseBody

class Repository {

    private var  apiManager: ApiManager = ApiManager()

    val gatAllUser = apiManager.getAllUsers()

    fun getUserByPhone(phone: String) = apiManager.getUserByPhone(phone)

    fun addNewUser(user: User): Observable<ResponseBody>{
        return apiManager.addNewUser(user)
    }

    fun editUser(id: String, user: User): Observable<ResponseBody>{
        return apiManager.editUser(id, user)
    }

    fun deleteUser(id: String): Observable<ResponseBody>{
        return apiManager.deletedUser(id)
    }

}