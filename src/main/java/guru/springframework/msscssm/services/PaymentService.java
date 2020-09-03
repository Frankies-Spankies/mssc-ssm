package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.Payment;
import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import org.springframework.statemachine.StateMachine;

/*
* Interfaz que funciona como el cabezal del state machine,o almenos para interactuar con el cabezal
* Payment llevara el estado en BD del state machine
* Se ocupa un state machine por aplicacion o en este caso por logica, en este caso la solucion de un sistema de pagos
* se modela con unstate machine
*/

public interface PaymentService {

    Payment newPayment(Payment payment);

    StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId);
}
