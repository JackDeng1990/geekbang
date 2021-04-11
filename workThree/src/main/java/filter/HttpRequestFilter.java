package filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author admin
 */
public interface HttpRequestFilter {

    void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx);
}
