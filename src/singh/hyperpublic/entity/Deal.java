package singh.hyperpublic.entity;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import singh.hyperpublic.exc.ClientException;

public class Deal extends Entity<Deal> {
	public static final String URL = BASE + "offers";
	private String description;
	@Token("expiration_date")
	private String expirationDate;
	private String id;
	@Token("image_url")
	private String imageUrl;
	private Payout payout;
	private PlaceOfDeal place;
	private String price;
	private String source;
	@Token("source_id")
	private String sourceId;
	private String status;
	private String title;
	private String url;
	private String value;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the expirationDate
	 */
	public String getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @return the payout
	 */
	public Payout getPayout() {
		return payout;
	}

	/**
	 * @return the place
	 */
	public PlaceOfDeal getPlace() {
		return place;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param expirationDate
	 *            the expirationDate to set
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @param payout
	 *            the payout to set
	 */
	public void setPayout(Payout payout) {
		this.payout = payout;
	}

	/**
	 * @param place
	 *            the place to set
	 */
	public void setPlace(PlaceOfDeal place) {
		this.place = place;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @param sourceId
	 *            the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return description + "\n\n***" + place + "\n\n***" + payout;
	}

	@Override
	public Deal initFromJSON(JSONObject json) throws Exception {
		try {
			description = super.getString(json, "description");
			id = super.getString(json, "id");
			expirationDate = String.valueOf(json.get("expiration_date"));
			imageUrl = super.getString(json, "image_url");
			price = super.getString(json, "price");
			source = super.getString(json, "source");
			sourceId = super.getString(json, "source_id");
			status = super.getString(json, "status");
			title = super.getString(json, "title");
			url = super.getString(json, "url");
			value = super.getString(json, "value");
			payout = new Payout().initFromJSON(json.getJSONObject("payout"));
			place = new PlaceOfDeal().initFromJSON(json.getJSONObject("place"));
		} catch (JSONException e) {
			throw new ClientException(e);
		}

		return this;
	}
}
