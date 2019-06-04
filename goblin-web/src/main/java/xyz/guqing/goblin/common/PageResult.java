package xyz.guqing.goblin.common;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer pageNum;      // 开始数据的索引
    private Integer pageSize;      // 每一页的数量
    private Long total;      // 总共的数据量
	private List<?> pageResult;
	
    /**
     * 提供一个构造方法
     * @param total
     * @param pageResult
     */ 
    public PageResult(Long total, List<?> pageResult) {
        this.total = total;
        this.pageResult = pageResult;
    }
    

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<?> getPageResult() {
		return pageResult;
	}

	public void setPageResult(List<?> pageResult) {
		this.pageResult = pageResult;
	}
	
}