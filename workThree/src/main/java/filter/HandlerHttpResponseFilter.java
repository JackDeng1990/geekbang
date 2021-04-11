package filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author admin
 */
public class HandlerHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("Content-Type", "application/json");
        response.headers().setInt("Content-Length", response.content().readableBytes());
        response.headers().set("study","day day up");
    }
}
