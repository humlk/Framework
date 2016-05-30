package com.sai.net.http;

import java.io.IOException;
import java.util.Map;


public interface HttpStack {

    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders)
        throws IOException;

}
