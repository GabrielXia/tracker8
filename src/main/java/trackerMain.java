import com.snowplowanalytics.snowplow.tracker.DevicePlatform;
import com.snowplowanalytics.snowplow.tracker.events.Event;

/**
 */
public class trackerMain {

    public static void main(String[] args) {

        // for now the collectorUrl runs on AWS
        String collectorUrl = "http://54.202.93.242:8080";

        Event osEvent = EventsUtils.getOsEvent();

        ReportMetric.reportMetric(collectorUrl, "terasology", DevicePlatform.Desktop, osEvent);
    }
}
