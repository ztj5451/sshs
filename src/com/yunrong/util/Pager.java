package com.yunrong.util;

import java.util.List;

/**
 * 定义分页
 * 
 * @author Administrator
 * 
 */
public class Pager {

	public static final Integer MAX_PAGE_SIZE = 200;// 每页最大记录数限制

	// 排序方式（递增、递减）
	public enum Order {
		asc, desc
	}

	private int pageNumber = 1;// 当前页码
	private int numPerPage = 20;// 每页记录数
	private String searchBy;// 查找字段
	private String keyword;// 查找关键字
	private String orderBy;// 排序字段
	private Order order;// 排序方式

	private int totalCount;// 总记录数
	private List<?> result;// 返回结果

	// 获取总页数
	public int getPageCount() {
		int pageCount = totalCount / numPerPage;
		if (totalCount % numPerPage > 0) {
			pageCount++;
		}
		return pageCount;
	}

	// 获取当前页
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	//
	public int getPageSize() {
		return numPerPage;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.numPerPage = pageSize;
	}

	// 关键字搜索
	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	// 获取关键字
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	// 排序
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	// 获取总记录数
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	// 获取总结果
	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

}