package singh.hyperpublic.rest;

import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import singh.hyperpublic.client.HPClientService;
import singh.hyperpublic.exc.AuthException;
import singh.hyperpublic.exc.ClientException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.json.impl.provider.entity.JSONRootElementProvider;

/**
 * @author Parminder Singh
 * 
 */
public class RestHelper {
	private static final String CLIENT_ID = "client_id";
	private static final String CLIENT_SECRET = "client_secret";
	private static final String ERROR = "error";

	public static String read(String url, String clientId, String clientSecret,
			Map<String, String> apiParams) throws AuthException, ClientException {
		String resp = null;
		try {
			setupSSL();
			resp = readWithJersey(url, clientId, clientSecret, apiParams)
					.getEntity(String.class);
			if( !resp.startsWith("[") ) {
				checkError(resp);
			}
			return resp;
		} catch (Exception e) {
			if( HPClientService.INVALID_OAUTH_ERROR.equals(resp) ) {
				throw new AuthException(resp);
			}
			throw new ClientException(e);
		}
	}

	private static void checkError(String resp) {
		try {
			JSONObject obj = new JSONObject(resp);
			if(obj.has(ERROR)) {
				throw new ClientException(obj.getString(ERROR));
			}
		} catch (JSONException e) {
			throw new ClientException(e);
		}
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private static ClientResponse readWithJersey(String url, String clientId,
			String clientSecret, Map<String, String> apiParams)
			throws Exception {
		ClientConfig config = new DefaultClientConfig();
		config.getClasses().add(JSONRootElementProvider.class);

		Client client = Client.create();
		WebResource webResource = client.resource(url);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		params.add(CLIENT_ID, clientId);
		params.add(CLIENT_SECRET, clientSecret);
		if (apiParams != null && !apiParams.isEmpty()) {
			for (String key : apiParams.keySet()) {
				params.add(key, apiParams.get(key));
			}
		}

		return webResource.queryParams(params).get(ClientResponse.class);
	}

	private static void setupSSL() throws Exception {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] certs,
					String authType) {
				// Trust always
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs,
					String authType) {
				// Trust always
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		// Create empty HostnameVerifier
		HostnameVerifier hv = new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		};

		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
	}
}
