package com.yunrong.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Entity
@Table(name="crm_urlSource")
public class UrlSource extends BaseEntity  implements java.io.Serializable{
	private static final long serialVersionUID = -4692578414635196480L;
	private String url;
	private String urlName;
	private Set<UrlSourceLevel> urlLevels;
	private Collection<ConfigAttribute> configAttributes;
	private RequestMatcher urlMatcher;
	
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
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="urlId",updatable=false,insertable=false)
	public Set<UrlSourceLevel> getUrlLevels() {
		return urlLevels;
	}
	public void setUrlLevels(Set<UrlSourceLevel> urlLevels) {
		this.urlLevels = urlLevels;
	}
	
	@Transient
	public Collection<ConfigAttribute> getConfigAttributes() {
		if(configAttributes!=null){
			return configAttributes;
		}else{
			if(urlLevels==null || urlLevels.size()==0){
				return null;
			}else{
				configAttributes = new ArrayList<ConfigAttribute>(urlLevels.size());
				for (UrlSourceLevel urlLevel : urlLevels) {
					configAttributes.add(new SecurityConfig(urlLevel.getLevelName().trim()));
				}
				return configAttributes;
			}
		}
	}
	
	@Transient
	public boolean matches(HttpServletRequest request){
		if(urlMatcher==null){
			urlMatcher = new AntPathRequestMatcher(url);
		}
		return urlMatcher.matches(request);
	}
	
	@Transient
	public void clearConfigAttributes() {
		configAttributes=null;
	}
	
	@Transient
	public void clearUrlMatcher() {
		urlMatcher=null;
	}
	
}