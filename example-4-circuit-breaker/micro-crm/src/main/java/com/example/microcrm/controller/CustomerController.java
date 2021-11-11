package com.example.microcrm.controller;

import com.example.microcrm.pojo.Customer;
import com.example.microcrm.service.CustomerService;
import com.example.microcrm.service.CustomerService2;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerService2 customerService2;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private Retry retry = Retry.ofDefaults("backendService");
    private CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("backendService");
    private ThreadPoolBulkhead threadPoolBulkhead = ThreadPoolBulkhead.ofDefaults("backendService");
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
    private ScheduledExecutorService scheduledExecutorService2 = Executors.newScheduledThreadPool(3);
    private TimeLimiter timeLimiter = TimeLimiter.of(Duration.ofSeconds(1));

    public CustomerController(CustomerService customerService, CustomerService2 customerService2) {
        this.customerService = customerService;
        this.customerService2 = customerService2;
    }

    @GetMapping("/customers/{id}")
    public Future<Optional<Customer>> customer(@PathVariable int id) {
        log.info("Called CustomerController::customer, customerId = {}", id);
//        return customerService2.customer(id);
        return Decorators.ofSupplier(() -> customerService.findOne(id))
                .withThreadPoolBulkhead(threadPoolBulkhead)
                .withTimeLimiter(timeLimiter, scheduledExecutorService)
//                .withRetry(retry, scheduledExecutorService2)
                .withCircuitBreaker(circuitBreaker)
                .withFallback(throwable -> Optional.of(new Customer(0, null, null)))
                .get()
                .toCompletableFuture();
    }

}
