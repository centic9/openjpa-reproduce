package org.dstadler.commoncrawl.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("unused")
@Entity
public class FileURL {
	public final static int URL_MAX_LENGTH = 8096;

	@Id
	@Column(length = URL_MAX_LENGTH)
	private String url;

	public FileURL() {
		super();
	}

	public FileURL(String url) {
		super();

		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@SuppressWarnings("RedundantIfStatement")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileURL other = (FileURL) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
}
