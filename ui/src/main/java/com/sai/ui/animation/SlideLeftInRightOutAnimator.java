//package com.sai.ui.animation;
//
//import android.animation.Animator;
//import android.animation.AnimatorSet;
//import android.animation.ObjectAnimator;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.animation.AccelerateDecelerateInterpolator;
//
//public class SlideLeftInRightOutAnimator extends ListItemAnimator {
//    @Override
//    public void animateAddImpl(final RecyclerView.ViewHolder holder) {
//        View target = holder.itemView;
//        target.setPivotX(target.getMeasuredWidth() / 2);
//        target.setPivotY(target.getMeasuredHeight() / 2);
//
//        AnimatorSet animator = new AnimatorSet();
//
//        animator.playTogether(
//                ObjectAnimator.ofFloat(target, "translationX", -target.getMeasuredWidth(), 0.0f),
//                ObjectAnimator.ofFloat(target, "alpha", target.getAlpha(), 1.0f)
//        );
//
//        animator.setTarget(target);
//        animator.setDuration(animationDuration);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.setStartDelay((animationDuration * holder.getPosition()) / 10);
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                mPendingAdd.remove(holder);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        animator.start();
//    }
//
//    @Override
//    public void animateRemoveImpl(RecyclerView.ViewHolder holder) {
//        View target = holder.itemView;
//        target.setPivotX(target.getMeasuredWidth() / 2);
//        target.setPivotY(target.getMeasuredHeight() / 2);
//
//        AnimatorSet animator = new AnimatorSet();
//
//        animator.playTogether(
//                ObjectAnimator.ofFloat(target, "translationX", 0.0f, target.getMeasuredWidth()),
//                ObjectAnimator.ofFloat(target, "alpha", target.getAlpha(), 0.0f)
//        );
//
//        animator.setTarget(target);
//        animator.setDuration(animationDuration);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.setStartDelay((animationDuration * holder.getAdapterPosition()) / 10);
//        animator.start();
//    }
//}
