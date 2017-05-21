import com.snowplowanalytics.snowplow.tracker.DevicePlatform;
import com.snowplowanalytics.snowplow.tracker.events.Event;
import com.snowplowanalytics.snowplow.tracker.events.PageView;
import com.snowplowanalytics.snowplow.tracker.events.ScreenView;

/**
 * This is an client example tracking metric and sending them to the telemetry system
 */
public class TrackerMain {

    public static void main(String[] args) {

        // For now the collectorUrl runs on a docker container
        String collectorUrl = "http://localhost:8080";

        Event osEvent = EventsUtils.getOsEvent();

        Event pageView = PageView.builder()
                .referrer("1")
                .pageTitle("2")
                .pageUrl("3")
                .build();

        Event screenEvent = ScreenView.builder()
                .id("id")
                .name("name")
                .build();

        ReportMetric.reportMetric(collectorUrl, "terasology", DevicePlatform.Desktop, screenEvent);
        ReportMetric.reportMetric(collectorUrl, "terasology", DevicePlatform.Desktop, pageView);

        //if osEvent is not registered, this is a bad event in Elasticsearch
        ReportMetric.reportMetric(collectorUrl, "terasology", DevicePlatform.Desktop, osEvent);


    }
}
