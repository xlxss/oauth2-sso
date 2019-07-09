package xiao.xss.study.demo.oauth2.sso.auth.server.common;

/**
 *
 * @author xiaoliang
 * @since 2019-07-09 11:50
 */
public class AuthServer {
    public static final long SERIAL_VERSION_UID = 1L;
    private static final String SERVER_VERSION = "V0.1";

    public static void showVersion() {
        System.out.format("\33[%d;1mAuth-Server: %s%n\33[0;39m", 36, SERVER_VERSION);
    }
}
