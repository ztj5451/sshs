package com.yunrong.service;

import java.util.List;

import com.yunrong.entity.UrlResource;

public interface UrlResourceService extends BaseService<UrlResource, String> {
	/**
	 * 保存
	 * 
	 * @param urlResource
	 */
	public void addUrlResource(UrlResource urlResource);

	/**
	 * 更新
	 * 
	 * @param id
	 */
	public void updateUrlResource(UrlResource urlResource);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteUrlResource(String id);

	/**
	 * 获取所有
	 * 
	 * @return
	 */
	public List<UrlResource> getAllUrlResource();
}
