package com.ztktsn.moviego.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.ztktsn.moviego.R
import com.ztktsn.moviego.databinding.FragmentDetailedBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [detailedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detailedFragment : Fragment() {
    class MovieDetailsFragment : Fragment() {

        private var _binding: FragmentDetailedBinding? = null
        private val binding get() = _binding!!

//        private val args by navArgs<DetailedFragmentArgs>()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentDetailedBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

//            binding.movieTitle.text = args.movieTitle

//            binding.button.setOnClickListener {
//                val movieTitle = binding.editText.text.toString()
//                setFragmentResult("MovieTitleResult", bundleOf("bundleKey" to movieTitle))
//                requireActivity().onBackPressed()
//            }
        }
    }
}