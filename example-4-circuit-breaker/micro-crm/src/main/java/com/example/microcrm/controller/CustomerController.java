package com.example.microcrm.controller;

import com.example.microcrm.pojo.Customer;
import com.example.microcrm.service.CustomerService;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Supplier;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private Retry retry = Retry.ofDefaults("backendService");
    private CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("backendService");
    private Bulkhead bulkhead = Bulkhead.ofDefaults("backendService");
    private ThreadPoolBulkhead threadPoolBulkhead = ThreadPoolBulkhead.ofDefaults("backendService");
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
    private ScheduledExecutorService scheduledExecutorService2 = Executors.newScheduledThreadPool(3);
    private TimeLimiter timeLimiter = TimeLimiter.of(Duration.ofSeconds(1));

    @GetMapping("/customers/{id}")
    public Future<Optional<Customer>> customer(@PathVariable int id) {
        log.info("Called CustomerController::customer, customerId = {}", id);
//        return customerService.findOne(id);
        Supplier<Optional<Customer>> supplier = () -> customerService.findOne(id);

        Supplier<Optional<Customer>> decoratedSupplier = Decorators.ofSupplier(supplier)
                .withCircuitBreaker(circuitBreaker)
                .withBulkhead(bulkhead)
                .withRetry(retry)
                .decorate();

        return Decorators.ofSupplier(decoratedSupplier)
                .withThreadPoolBulkhead(threadPoolBulkhead)
                .withTimeLimiter(timeLimiter, scheduledExecutorService)
//                .withRetry(retry, scheduledExecutorService2)
                .withCircuitBreaker(circuitBreaker)
                .withFallback(Arrays.asList(TimeoutException.class,
                        CallNotPermittedException.class,
                        BulkheadFullException.class),
                        throwable -> Optional.of(new Customer(0, null, null)))
                .get()
                .toCompletableFuture();
    }

}
