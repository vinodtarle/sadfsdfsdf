package com.construction.app.base.utility.button

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.Button
import androidx.annotation.VisibleForTesting
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import androidx.core.widget.TextViewCompat
import com.construction.app.R

/**
 * A convenience class for creating an Action buttons as designed and defined by DesignSystem.
 *
 * This class can render buttons in 4 different ways based upon the ActionButtonType set while defining in XML.
 * ActionButtonType @param HighEmphasis, MediumEmphasis, LowEmphasis or Destructive
 */

class ActionButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : Button(context, attrs) {
    companion object {
        private var actionButtonType = 0
        private const val LINE_HEIGHT_MULTIPLIER_FOR_LOW_EMPHASIS = 1.26f
        private const val LINE_HEIGHT_MULTIPLIER_FOR_HIGH_EMPHASIS = 1.06f

    }

    enum class ActionButtonType {
        /**
         * Used to persuade the user to perform a desired action in decision making.
         */
        HighEmphasis,

        /**
         * Used if you don't want attract user to a specific action, but also don't want to be hidden from user.
         * It can also be used to create a neutral emphasis between 2 actions.
         */
        MediumEmphasis,

        /**
         * Used for less important actions, it should never take the role of a primary action on the page.
         */
        LowEmphasis,

        /**
         * Used to indicate the action will result loss of data or difficult to recover from.
         */
        Destructive
    }

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ActionButton, 0, 0).use {
            actionButtonType = it.getInteger(R.styleable.ActionButton_actionButtonType, 0)
        }
        isAllCaps = false
        setupStyle(ActionButtonType.values()[actionButtonType])
        stateListAnimator = null
    }

    /**
     * Called to render button in a any of the defined types.
     *
     * @param actionButtonType ActionButtonType to which state needs to be changed
     */
    fun setupStyle(actionButtonType: ActionButtonType) {
        when (actionButtonType) {
            ActionButtonType.HighEmphasis -> setupHighEmphasisButton()
            ActionButtonType.MediumEmphasis -> setupMediumEmphasisButton()
            ActionButtonType.LowEmphasis -> setupLowEmphasisButton()
            ActionButtonType.Destructive -> setupDestructiveButton()
        }
    }

    @VisibleForTesting
    fun getActionButtonType() = ActionButtonType.values()[actionButtonType]

    private fun setupHighEmphasisButton() {
        setBackgroundResource(R.drawable.action_button_high_emphasis_bg)
        setTextColor(
            ContextCompat.getColorStateList(
                context,
                R.color.interactiveInverseForegroundNormal
            )
        )
        setHighAndMediumEmphasisAppearance()
    }

    private fun setHighAndMediumEmphasisAppearance() {
        TextViewCompat.setTextAppearance(this, R.style.font_emphasis16)
        setLineSpacing(0f, LINE_HEIGHT_MULTIPLIER_FOR_HIGH_EMPHASIS)
        setPadding(
            resources.getDimensionPixelSize(R.dimen.spacing2x),
            resources.getDimensionPixelSize(R.dimen.spacing2x),
            resources.getDimensionPixelSize(R.dimen.spacing2x),
            resources.getDimensionPixelSize(R.dimen.spacing2x)
        )
        minHeight = resources.getDimensionPixelSize(R.dimen.buttonMinHeightHighEmphasis)
        minWidth = resources.getDimensionPixelSize(R.dimen.buttonMinWidthHighEmphasis)
        gravity = Gravity.CENTER
    }

    private fun setupMediumEmphasisButton() {
        setBackgroundResource(R.drawable.action_button_medium_emphasis_bg)
        setTextColor(
            ContextCompat.getColorStateList(
                context,
                R.color.action_button_medium_emphasis_label_color
            )
        )
        setHighAndMediumEmphasisAppearance()
    }

    private fun setupLowEmphasisButton() {
        setTextColor(
            ContextCompat.getColorStateList(
                context,
                R.color.action_button_low_emphasis_label_color
            )
        )
        setLowEmphasisAppearance()
    }

    private fun setLowEmphasisAppearance() {
        background = null
        TextViewCompat.setTextAppearance(this, R.style.font_essential16)
        setLineSpacing(0f, LINE_HEIGHT_MULTIPLIER_FOR_LOW_EMPHASIS)
        setPadding(
            resources.getDimensionPixelSize(R.dimen.zero_dp),
            resources.getDimensionPixelSize(R.dimen.spacing2x),
            resources.getDimensionPixelSize(R.dimen.zero_dp),
            resources.getDimensionPixelSize(R.dimen.spacing2x)
        )
        minHeight = resources.getDimensionPixelSize(R.dimen.buttonMinHeightText)
        minimumWidth = resources.getDimensionPixelSize(R.dimen.buttonMinWidthText)
        minWidth = resources.getDimensionPixelSize(R.dimen.buttonMinWidthText)
        gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
    }

    private fun setupDestructiveButton() {
        setTextColor(
            ContextCompat.getColorStateList(
                context,
                R.color.action_button_destructive_label_color
            )
        )
        setLowEmphasisAppearance()
    }
}