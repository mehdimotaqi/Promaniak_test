package ma.test.promaniak.network

import io.reactivex.Observable
import io.reactivex.Single
import ma.test.promaniak.data.entities.User
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {

    companion object {
        const val BASE_URL = "http://127.0.0.1:8000/api/"
        fun create(): Api {
            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }


    /*
  API :  127.0.0.1:8000/api/users  
   Afficher tous les utilisateurs depuis la base de donnée

  API :  127.0.0.1:8000/api/users  
   Création d’un utilisateur

  API :  127.0.0.1:8000/api/users/show/{id} 
   Afficher les informations de l’utilisateur qui a le même {id}
  API :  127.0.0.1:8000/api/users/update/{id} 
   Modifier les informations de ce utilisateur qui a le même {id}
  API :  127.0.0.1:8000/api/users/delete/{id} 
   Supprimer l’utilisateur qui a le même {id}
  API :  127.0.0.1:8000/api/search 
   cherche sur un utilisateur avec ce nom, email ou téléphone
 */
    @GET("users")
    fun getAllUsers(): Observable<List<User>>

    @GET("search")
    fun getUserByPhone(@Query("userPhone") userPhone: String): Single<User>

    @POST("users")
    fun addNewUser(@Body user: User): Observable<ResponseBody>

    @PUT("users/update/{id}")
    fun editUser(@Path("id") id: String, @Body user: User): Observable<ResponseBody>

    @DELETE("users/delete/{id}")
    fun deletedUser(@Path("id") id: String): Observable<ResponseBody>

}