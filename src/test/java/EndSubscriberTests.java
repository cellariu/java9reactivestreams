
import org.junit.jupiter.api.Test;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.* ;

import java.util.List;

import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class EndSubscriberTests {

    @Test
    public void whenSubscribeToItThenShouldConsumeAll() {

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        EndSubscriber<String> subscriber = new EndSubscriber<>();
        publisher.subscribe(subscriber);

        List<String> items = List.of("1", "x", "2", "x", "3", "x");
        assertEquals(1, publisher.getNumberOfSubscribers());
        items.forEach(publisher::submit);
        publisher.close();

        await().atMost(1000, TimeUnit.MILLISECONDS)
                .until(() -> subscriber.getConsumedElements().containsAll(items));
    }

}
