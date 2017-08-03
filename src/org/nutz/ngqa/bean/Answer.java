package org.nutz.ngqa.bean;

import java.util.Date;

import org.nutz.mongo.annotation.Co;
import org.nutz.mongo.annotation.CoField;
import org.nutz.mongo.annotation.CoId;
import org.nutz.ngqa.Helpers;

@Co("answer")
public class Answer implements Freshable {

	@CoId
	private String id;
	@CoField(ref = true)
	private User user;
	@CoField
	private String content;
	@CoField
	private String format;
	@CoField
	private Date createdAt;
	@CoField
	private Date updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getShowContent() {
		return Helpers.formatContent(content, format);
	}

	public String getShowCreatedAt() {
		return Helpers.getFormatData(createdAt);
	}

}
