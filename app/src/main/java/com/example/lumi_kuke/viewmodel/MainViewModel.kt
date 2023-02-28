//package com.example.lumi_kuke.viewmodel
//
//import android.app.Application
//import android.content.Context
//import android.net.ConnectivityManager
//import android.net.NetworkCapabilities
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import com.example.lumi_kuke.data.Repository
//import com.example.lumi_kuke.model.Recipe
//import com.example.lumi_kuke.util.NetworkResult
//import kotlinx.coroutines.launch
//import retrofit2.Call
//import javax.inject.Inject
//
//class MainViewModel @Inject constructor(private val repository: Repository, application: Application) : AndroidViewModel(application) {
//    var recipesResponse: MutableLiveData<NetworkResult<Recipe>> = MutableLiveData()
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
//        getRecipesSafeCall(queries)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
//        recipesResponse.value = NetworkResult.Loading()
//        if (hasInternetConnection()) {
//            try {
//                val response = repository.remote.getRecipes()
//                recipesResponse.value = handleRecipesResponse(response)
//            } catch (e: java.lang.Exception) {
//                recipesResponse.value = NetworkResult.Error("Recipes not found")
//            }
//        } else {
//            recipesResponse.value = NetworkResult.Error("No internet Connection")
//        }
//    }
//
//    private fun handleRecipesResponse(response: Call<Recipe>): NetworkResult<Recipe>? {
//        when {
//            response.toString().contains("timeout") -> {
//                return NetworkResult.Error("Timeout")
//            }
//            else -> {
//                return  NetworkResult.Error("Error")
//            }
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun hasInternetConnection(): Boolean {
//
//        val connectivityManager = getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val  activeNetwork = connectivityManager.activeNetwork ?: return false
//        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
//        return when {
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//            else -> false
//        }
//    }
//}