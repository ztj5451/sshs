package com.yunrong.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Entity
@Table(name = "crm_urlresource")
public class UrlResource extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -4692578414635196480L;
	private String url;
	private String urlName;
	private String parentId;
	private String parentName;
	private RequestMatcher urlMatcher;
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}





	@Transient
	public boolean matches(HttpServletRequest request) {
		if (urlMatcher == null) {
			urlMatcher = new AntPathRequestMatcher(url);
		}
		return urlMatcher.matches(request);
	}


	@Transient
	public void clearUrlMatcher() {
		urlMatcher = null;
	}

}