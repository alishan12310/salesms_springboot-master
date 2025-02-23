package com.dyuloon.service;

import com.dyuloon.entity.IndexNotes;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyuloon.from.SearchForm;
import com.dyuloon.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dyuloon
 * @since 2023-05-06
 */
public interface IndexNotesService extends IService<IndexNotes> {

    PageVO getNotesList(SearchForm searchForm);
}
