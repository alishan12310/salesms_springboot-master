package com.dyuloon.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author dyuloon
 * @since 2023-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IndexNotes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 备忘id
     */
    @TableId(value = "notes_id", type = IdType.AUTO)
    private Integer notesId;

    /**
     * 备忘标题
     */
    private String notesTitle;

    /**
     * 备忘内容
     */
    private String notesContent;

    /**
     * 备忘日期
     */
    private LocalDate notesDate;

    /**
     * 备忘时间
     */
    private LocalTime notesTime;

    /**
     * 备忘用户id
     */
    private Integer notesUser;


}
