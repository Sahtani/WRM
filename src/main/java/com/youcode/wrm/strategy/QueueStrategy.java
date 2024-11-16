package com.youcode.wrm.strategy;

import com.youcode.wrm.entity.Visit;

import java.util.List;

public interface QueueStrategy {
    Visit getNextVisit(List<Visit> waitingVisits);
}