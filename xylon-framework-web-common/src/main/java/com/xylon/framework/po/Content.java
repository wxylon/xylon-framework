/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.framework.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-2
 */
public class Content implements Serializable {
	
	private static final long serialVersionUID = 9165002456746543744L;
	private String author;
	private String id;
	private String title;
	private List<Picture> pictures;
	private List<Image> images;
	private String path;
	private BigDecimal price;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
}
