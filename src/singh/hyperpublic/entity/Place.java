package singh.hyperpublic.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import singh.hyperpublic.exc.ClientException;

public class Place extends Entity<Place> {
	public static final String URL = BASE + "places";
	@Ignore
	private List<Category> categories;
	@Token("display_name")
	private String displayName;
	@Ignore
	private double distance;
	private String id;
	@Ignore
	private List<Image> images;
	@Ignore
	private List<Location> locations;
	@Token("perma_link")
	private String permaLink;
	@Token("phone_number")
	private String phone;
	@Ignore
	private Map<String, String> properties;
	@Ignore
	private List<String> tags;
	private String website;

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the images
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * @return the locations
	 */
	public List<Location> getLocations() {
		return locations;
	}

	/**
	 * @return the permaLink
	 */
	public String getPermaLink() {
		return permaLink;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the properties
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(List<Image> images) {
		this.images = images;
	}

	/**
	 * @param locations
	 *            the locations to set
	 */
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	/**
	 * @param permaLink
	 *            the permaLink to set
	 */
	public void setPermaLink(String permaLink) {
		this.permaLink = permaLink;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public Place initFromJSON(JSONObject json) throws Exception {
		try {
			super.initFromJSON(json);
			id = super.getString(json, "id");

			// categories
			categories = new ArrayList<Category>();
			JSONArray catgArray = json.getJSONArray("category");
			int count = catgArray.length();
			for (int x = 0; x < count; x++) {
				JSONObject catg = (JSONObject) catgArray.get(x);
				categories.add(new Category().initFromJSON(catg));
			}

			// images
			images = new ArrayList<Image>();
			JSONArray imgArray = json.getJSONArray("images");
			count = imgArray.length();
			for (int x = 0; x < count; x++) {
				JSONObject img = (JSONObject) imgArray.get(x);
				images.add(new Image().initFromJSON(img));
			}

			// locations
			locations = new ArrayList<Location>();
			JSONArray locArray = json.getJSONArray("locations");
			count = locArray.length();
			for (int x = 0; x < count; x++) {
				JSONObject loc = (JSONObject) locArray.get(x);
				locations.add(new Location().initFromJSON(loc));
			}

			// properties
			properties = new HashMap<String, String>();
			JSONArray propsArr = json.getJSONArray("properties");
			count = propsArr.length();
			for (int x = 0; x < count; x++) {
				JSONObject prop = (JSONObject) locArray.get(x);
				properties.put(prop.getString("key"), prop.getString("value"));
			}

			// tags
			tags = new ArrayList<String>();
			JSONArray tagsArr = json.getJSONArray("tags");
			count = tagsArr.length();
			for (int x = 0; x < count; x++) {
				tags.add((String) tagsArr.get(x));
			}

		} catch (JSONException e) {
			throw new ClientException(e);
		}

		return this;
	}

	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return id + ": " + displayName;
	}
}
