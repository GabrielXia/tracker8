import com.snowplowanalytics.snowplow.tracker.DevicePlatform;
import com.snowplowanalytics.snowplow.tracker.events.Event;
import com.snowplowanalytics.snowplow.tracker.events.ScreenView;

/**
 */
public class trackerMain {

    public static void main(String[] args) {

        // for now the collectorUrl runs on AWS
        String collectorUrl = "http://54.202.97.104:8080";
        Event screenView = ScreenView.builder()
                .name("name")
                .id("id")
                .build();

        ReportMetric.reportMetric(collectorUrl, "terasology", DevicePlatform.Desktop, screenView);
    }
}
