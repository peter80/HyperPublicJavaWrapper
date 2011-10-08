package singh.hyperpublic.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import singh.hyperpublic.exc.ClientException;

public class Category extends Entity<Category> {
	public static final String URL = BASE + "categories";
	private String id;
	private String name;
	private List<Category> subcategories;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the subcategories
	 */
	public List<Category> getSubcategories() {
		return subcategories;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param subcategories
	 *            the subcategories to set
	 */
	public void setSubcategories(List<Category> subcategories) {
		this.subcategories = subcategories;
	}

	@Override
	public Category initFromJSON(JSONObject json) {
		try {
			name = super.getString(json, "name");
			id = super.getString(json, "id");
			if (json.has("subcategories")) {
				JSONArray array = json.getJSONArray("subcategories");
				int len = array.length();
				subcategories = new ArrayList<Category>();
				for (int x = 0; x < len; x++) {
					JSONObject obj = (JSONObject) array.get(x);
					subcategories.add(initFromJSON(obj));
				}
			}
		} catch (JSONException e) {
			throw new ClientException(e);
		}

		return this;
	}

	@Override
	public String toString() {
		return name;
	}
}
