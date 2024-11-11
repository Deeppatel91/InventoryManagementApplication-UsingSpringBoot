package ca.gbc.orderservice.controller;

import ca.gbc.orderservice.dto.OrderRequest;
import ca.gbc.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        logger.info("Received order request: {}", orderRequest);
        try {
            orderService.placeOrder(orderRequest);
            return "Order placed successfully";
        } catch (Exception e) {
            logger.error("Error while placing order", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Order placement failed", e);
        }
    }
}
