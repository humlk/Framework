//package com.sai.net.task;
//
//import com.sai.net.SaiContent;
//import com.sai.net.model.RequestResult;
//import com.sai.net.request.OnResponseListener;
//
//import java.lang.ref.WeakReference;
//
//public abstract class BaseTask{
//
//    private int mState = SaiContent.STATE_TASK_NULL;
//
//    private WeakReference<OnResponseListener> mTaskListener;
//    private RequestResult mResult;
//
//    protected void start(){
//        mState = SaiContent.STATE_TASK_RUNNING;
//        complateTask(SaiContent.STATE_TASK_RUNNING);
////        prepare();
//    }
//
////    protected void prepare(){
////        complateTask(SaiContent.STATE_TASK_STATE);
////    }
//
//    public void error(RequestResult result){
//        mResult = result;
//        complateTask(SaiContent.STATE_TASK_FAILED);
//    }
//
//    public void success(RequestResult result){
//        mResult = result;
//        complateTask(SaiContent.STATE_TASK_SUCCESS);
//    }
//
//    protected void cancel(){
//        complateTask(SaiContent.STATE_TASK_CANCEL);
//    }
//    protected void complate(){
//    }
//    private void complateTask(int state){
//
//        OnResponseListener listener = getCallBack();
//        if(listener == null){
//            return;
//        }
//        if(mState == SaiContent.STATE_TASK_COMPLATE){
//            return;
//        }
//
//        if(state == SaiContent.STATE_TASK_RUNNING){
//            listener.onStart();
//            return;
//        }else if(state == SaiContent.STATE_TASK_SUCCESS){
//            listener.onSuccess(mResult);
//        }else if(state == SaiContent.STATE_TASK_CANCEL){
//            listener.onCancel();
//        }else{
//            listener.onError(mResult);
//        }
//        mState = SaiContent.STATE_TASK_COMPLATE;
//
//        listener.onComplate(state,mResult);
//    }
//
//    public void addTaskListener(OnResponseListener mTaskListener){
//        this.mTaskListener = new WeakReference<OnResponseListener>(mTaskListener);
//    }
//
//    public int getState(){
//        return mState;
//    }
//
//    private OnResponseListener getCallBack(){
//        if (mTaskListener != null && mTaskListener.get() != null) {
//            return mTaskListener.get();
//        }
//        return null;
//    }
//}
