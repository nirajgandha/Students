package com.school.students.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.students.databinding.FragmentFeeBinding
import com.school.students.databinding.FragmentHomeWorkBinding

class FeeFragment : Fragment() {

    var _feeBinding : FragmentFeeBinding? = null
    val feeBinding get() = _feeBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _feeBinding = FragmentFeeBinding.inflate(inflater)
        return feeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}