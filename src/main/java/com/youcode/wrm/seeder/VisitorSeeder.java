package com.youcode.wrm.seeder;

import com.youcode.wrm.entity.Visitor;
import com.youcode.wrm.entity.Visit;
import com.youcode.wrm.repository.VisitorRepository;
import com.youcode.wrm.repository.VisitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VisitorSeeder {

    private VisitorRepository visitorRepository;

    private VisitRepository visitRepository;

    @Transactional
    public void seed() {
        // Créer quelques visiteurs
        Visitor visitor1 = new Visitor();
        visitor1.setName("John Doe");

        Visitor visitor2 = new Visitor();
        visitor2.setName("Jane Smith");


        // Sauvegarder les entités dans la base de données
        List<Visitor> visitors = Arrays.asList(visitor1, visitor2);
        visitorRepository.saveAll(visitors);

    }
}
