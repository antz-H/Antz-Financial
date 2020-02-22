package com.antz.financial.domain.core;

import lombok.Data;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

/**
 * @author antz-H
 * @description
 * @date 2020/2/22 23:08
 **/
@Data
public abstract class AbstractEntity {

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    @Version
    private long verion;
}
