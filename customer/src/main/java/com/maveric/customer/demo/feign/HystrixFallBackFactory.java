package com.maveric.customer.demo.feign;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

public class HystrixFallBackFactory implements FallbackFactory<AccountFeign> {
    private static Logger logger = LoggerFactory.getLogger(HystrixFallBackFactory.class);
    @Override
    public AccountFeign create(Throwable cause) {
logger.info("fallback; reason was: {}" , cause.getMessage());

            return null;

    }

}

