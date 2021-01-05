package com.school.students.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.students.R
import com.school.students.databinding.FragmentHomeBinding
import com.school.students.databinding.FragmentHomeWorkBinding

class HomeWorkFragment : Fragment() {

    var _homeWorkBinding : FragmentHomeWorkBinding? = null
    val homeWorkBinding get() = _homeWorkBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _homeWorkBinding = FragmentHomeWorkBinding.inflate(inflater)
        return homeWorkBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}