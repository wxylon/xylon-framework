/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.po;

/**
 * @author wxylon@gmail.com
 * @date 2013-1-4
 */
public class Image {
	private String url;
	private String size;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Image [url=" + url + ", size=" + size + "]";
	}
}

