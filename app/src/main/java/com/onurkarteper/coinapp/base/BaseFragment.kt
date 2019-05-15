package com.onurkarteper.coinapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.onurkarteper.coinapp.di.Injectable
import timber.log.Timber


@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment(), Injectable {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(layoutId, container, false)
    }

    protected var binding: T? = null


    protected fun baseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("Fragment Created")
        binding = DataBindingUtil.bind(view)
        binding?.lifecycleOwner = this
    }


    protected fun showErrorDialog(message: String) {
        baseActivity().showBaseErrorDialog(message)
    }

}