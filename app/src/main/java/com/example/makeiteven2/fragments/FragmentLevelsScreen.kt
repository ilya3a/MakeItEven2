package com.example.makeiteven2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.makeiteven2.R
import com.example.makeiteven2.adapters.LevelsAdapter
import com.example.makeiteven2.data_models.Level
import kotlinx.android.synthetic.main.fragment_levels.view.*

class FragmentLevelsScreen : Fragment() {

    private lateinit var mCallBack : IFragmentLevelsScreenCallback
    private lateinit var mLevelsRecyclerView : RecyclerView
    private lateinit var mLevelsAdapter : LevelsAdapter
    private  var mCurrentStage = 0
    var  mLevelItemsList : ArrayList<Level> = ArrayList()

    interface IFragmentLevelsScreenCallback {
        fun onLevelsFragmentLevelClicked(view : View)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_levels,container,false)
        mLevelsRecyclerView = rootView.recyclerLevels
        //TODO: insted of 10 in testings, replase the spanCount to mCurrentStage
        mLevelsAdapter = LevelsAdapter(mLevelItemsList,rootView.context,10)
        initLevels()
        initRecyclerView()

        return rootView
    }

    private fun initLevels() {
        for (i in 1..100)
           mLevelItemsList.add(Level(i))
    }

    private fun initRecyclerView() {
        mLevelsRecyclerView.setHasFixedSize(true)
        mLevelsRecyclerView.layoutManager = GridLayoutManager(context, 4)
        mLevelsRecyclerView.adapter = mLevelsAdapter

    }
    override fun onAttach(context: Context) {
        if (context is IFragmentLevelsScreenCallback) {
            mCallBack = context
        }
        else{
            throw ClassCastException(context.toString() + "must implement OnLevelClicked")
        }
        super.onAttach(context)
    }
}