package com.dyuloon.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.entity.IndexNotes;
import com.dyuloon.entity.MenuManageEntity.levelOneList;
import com.dyuloon.from.SearchForm;
import com.dyuloon.service.IndexNotesService;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dyuloon
 * @since 2023-05-06
 */
@RestController
@RequestMapping("/indexNotes")
public class IndexNotesController {

    @Autowired
    private IndexNotesService indexNotesService;

    // 增
    @PostMapping()
    public ResultVO saveNotesList(@RequestBody IndexNotes indexNotes){
        boolean save = this.indexNotesService.save(indexNotes);
        ResultVO resultVO = save ? ResultVOUtil.success(null,"新增成功！"):ResultVOUtil.error("新增失败！");
        return resultVO;
    }

    // 删除
    @DeleteMapping("{id}")
    public ResultVO delNotes(@PathVariable Integer id){
        boolean del = this.indexNotesService.removeById(id);
        ResultVO resultVO = del ? ResultVOUtil.success(null,"删除成功！"):ResultVOUtil.error("删除失败！");
        return resultVO;
    }

    // 改
    @PutMapping()
    public ResultVO updateNotesList(@RequestBody IndexNotes indexNotes){
        boolean update = this.indexNotesService.updateById(indexNotes);
        ResultVO resultVO = update ? ResultVOUtil.success(null,"修改成功！"):ResultVOUtil.error("修改失败！");
        return resultVO;
    }

    // 查找
    @GetMapping()
    public PageVO getNotesList(SearchForm searchForm){
        PageVO pageVO = this.indexNotesService.getNotesList(searchForm);
        return pageVO;
    }

}

