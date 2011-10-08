package singh.hyperpublic.entity;

public class Image extends Entity<Image> {
	@Token("src_large")
	private String large;
	@Token("src_small")
	private String small;
	@Token("src_thumb")
	private String thumbnail;

	/**
	 * @return the large
	 */
	public String getLarge() {
		return large;
	}

	/**
	 * @return the small
	 */
	public String getSmall() {
		return small;
	}

	/**
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * @param large
	 *            the large to set
	 */
	public void setLarge(String large) {
		this.large = large;
	}

	/**
	 * @param small
	 *            the small to set
	 */
	public void setSmall(String small) {
		this.small = small;
	}

	/**
	 * @param thumbnail
	 *            the thumbnail to set
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
