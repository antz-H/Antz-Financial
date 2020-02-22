package com.antz.financial.infra.repository.mongo;

import com.antz.financial.domain.Fund;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author antz-H
 * @description
 * @date 2020/2/22 21:47
 **/

public interface FundMongoRepository extends MongoRepository<Fund, String> {
}
