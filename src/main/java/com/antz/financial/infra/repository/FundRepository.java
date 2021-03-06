package com.antz.financial.infra.repository;

import com.antz.financial.domain.Fund;
import com.antz.financial.infra.repository.mongo.FundMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author antz-H
 * @description
 * @date 2020/2/22 21:45
 **/
@Repository
public class FundRepository {

    @Autowired
    private FundMongoRepository fundMongoRepository;

    public long saveAll(List<Fund> funds) {
        return fundMongoRepository.saveAll(funds).size();
    }

    public List<Fund> fundALLOf() {
        return fundMongoRepository.findAll();
    }

    public Optional<Fund> fundOfByCode(String fundCode) {
        return fundMongoRepository.findById(fundCode);
    }

    public Page<Fund> fundOfByPage(Fund fund, Pageable pageable) {
        Example<Fund> fundExample = Example.of(fund);
        return fundMongoRepository.findAll(fundExample, pageable);
    }


}
