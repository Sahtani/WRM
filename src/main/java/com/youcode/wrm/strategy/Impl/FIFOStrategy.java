package com.youcode.wrm.strategy.Impl;

import com.youcode.wrm.entity.Visit;
import com.youcode.wrm.strategy.QueueStrategy;

import java.util.Comparator;
import java.util.List;

public class FIFOStrategy implements QueueStrategy {
    @Override
    public Visit getNextVisit(List<Visit> waitingVisits) {
        return waitingVisits.stream()
                .min(Comparator.comparing(Visit::getArrivalTime))
                .orElse(null);
    }
}
