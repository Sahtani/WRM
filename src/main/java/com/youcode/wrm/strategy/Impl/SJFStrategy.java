package com.youcode.wrm.strategy.Impl;

import com.youcode.wrm.entity.Visit;
import com.youcode.wrm.strategy.QueueStrategy;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class SJFStrategy implements QueueStrategy {
    @Override
    public Visit getNextVisit(List<Visit> waitingVisits) {
        return waitingVisits.stream()
                .min(Comparator
                        .comparing(Visit::getEstimatedProcessingTime)
                        .thenComparing(Visit::getArrivalTime))
                .orElse(null);
    }
}