package com.sai.net.http;

import android.os.Handler;

import com.sai.net.exception.BaseException;

import java.util.concurrent.Executor;

public class ExecutorDelivery implements ResponseDelivery {
    private final Executor mResponsePoster;

    public ExecutorDelivery(final Handler handler) {
        mResponsePoster = new Executor() {
            @Override
            public void execute(Runnable command) {
                handler.post(command);
            }
        };
    }

    public ExecutorDelivery(Executor executor) {
        mResponsePoster = executor;
    }

    @Override
    public void postResponse(Request<?> request, Response<?> response) {
        request.markDelivered();
        mResponsePoster.execute(new ResponseDeliveryRunnable(request, response));
    }


    @Override
    public void postError(Request<?> request, BaseException error) {
        Response<?> response = Response.error(error);
        mResponsePoster.execute(new ResponseDeliveryRunnable(request, response));
    }

    @Override
    public void postCancel(Request<?> request) {
        mResponsePoster.execute(new ResponseDeliveryRunnable(request, null));
    }

    @Override
    public void postProgress(final Request<?> request, final long downloadSize, final long totalFileSize) {
        mResponsePoster.execute(new Runnable() {
            @Override
            public void run() {
                request.deliverProgress(downloadSize, totalFileSize);
            }
        });
    }

    private class ResponseDeliveryRunnable implements Runnable {
        private final Request mRequest;
        private final Response mResponse;

        public ResponseDeliveryRunnable(Request request, Response response) {
            mRequest = request;
            mResponse = response;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void run() {
            //取消
            if (mRequest.isCanceled()) {
                mRequest.deliverCancel();
                mRequest.deliverFinish();
                mRequest.finish();
                return;
            }

            //成功
            if (mResponse.isSuccess()) {
                mRequest.deliverResponse(mResponse.result);
            } else {
                //失败
                mRequest.deliverError(mResponse.error);
            }
            mRequest.deliverFinish();
            //完成
            mRequest.finish();
       }
    }
}
