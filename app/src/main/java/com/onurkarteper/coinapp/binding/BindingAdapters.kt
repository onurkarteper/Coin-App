package com.onurkarteper.coinapp.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * Data Binding adapters specific to the app.
 */

object BindingAdapters {


    @JvmStatic
    @BindingAdapter("visibleGone")
    fun visibleGone(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView)
                .load(url)
                .into(imageView)
        }

    }
    /*   @JvmStatic
       @BindingAdapter("visibleGone")
       fun showHide(view: View, show: Boolean) {
           view.visibility = if (show) View.VISIBLE else View.GONE
       }

       @JvmStatic
       @BindingAdapter("loadHighResPoster")
       fun loadHighResPoster(imageView: ImageView, movie: Movie) {
           Glide.with(imageView)
               .load("https://image.tmdb.org/t/p/w780/" + movie.backdropPath)
               .into(imageView)
       }
   */
}



