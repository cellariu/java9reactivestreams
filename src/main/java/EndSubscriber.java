import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;

public class EndSubscriber<String> implements Flow.Subscriber<String> {

    private Flow.Subscription subscription;

    private List<String> consumedElements = new LinkedList<>();

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        System.out.println("Got an item: " + item);
        consumedElements.add(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done!");
    }

    public List<String> getConsumedElements() {
        return consumedElements;
    }
}
