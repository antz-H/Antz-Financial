package com.antz.financial.infra.configuration;

import com.antz.financial.domain.core.AbstractEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.time.LocalDateTime;

/**
 * @author antz-H
 * @description
 * @date 2020/2/22 23:04
 **/
@Configuration
public class MongoCreatedTimeAndLastModifiedTimeEventListener extends AbstractMongoEventListener {

    @Override
    public void onBeforeConvert(BeforeConvertEvent event) {
        Object source = event.getSource();
        if (source instanceof AbstractEntity) {
            LocalDateTime now = LocalDateTime.now();
            AbstractEntity entity = AbstractEntity.class.cast(source);
            entity.setLastModifiedDate(now);
            if (entity.getCreatedDate() == null) {
                entity.setCreatedDate(now);
            }
        }
    }
}
