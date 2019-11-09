package com.example.fragmentandrecyclerviewdemo_kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragmentandrecyclerviewdemo_kotlin.R

class TextFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_text, container, false)
        val textView: TextView = root.findViewById(R.id.text)
        textView.setText(R.string.text_fragment)
        return root
    }
}