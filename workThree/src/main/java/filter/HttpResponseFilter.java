package filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author admin
 */
public interface HttpResponseFilter {
    void filter(FullHttpResponse fullHttpResponse);
}
