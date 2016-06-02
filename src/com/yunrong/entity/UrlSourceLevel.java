package com.yunrong.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="crm_urlSourceLevel")
public class UrlSourceLevel extends BaseEntity  implements java.io.Serializable{
	
	private static final long serialVersionUID = 8512141736173129381L;
	private String urlId;
	private String levelName;
	private UrlSource url;
	
	public String getUrlId() {
		return urlId;
	}
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="urlId",updatable=false,insertable=false)
    public UrlSource getUrl() {
		return url;
	}
	public void setUrl(UrlSource url) {
		this.url = url;
	}
	
}