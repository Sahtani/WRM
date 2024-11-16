package com.youcode.wrm.strategy.Impl;

import com.youcode.wrm.entity.Visit;
import com.youcode.wrm.strategy.QueueStrategy;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
@Component
public class PriorityStrategy implements QueueStrategy {
    @Override
    public Visit getNextVisit(List<Visit> waitingVisits) {
        return waitingVisits.stream()
                .max(Comparator
                        .comparing(Visit::getPriority)
                        .thenComparing(Visit::getArrivalTime))
                .orElse(null);
    }
}
