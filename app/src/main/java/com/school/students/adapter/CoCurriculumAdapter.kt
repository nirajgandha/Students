package com.school.students.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.school.students.R
import com.school.students.databinding.CocurriculumRecyclerItemBinding
import com.school.students.model.CoCurriculum
import com.school.students.model.Syllabus
import java.util.*


class CoCurriculumAdapter(private var cocurriculumlist: ArrayList<CoCurriculum>, private val context: Context) : RecyclerView.Adapter<CoCurriculumAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = CocurriculumRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(appBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(cocurriculumlist[position]){
                cocurriculumRecyclerItemBinding.title.text = title
                Glide.with(context).load(vectorImages).into(cocurriculumRecyclerItemBinding.imgs)
            }
        }
    }

    override fun getItemCount(): Int {
        return cocurriculumlist.size
    }

    fun refreshList(cocurriculumlist: ArrayList<CoCurriculum>) {
        this.cocurriculumlist = cocurriculumlist
        notifyDataSetChanged()
    }

    class ViewHolder(val cocurriculumRecyclerItemBinding: CocurriculumRecyclerItemBinding) : RecyclerView.ViewHolder(cocurriculumRecyclerItemBinding.root)

}