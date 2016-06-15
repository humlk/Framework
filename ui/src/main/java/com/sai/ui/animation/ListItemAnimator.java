//package com.sai.ui.animation;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.animation.ObjectAnimator;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by huajie-work on 2015/7/25.
// * listviewitem动画
// */
//public abstract class ListItemAnimator extends RecyclerView.ItemAnimator{
//    List<RecyclerView.ViewHolder> mPendingAdd = new ArrayList<RecyclerView.ViewHolder>();
//    List<RecyclerView.ViewHolder> mPendingRemove = new ArrayList<RecyclerView.ViewHolder>();
//    List<MoveInfo> mPendingMove = new ArrayList<MoveInfo>();
//    int animationDuration = 500;
//    /**
//     *
//     */
//    @Override
//    public void runPendingAnimations() {
//
//        if (!mPendingRemove.isEmpty()) {
//            for (final RecyclerView.ViewHolder viewHolder : mPendingRemove) {
//                animateRemoveImpl(viewHolder);
//            }
//            mPendingRemove.clear();
//        }
//
//        if (!mPendingMove.isEmpty()) {
//            for(MoveInfo info : mPendingMove) {
//                animateMoveImpl(info);
//            }
//            mPendingMove.clear();
//        }
//
//        if (!mPendingAdd.isEmpty()) {
//            for (final RecyclerView.ViewHolder viewHolder : mPendingAdd) {
//                animateAddImpl(viewHolder);
//            }
//            mPendingAdd.clear();
//        }
//
//    }
//
//    @Override
//    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
//        mPendingRemove.add(viewHolder);
//        return true;
//    }
//
//    @Override
//    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
//        viewHolder.itemView.setAlpha(0.0f);
//        mPendingAdd.add(viewHolder);
//        return true;
//    }
//
//    @Override
//    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int fromX, int fromY, int toX, int toY) {
//        View view = viewHolder.itemView;
//        fromY += view.getTranslationY();
//        int delta = toY - fromY;
//        view.setTranslationY(-delta);
//        MoveInfo info = new MoveInfo(viewHolder, fromX, fromY, toX, toY);
//        mPendingMove.add(info);
//        return true;
//    }
//
//    @Override
//    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
//        return false;
//    }
//
//    @Override
//    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
//    }
//
//    @Override
//    public void endAnimations() {
//    }
//
//    @Override
//    public boolean isRunning() {
//        return !mPendingAdd.isEmpty() || !mPendingRemove.isEmpty();
//    }
//
//    public abstract void animateAddImpl(final RecyclerView.ViewHolder holder);
//    public abstract void animateRemoveImpl(final RecyclerView.ViewHolder holder);
//
//    public void animateMoveImpl(final MoveInfo moveInfo){
//        final View view = moveInfo.holder.itemView;
//        ObjectAnimator animator = ObjectAnimator.ofFloat(view,
//                "translationY", view.getTranslationY(), 0);
//        animator.setDuration(animationDuration);
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                dispatchMoveStarting(moveInfo.holder);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                dispatchMoveFinished(moveInfo.holder);
//                if(!isRunning()) dispatchAnimationsFinished();
//            }
//        });
//        animator.start();
//    };
//
//    class MoveInfo{
//        private RecyclerView.ViewHolder holder;
//        private int fromX;
//        private int fromY;
//        private int toX;
//        private int toY;
//
//        public MoveInfo(RecyclerView.ViewHolder holder,
//                        int fromX, int fromY, int toX, int toY) {
//            this.holder = holder;
//            this.fromX = fromX;
//            this.fromY = fromY;
//            this.toX = toX;
//            this.toY = toY;
//        }
//    }
//}
