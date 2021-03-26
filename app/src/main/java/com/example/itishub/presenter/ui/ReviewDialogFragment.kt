package com.example.itishub.presenter.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.itishub.R
import com.example.itishub.databinding.DialogReviewBinding

class ReviewDialogFragment : DialogFragment() {

    private var positiveAction: (String, String) -> Unit = { _, _ -> }
    private var negativeAction: () -> Unit = { }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogReviewBinding.inflate(
            LayoutInflater.from(context),
            null,
            false
        )
        binding.etReview.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onPositiveClick(binding)
                dismiss()
                true
            } else false
        }
        return AlertDialog.Builder(requireContext())
            .setPositiveButton(resources.getString(R.string.dialog_send)) { _, _ ->
                onPositiveClick(binding)
            }
            .setNegativeButton(resources.getString(R.string.dialog_cancel)) { _, _ ->
                onNegativeClick()
            }
            .setView(binding.root)
            .create()
    }

    private fun onPositiveClick(binding: DialogReviewBinding) {
        positiveAction.invoke(
            binding.etEmail.text.toString(),
            binding.etReview.text.toString()
        )
    }

    private fun onNegativeClick() {
        negativeAction.invoke()
    }

    companion object {
        fun show(
            fragmentManager: FragmentManager,
            positiveAction: (String, String) -> Unit,
            negativeAction: () -> Unit
        ) = ReviewDialogFragment().also {
            it.positiveAction = positiveAction
            it.negativeAction = negativeAction
            it.show(
                fragmentManager,
                ReviewDialogFragment::class.java.name
            )
        }
    }
}