package com.example.itishub.utils

import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment

class FragmentExt {
    companion object {
        fun Fragment.waitForTransition(targetView: View) {
            postponeEnterTransition()
            targetView.doOnPreDraw { startPostponedEnterTransition() }
        }
    }
}