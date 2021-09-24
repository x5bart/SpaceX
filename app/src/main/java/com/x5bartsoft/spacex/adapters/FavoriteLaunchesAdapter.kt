package com.x5bartsoft.spacex.adapters

import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.data.database.etities.FavoriteEntity
import com.x5bartsoft.spacex.databinding.LayoutLaunchFavoritesRowBinding
import com.x5bartsoft.spacex.ui.fragments.FavoriteFragmentDirections
import com.x5bartsoft.spacex.util.LaunchDiffUtil
import com.x5bartsoft.spacex.viewmodels.MainViewModel


class FavoriteLaunchesAdapter (
    private val requireActivity: FragmentActivity,
    private val mainViewModel: MainViewModel,
) :
    RecyclerView.Adapter<FavoriteLaunchesAdapter.MyViewHolder>(), ActionMode.Callback {

    private lateinit var rootView: View

    private var multiSelection = false
    private var selectedRecipe = arrayListOf<FavoriteEntity>()
    private var myViewHolders = arrayListOf<MyViewHolder>()
    private var favoriteRecipes = emptyList<FavoriteEntity>()

    private lateinit var mActionMode: ActionMode

    class MyViewHolder(private val binding: LayoutLaunchFavoritesRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favoriteEntity: FavoriteEntity) {
            binding.entity = favoriteEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutLaunchFavoritesRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        myViewHolders.add(holder)
        rootView = holder.itemView.rootView


        val currentRecipe = favoriteRecipes[position]
        holder.bind(currentRecipe)

        saveItemStateScroll(currentRecipe, holder)
        /**
         * Single Click Listener
         **/
        holder.itemView.findViewById<ConstraintLayout>(R.id.l_favorite_row_layout2)
            .setOnClickListener {
                if (multiSelection) {
                    applySelection(holder, currentRecipe)
                } else {
                    val action =
                        FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(
                            currentRecipe.launch
                        )
                    holder.itemView.findNavController().navigate(action)
                }


            }

        /**
         * Long Click Listener
         **/
        holder.itemView.findViewById<ConstraintLayout>(R.id.l_favorite_row_layout2)
            .setOnLongClickListener {
                if (!multiSelection) {
                    multiSelection = true
                    requireActivity.startActionMode(this)
                    applySelection(holder, currentRecipe)
                    true
                } else {
                    applySelection(holder, currentRecipe)
                    true
                }
            }
    }

    private fun saveItemStateScroll(currentRecipe: FavoriteEntity, holder: MyViewHolder) {
        if (selectedRecipe.contains(currentRecipe)) {
            changeRecipeStyle(holder, R.color.cardBackgroundLightColor, R.color.colorPrimary)
        } else {
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
        }
    }


    private fun applySelection(holder: MyViewHolder, currentRecipe: FavoriteEntity) {
        if (selectedRecipe.contains(currentRecipe)) {
            selectedRecipe.remove(currentRecipe)
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
            applyActionMode()
        } else {
            selectedRecipe.add(currentRecipe)
            changeRecipeStyle(holder, R.color.cardBackgroundLightColor, R.color.colorPrimary)
            applyActionMode()
        }
    }

    private fun changeRecipeStyle(holder: MyViewHolder, backgroundColor: Int, strokeColor: Int) {
        holder.itemView.findViewById<ConstraintLayout>(R.id.l_favorite_row_layout2)
            .setBackgroundColor(ContextCompat.getColor(requireActivity, backgroundColor))
        holder.itemView.findViewById<MaterialCardView>(R.id.l_launch_favorite_cardView).strokeColor =
            ContextCompat.getColor(requireActivity, strokeColor)
    }

    private fun applyActionMode() {
        when (selectedRecipe.size) {
            0 -> {
                mActionMode.finish()
                multiSelection = false
            }
            1 -> mActionMode.title = "${selectedRecipe.size} item selected"
            else -> mActionMode.title = "${selectedRecipe.size} item selected"
        }
    }

    override fun getItemCount(): Int {
        return favoriteRecipes.size
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.favorite_contextual_menu, menu)
        mActionMode = mode!!
        applyStatusBarColor(R.color.contextualStatusBarColor)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        if (item?.itemId == R.id.delete_favorite_recipe_menu) {
            selectedRecipe.forEach { mainViewModel.deleteFavoriteLaunch(it) }
//            showSnackBar("${selectedRecipe.size} Recipes removed.")
            multiSelection = false
            selectedRecipe.clear()
            mode?.finish()
        }
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        myViewHolders.forEach { holder ->
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
        }
        multiSelection = false
        selectedRecipe.clear()
        applyStatusBarColor(R.color.statusBarColor)
    }

    private fun applyStatusBarColor(color: Int) {
        requireActivity.window.statusBarColor =
            ContextCompat.getColor(requireActivity, color)
    }

    fun setData(newFavoriteRecipe: List<FavoriteEntity>) {
        val favoriteRecipeDiffUtil =
            LaunchDiffUtil(favoriteRecipes, newFavoriteRecipe)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipeDiffUtil)
        favoriteRecipes = newFavoriteRecipe
        diffUtilResult.dispatchUpdatesTo(this)
    }

//    private fun showSnackBar(message: String) {
//        Snackbar.make(
//            rootView,
//            message,
//            Snackbar.LENGTH_SHORT
//        ).setAction("Okay") {}
//            .show()
//    }

    fun clearContextualActionMode() {
        if (this::mActionMode.isInitialized) {
            mActionMode.finish()
        }
    }
}