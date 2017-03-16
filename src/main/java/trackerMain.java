import com.snowplowanalytics.snowplow.tracker.DevicePlatform;
import com.snowplowanalytics.snowplow.tracker.events.Event;
import com.snowplowanalytics.snowplow.tracker.events.PageView;
import com.snowplowanalytics.snowplow.tracker.events.ScreenView;
import com.snowplowanalytics.snowplow.tracker.events.Timing;

/**
 */
public class trackerMain {

    public static void main(String[] args) {

        // for now the collectorUrl runs on AWS
        String collectorUrl = "http://54.202.224.47:8080";
        Event screenEvent = ScreenView.builder()
                .name("xia")
                .id("jin")
                .build();

        Event osEvent = EventsUtils.getOsEvent();

        Event timing = Timing.builder()
                .category("ca")
                .timing(10)
                .variable("va")
                .build();

        Event pageView = PageView.builder()
                .pageTitle("title")
                .pageUrl("url")
                .referrer("ref")
                .build();

        ReportMetric.reportMetric(collectorUrl, "terasology", DevicePlatform.Desktop, pageView);
    }
}
