package com.example.thatshot.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.domain.models.Ingredient
import com.example.thatshot.domain.models.Recipe
import com.example.thatshot.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewRecipeViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private var _allIngredients: MutableLiveData<List<Ingredient>> = MutableLiveData()
    val allIngredients: LiveData<List<Ingredient>> get() = _allIngredients

    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch(Dispatchers.Main){
        useCases.deleteRecipeUseCase(recipe)
    }
    fun deleteIngredients(recipeID: Int) = viewModelScope.launch(Dispatchers.Main){
       useCases.deleteIngFromRecipeUseCase(recipeID)
    }

    fun getIngredients(recipeID: Int) = viewModelScope.launch(Dispatchers.Main) {
        _allIngredients.value = useCases.getIngredientsUseCase(recipeID)

    }

}