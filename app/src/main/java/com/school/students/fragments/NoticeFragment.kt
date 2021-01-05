package com.school.students.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.students.databinding.FragmentNoticeBinding

class NoticeFragment : Fragment() {

    var _fragmentNoticeBinding : FragmentNoticeBinding? = null
    val fragmentNoticeBinding get() = _fragmentNoticeBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _fragmentNoticeBinding = FragmentNoticeBinding.inflate(inflater)
        return fragmentNoticeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}