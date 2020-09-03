package guru.springframework.msscssm.config.actions;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import guru.springframework.msscssm.services.PaymentServiceImpl;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PreAuthAction implements Action<PaymentState, PaymentEvent> {

    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> context) {
        System.out.println("preAuthAction was call!");

        if (new Random().nextInt(10) < 8) {
            context.getStateMachine()
                    .sendEvent(MessageBuilder
                            .withPayload(PaymentEvent.PRE_AUTH_APPROVED)
                            .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                            .build());
            System.out.println("PRE AUTH APPROVED !!");
        } else {
            context.getStateMachine()
                    .sendEvent(MessageBuilder
                            .withPayload(PaymentEvent.PRE_AUTH_DECLINED)
                            .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                            .build());
            System.out.println("PRE AUTH DECLINED !!");
        }
    }

    ;

}
