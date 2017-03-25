import org.slf4j.LoggerFactory;

/**
 * Created by XIAJin on 2017/3/24.
 */
public class LogstashTest {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LogstashTest.class);

    public static void main(String[] args) {

        logger.debug("THIS IS DEBUG");
        logger.info("THIS IS INFO");
        logger.warn("THIS IS WARN");
        logger.error("THIS IS ERROR");

        try {
            Thread.sleep(1000);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
