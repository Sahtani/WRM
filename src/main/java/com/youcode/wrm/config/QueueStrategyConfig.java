package com.youcode.wrm.config;// src/main/java/com/wrm/config/QueueStrategyConfig.java

import com.youcode.wrm.strategy.Impl.FIFOStrategy;
import com.youcode.wrm.strategy.Impl.PriorityStrategy;
import com.youcode.wrm.strategy.Impl.SJFStrategy;
import com.youcode.wrm.strategy.QueueStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueStrategyConfig {
    @Bean
    public Map<String, QueueStrategy> queueStrategies(
            FIFOStrategy fifoStrategy,
            PriorityStrategy priorityStrategy,
            SJFStrategy sjfStrategy
    ) {
        Map<String, QueueStrategy> strategies = new HashMap<>();
        strategies.put("fifo", fifoStrategy);
        strategies.put("priority", priorityStrategy);
        strategies.put("sjf", sjfStrategy);
        return strategies;
    }
}