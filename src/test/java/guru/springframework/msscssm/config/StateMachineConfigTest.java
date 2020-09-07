package guru.springframework.msscssm.config;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;
import java.util.stream.Stream;

@Slf4j
@SpringBootTest
class StateMachineConfigTest {

    @Autowired
    private StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Test
    void testNewStateMachine() {
        StateMachine<PaymentState, PaymentEvent> sm = factory.getStateMachine(UUID.randomUUID());

        sm.start();
        print(sm);

        Stream.of(PaymentEvent.PRE_AUTHORIZE,PaymentEvent.PRE_AUTH_APPROVED, PaymentEvent.PRE_AUTH_DECLINED).forEach(i -> {
            sm.sendEvent(i);
            print(sm);
        });

    }

    private void print(StateMachine<PaymentState, PaymentEvent> sm) {
        System.out.println(sm.getState().toString());
    }
}