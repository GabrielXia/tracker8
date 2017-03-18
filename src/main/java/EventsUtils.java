import com.snowplowanalytics.snowplow.tracker.events.Unstructured;
import com.snowplowanalytics.snowplow.tracker.payload.SelfDescribingJson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XIAJin on 2017/3/16.
 */
public class EventsUtils {

    static final public String SCHEMA_OS = "iglu:org.terasology.telemetry/os/jsonschema/1-0-0";

    static public Unstructured getOsEvent() {

        Map<String, String> osData = new HashMap<String,String>();

        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArchitecture = System.getProperty("os.arch");
        osData.put("osName", osName);
        osData.put("osVersion", osVersion);
        osData.put("osArchitecture", osArchitecture);

        SelfDescribingJson osJson = new SelfDescribingJson(SCHEMA_OS, osData);
        Unstructured osEvent = Unstructured.builder()
                .eventData(osJson)
                .build();

        return osEvent;
    }
}
