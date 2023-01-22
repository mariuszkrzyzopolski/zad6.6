import org.example.Client;
import org.example.Message;
import org.example.RaceResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class TestMockitoRaceResultService {
    Message message;
    RaceResultService raceResult;
    Client clientMock;
    @BeforeEach
    void setup(){
        message = new Message();
        raceResult = new RaceResultService();
        clientMock = mock(Client.class);
    }


    @Test
    void testSendMessage() {
        raceResult.addSubscriber(clientMock);
        raceResult.send(message);
        verify(clientMock, times(1)).receive(any());
    }

    @Test
    void testRemoveSubscriber() {
        raceResult.addSubscriber(clientMock);
        raceResult.removeSubscriber(clientMock);
        verify(clientMock, times(0)).receive(any());
    }

    @Test
    void testSendTwoMessages() {
        raceResult.addSubscriber(clientMock);
        Client secondClientMock = mock(Client.class);
        raceResult.addSubscriber(secondClientMock);
        raceResult.send(message);
        verify(clientMock, times(1)).receive(any());
        verify(secondClientMock, times(1)).receive(any());
    }

}
