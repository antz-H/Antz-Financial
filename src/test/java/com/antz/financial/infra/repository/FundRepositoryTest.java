package com.antz.financial.infra.repository;

import com.antz.financial.adapter.endpoint.FundEndpoint;
import com.antz.financial.domain.Fund;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FundRepositoryTest {

    @Autowired
    private FundRepository fundRepository;

    @Test
    public void testFundByPageOf() {
        Fund fund = Fund.builder().code("000001").build();

        Pageable pageRequest = PageRequest.of(
                Integer.valueOf(FundEndpoint.DEFAULE_PAGE),
                Integer.valueOf(FundEndpoint.DEFAULE_PAGESIZE));

        Page<Fund> fundPage = fundRepository.fundByPageOf(fund, pageRequest);
        Assertions.assertThat(fundPage.isEmpty()).isFalse();
    }

}