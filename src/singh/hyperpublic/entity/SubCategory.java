package singh.hyperpublic.entity;

import java.util.List;

public class SubCategory {
	private String id;
	private String name;
	private List<SubCategory> subcategories;

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
	public List<SubCategory> getSubcategories() {
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
	public void setSubcategories(List<SubCategory> subcategories) {
		this.subcategories = subcategories;
	}
}
