/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.framework.po;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-2
 */
public class Picture implements Serializable {
	
	private static final long serialVersionUID = -3318102781194896749L;
	private String description;
	private String imgPath;
	private BigDecimal price;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Picture [description=" + description + ", imgPath=" + imgPath + ", price=" + price + "]";
	}
}
