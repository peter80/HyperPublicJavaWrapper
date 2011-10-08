package singh.hp.client;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import singh.hyperpublic.client.HPClient;
import singh.hyperpublic.entity.Category;
import singh.hyperpublic.entity.Deal;
import singh.hyperpublic.entity.Place;
import singh.hyperpublic.exc.AuthException;

public class HPClientTest {
	//README: Put the correct client id and secret before running the tests
	private static final String SECRET = "";
	private static final String CLIENT_ID = "";

	@Test
	public void findPlaces() {
		List<Place> places = HPClient.create(
				CLIENT_ID,
				SECRET).findPlaces(
				"lat=40.7&lon=-74.0");
		Assert.assertTrue("Places list should not be empty", places != null
				&& places.size() > 0);
		for (Place place : places) {
			Assert.assertEquals("Super 1 Mart Hannam Chain Supermarket", place.getDisplayName());
			Assert.assertEquals("4de69538b04a760001006a52", place.getId());
			break;
		}
	}

	@Test
	public void findOffers() {
		List<Deal> deals = HPClient.create(
				CLIENT_ID,
				SECRET).findOffers(
				"lat=40.74&lon=-74.00");
		Assert.assertTrue("Deals list should not be empty", deals != null
				&& deals.size() > 0);
		for (Deal deal : deals) {
			Assert.assertEquals(deal.getSource(), "buywithme");
			Assert.assertEquals(deal.getSourceId(), "nyc-10337");
			break;
		}
	}

	@Test
	public void getCategories() {
		List<Category> list = HPClient.create(
				CLIENT_ID,
				SECRET).getCategories();
		Assert.assertTrue("List should not be empty",
				list != null && list.size() > 0);
		for (Category category : list) {
			Assert.assertEquals(category.getName(), "Yoga Studios");
			break;
		}
	}
	
	@Test(expected=AuthException.class) 
	public void getCategoriesExc() {
		HPClient.create(
				"sdas",
				"dasd").getCategories();
	}
}
