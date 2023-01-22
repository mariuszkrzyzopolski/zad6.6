import org.easymock.*;
import org.example.Client;
import org.example.Message;
import org.example.RaceResultService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
@ExtendWith({EasyMockExtension.class})
public class TestEasymockRaceResultService {
    private EasyMockSupport support = new EasyMockSupport();
    @Mock
    Message message;

    @TestSubject
    RaceResultService raceResult;
    @Mock
    Client clientMock;

    @Mock
    Client secondClientMock;


    @Test
    void testSendMessage() {
        support.replayAll();
        raceResult.addSubscriber(clientMock);
        raceResult.send(message);
        clientMock.receive(message);
        support.verifyAll();

    }

    @Test
    void testRemoveSubscriber() {
        support.replayAll();
        raceResult.addSubscriber(clientMock);
        raceResult.addSubscriber(secondClientMock);
        raceResult.send(message);
        clientMock.receive(message);
        support.verifyAll();
    }

    @Test
    void testSendTwoMessages() {
        support.replayAll();
        raceResult.addSubscriber(clientMock);
        raceResult.addSubscriber(secondClientMock);
        raceResult.send(message);
        raceResult.send(message);
        clientMock.receive(message);
        secondClientMock.receive(message);
        support.verifyAll();
    }
}
