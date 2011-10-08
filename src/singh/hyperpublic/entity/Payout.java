package singh.hyperpublic.entity;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import singh.hyperpublic.exc.ClientException;

public class Payout extends Entity<Payout> {
	private String description;
	private String type;
	private String value;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
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
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public Payout initFromJSON(JSONObject json) throws Exception {
		try {
			description = super.getString(json, "description");
			value = super.getString(json, "value");
			type = super.getString(json, "type");
		} catch (JSONException e) {
			throw new ClientException(e);
		}

		return this;
	}

	@Override
	public String toString() {
		return description + ", " + value + ", " + type;
	}
}
