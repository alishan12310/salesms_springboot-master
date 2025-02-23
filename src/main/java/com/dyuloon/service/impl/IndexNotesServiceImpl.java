package com.dyuloon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.entity.IndexNotes;
import com.dyuloon.entity.StoreStoremanage;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.IndexNotesMapper;
import com.dyuloon.service.IndexNotesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyuloon.util.PageVOUtil;
import com.dyuloon.vo.PageVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dyuloon
 * @since 2023-05-06
 */
@Service
public class IndexNotesServiceImpl extends ServiceImpl<IndexNotesMapper, IndexNotes> implements IndexNotesService {

    @Override
    public PageVO getNotesList(SearchForm searchForm) {
        Page storePage = new Page(searchForm.getPageNum(), searchForm.getPageSize());
        QueryWrapper<IndexNotes> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(searchForm.getValue()), searchForm.getKey(), searchForm.getValue());
        this.baseMapper.selectPage(storePage, queryWrapper.lambda().orderByDesc(IndexNotes::getNotesId));
        PageVO pageVO = PageVOUtil.success(storePage.getRecords(), "查询成功！", storePage.getTotal(), storePage.getCurrent(), storePage.getSize());
        return pageVO;
    }
}
