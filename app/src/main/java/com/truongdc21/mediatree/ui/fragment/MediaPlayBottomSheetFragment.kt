package com.truongdc21.mediatree.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.truongdc21.mediatree.databinding.LayoutMediaplayBottomsheetFragmentBinding
import com.truongdc21.mediatree.ui.adapter.AdapterVPGMediaPlay
import com.truongdc21.mediatree.viewmodel.MediaSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaPlayBottomSheetFragment :
    BottomSheetDialogFragment(){

    private val binding by lazy { LayoutMediaplayBottomsheetFragmentBinding.inflate(layoutInflater) }
    private val adapterVPGMediaPlay by lazy { AdapterVPGMediaPlay(childFragmentManager, lifecycle) }
    private val mMediaSharedViewModel : MediaSharedViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetMediaplay = super.onCreateDialog(savedInstanceState)

        setUpDiaLog(bottomSheetMediaplay)
        setTitleTop()
        setUpViewPager(binding.vpgMediaPlay)
        TabLayoutMediator(binding.intoTabLayout, binding.vpgMediaPlay) { tab, position -> }.attach()
        binding.imgDown.setOnClickListener {
            bottomSheetMediaplay.dismiss()
        }
        return bottomSheetMediaplay
    }

    private fun setUpViewPager( viewPager2: ViewPager2) {
        viewPager2.adapter = adapterVPGMediaPlay
        viewPager2.setPageTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                if (position <= -1.0F || position >= 1.0F) {
                    page.setAlpha(0.0F)
                    page.setVisibility(View.GONE);
                } else if (position == 0.0F) {
                    page.setAlpha(1.0F)
                    page.setVisibility(View.VISIBLE)
                } else {
                    page.setAlpha(1.0F - Math.abs(position))
                    page.setTranslationX(-position * (page.getWidth() / 2))
                    page.setVisibility(View.VISIBLE)
                }
            }
        })
    }

    private fun setUpDiaLog(dialog: Dialog) {
        dialog.setContentView(binding.root)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout = bottomSheetDialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            )
            parentLayout?.let { bottomSheet ->
                val behaviour = BottomSheetBehavior.from(bottomSheet)
                val layoutParams = bottomSheet.layoutParams
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
                bottomSheet.layoutParams = layoutParams
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    private fun setTitleTop() {
        mMediaSharedViewModel.titleTop.observe(
            this@MediaPlayBottomSheetFragment
        ){
            binding.tvtitle.text = it
        }
    }
}
