package com.sai.base.exception;

import java.util.Collection;
import java.util.Vector;

public class BaseException extends Exception{

    private static final long serialVersionUID = 1L;

    private Exception mException;
    private Vector<String> errorList = new Vector<String>();
    
    public BaseException(String desc){
        errorList = new Vector<String>();
        errorList.add(desc);
    }
    
    public BaseException(Exception exception, String desc){
        this(desc);
        mException = exception;
    }
    
    public BaseException(BaseException parent, String desc){
        this(desc);
        errorList.addAll(((BaseException)parent).getErrorList());
        
        if (mException == null && errorList.size() == 2){
            mException = parent;
        } else{
            mException = parent.mException;
        }
    }
    
    /**
     * 获取错误描述表.
     * 
     * @return 错误描述表
     */
    public Collection<String> getErrorList(){
        return errorList;
    }
    
    /**
     * 返回错误描述.
     * 
     * @return 错误描述表中第一个错误描述，是最顶层的错误描述
     */
    public String getMessage(){
        return errorList.firstElement();
    }
    
    /**   
     *    
     */  
    @Override
    public void printStackTrace() {
        if(mException != null){
            mException.printStackTrace();
        }else{
            super.printStackTrace();
        }
    }
}
