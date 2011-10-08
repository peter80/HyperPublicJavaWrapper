package singh.hyperpublic.client;

import java.util.List;

import singh.hyperpublic.entity.Category;
import singh.hyperpublic.entity.Deal;
import singh.hyperpublic.entity.Place;
import singh.hyperpublic.exc.ClientException;

/**
 * @author Parminder Singh
 *
 */
public interface HPClientService {
	String QUERY_DELIM = "&";
	String KV_DELIM = "=";
	String INVALID_OAUTH_ERROR = "Invalid OAuth Request";
	String _404 = "(404)";
	
	/**
	 * @return
	 * @throws ClientException
	 */
	List<Category> getCategories() throws ClientException ;

	/**
	 * @param query
	 * @return
	 * @throws ClientException
	 */
	List<Deal> findOffers(String query) throws ClientException ;

	/**
	 * @param query
	 * @return
	 * @throws ClientException
	 */
	List<Place> findPlaces(String query) throws ClientException ;
}
