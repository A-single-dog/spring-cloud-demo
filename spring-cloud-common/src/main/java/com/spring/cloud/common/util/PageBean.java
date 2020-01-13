package com.spring.cloud.common.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value="分页出参说明")
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = 8656597559014685635L;
    @ApiModelProperty(value="数据总数")
    private long total;        //总记录数
    @ApiModelProperty(value="数据结果集")
    private List<T> list;    //结果集
    @ApiModelProperty(value="第几页")
    private int pageNum;    // 第几页
    @ApiModelProperty(value="每页记录数")
    private int pageSize;    // 每页记录数
    @ApiModelProperty(value="总页数")
    private int pages;        // 总页数
    @ApiModelProperty(value="当前页的数量 ")
    private int size;        // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性
    
    /**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，
     * 而出现一些问题。
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public PageBean(List<?> list, Class<T>clazz) {
        if (list instanceof Page) {
            Page<?> page = (Page<?>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list  = BeanUtil.copyList(page, clazz);
            this.size = page.size();
        }
    }
    public PageBean(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list  = page.getResult();
            this.size = page.size();
        }
    }
}