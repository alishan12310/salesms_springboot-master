package com.dyuloon.util;

import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;

import java.util.List;

public class PageVOUtil {

    public static PageVO success(List list, String msg, long total, long pageNum, long pageSize){
        PageVO pageVO = new PageVO();
        pageVO.setCode(0);
        pageVO.setData(list);
        pageVO.setMsg(msg);
        pageVO.setTotal((int) total);
        pageVO.setPageNum((int) pageNum);
        pageVO.setPageSize((int) pageSize);
        return pageVO;
    }

    public static PageVO fail(String msg){
        PageVO pageVO = new PageVO();
        pageVO.setCode(-1);
        pageVO.setMsg(msg);
        return pageVO;
    }

}
