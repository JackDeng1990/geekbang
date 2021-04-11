import inbound.NettyHttpServer;

/**
 * @author admin
 */
public class NettyServerApplication {
    public final static int port = 8888;

    public static void main(String[] args) {

        NettyHttpServer server = new NettyHttpServer(port, "http://localhost:8801");

        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
