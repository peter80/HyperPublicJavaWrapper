package singh.hyperpublic.json;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import singh.hyperpublic.entity.Category;
import singh.hyperpublic.entity.Deal;
import singh.hyperpublic.entity.Image;
import singh.hyperpublic.entity.Location;
import singh.hyperpublic.entity.Payout;
import singh.hyperpublic.entity.Place;
import singh.hyperpublic.entity.SubCategory;
import singh.hyperpublic.exc.ClientException;

public class Convertor {
	public static List<Category> toCategories(String json) {
		try {
			List<Category> categories = new ArrayList<Category>();
			JSONArray array = new JSONArray(json);
			int len = array.length();
			for (int x = 0; x < len; x++) {
				JSONObject obj = array.getJSONObject(x);
				Category category = new Category();
				categories.add(category.initFromJSON(obj));
			}
			return categories;
		} catch (JSONException e) {
			throw new ClientException(e);
		}
	}

	public static List<Image> toImages(String json) {
		return null;
	}

	public static List<Location> toLocations(String json) {
		return null;
	}

	public static List<Deal> toOffers(String json) {
		try {
			List<Deal> deals = new ArrayList<Deal>();
			JSONArray array = new JSONArray(json);
			int len = array.length();
			for (int x = 0; x < len; x++) {
				JSONObject obj = array.getJSONObject(x);
				Deal deal = new Deal();
				deals.add(deal.initFromJSON(obj));
			}
			return deals;
		} catch (Exception e) {
			throw new ClientException(e);
		}
	}

	public static List<Payout> toPayouts(String json) {
		return null;
	}

	public static List<Place> toPlaces(String json) {
		try {
			List<Place> places = new ArrayList<Place>();
			JSONArray array = new JSONArray(json);
			int len = array.length();
			for (int x = 0; x < len; x++) {
				JSONObject obj = array.getJSONObject(x);
				Place place = new Place();
				places.add(place.initFromJSON(obj));
			}
			return places;
		} catch (Exception e) {
			throw new ClientException(e);
		}
	}

	public static List<SubCategory> toSubCategories(String json) {
		return null;
	}

	private Convertor() {
	}
}
