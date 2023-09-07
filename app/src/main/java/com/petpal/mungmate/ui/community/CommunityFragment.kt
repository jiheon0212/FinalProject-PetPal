package com.petpal.mungmate.ui.community

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.faltenreich.skeletonlayout.createSkeleton
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.petpal.mungmate.R
import com.petpal.mungmate.databinding.FragmentCommunityBinding


class CommunityFragment : Fragment() {

    lateinit var communityBinding: FragmentCommunityBinding
    private lateinit var skeleton: Skeleton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        communityBinding = FragmentCommunityBinding.inflate(inflater)

        bottomNavigationViewVISIBLE()
        communityBinding.run {



            communityRecyclerView()
            communityPostWritingFab.setOnClickListener {
              it.findNavController().navigate(R.id.action_item_community_to_communityWritingFragment)
            }

        }

        return communityBinding.root
    }

    private fun bottomNavigationViewVISIBLE() {
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.visibility = View.VISIBLE
    }

    private fun FragmentCommunityBinding.communityRecyclerView() {
        communityPostRecyclerView.run {

            val communityAdapter = CommunityAdapter(requireContext())
            adapter = communityAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            skeleton = applySkeleton(R.layout.row_community, 10).apply { showSkeleton() }

            Handler(Looper.getMainLooper()).postDelayed({
                skeleton.showOriginal()
            }, 3000)
        }
    }

}