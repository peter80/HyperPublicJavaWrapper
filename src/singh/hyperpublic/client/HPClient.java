package singh.hyperpublic.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import singh.hyperpublic.entity.Category;
import singh.hyperpublic.entity.Deal;
import singh.hyperpublic.entity.Place;
import singh.hyperpublic.json.Convertor;
import singh.hyperpublic.rest.RestHelper;

/**
 * @author Parminder Singh
 * 
 */
public class HPClient implements HPClientService {
	

	/* (non-Javadoc)
	 * @see singh.hyperpublic.client.HyperPublicClient#getCategories()
	 */
	@Override
	public List<Category> getCategories() {
		String resp = RestHelper.read(Category.URL, clientId, clientSecret,
				null);
		return Convertor.toCategories(resp);
	}	

	/* (non-Javadoc)
	 * @see singh.hyperpublic.client.HyperPublicClient#findOffers(java.lang.String)
	 */
	@Override
	public List<Deal> findOffers(String query) {
		String resp = RestHelper.read(Deal.URL, clientId, clientSecret,
				buildApiParamsFromQuery(query
						+ "&q=mixology&status=all&type=deal"));
		return Convertor.toOffers(resp);
	}	

	/* (non-Javadoc)
	 * @see singh.hyperpublic.client.HyperPublicClient#findPlaces(java.lang.String)
	 */
	@Override
	public List<Place> findPlaces(String query) {
		String resp = RestHelper.read(Place.URL, clientId, clientSecret,
				buildApiParamsFromQuery(query));
		return Convertor.toPlaces(resp);
	}
	
	/**
	 * @param clientId
	 * @param clientSecret
	 * @return
	 */
	public static HPClient create(String clientId, String clientSecret) {
		return new HPClient(clientId, clientSecret);
	}

	private final String clientId;

	private final String clientSecret;

	/**
	 * @param clientId
	 * @param clientSecret
	 */
	private HPClient(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
	
	/**
	 * @param query
	 * @return
	 */
	private Map<String, String> buildApiParamsFromQuery(String query) {
		Map<String, String> params = new HashMap<String, String>();
		String[] pairs = query.split(QUERY_DELIM);
		for (String pair : pairs) {
			String[] kv = pair.split(KV_DELIM);
			params.put(kv[0], kv[1]);
		}
		return params;
	}
}
