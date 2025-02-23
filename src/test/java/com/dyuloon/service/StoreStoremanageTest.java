package com.dyuloon.service;

import com.dyuloon.from.SearchForm;
import com.dyuloon.vo.PageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreStoremanageTest {

    @Autowired
    private StoreStoremanageService storeStoremanageService;

    @Test
    void testQueryStore() {
        SearchForm searchForm = new SearchForm();
        searchForm.setKey("storemanage_name");
        searchForm.setValue("asd");
        searchForm.setUserIdentity(1);
        searchForm.setPageNum(1);
        searchForm.setPageSize(10);
        PageVO pageVO = storeStoremanageService.queryStore(searchForm);
        System.out.println(pageVO);
    }

}
