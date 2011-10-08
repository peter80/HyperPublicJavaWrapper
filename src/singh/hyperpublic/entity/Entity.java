package singh.hyperpublic.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public abstract class Entity<T> {
	private static Logger logger = Logger.getLogger(Entity.class);

	private static final String SET = "set";
	protected static final String BASE = "https://api.hyperpublic.com/api/v1/";
	
	public T initFromJSON(JSONObject json) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("JSON STRING: " + json.toString());
		}
		Class<? extends Entity> clazz = getClass();
		Field fields[] = clazz.getDeclaredFields();
		new HashMap<String, Object>();
		for (Field f : fields) {
			if (f.isAnnotationPresent(Ignore.class)) {
				continue;
			}
			if (Modifier.isStatic(f.getModifiers())) {
				continue;
			}
			Class<?> fieldType = f.getType();
			if (fieldType.isAssignableFrom(Entity.class)) {
				continue;
			}

			String fieldName = f.getName();
			String token = fieldName;
			if (f.isAnnotationPresent(Token.class)) {
				token = f.getAnnotation(Token.class).value();
			}
			Object value = getValue(json, token);
			String setterName = SET + capitalize(fieldName);
			Method setterMethod = clazz.getMethod(setterName, fieldType);
			if (value != null && value != JSONObject.NULL) {
				if (logger.isDebugEnabled()) {
					logger.debug(setterName + ", " + value + ", "
							+ value.getClass());
				}
				setterMethod.invoke(this, value);
			}
		}
		return (T) this;
	}

	/**
	 * @param json
	 * @param token
	 * @return
	 * @throws JSONException
	 */
	protected Object getValue(JSONObject json, String token)
			throws JSONException {
		if (json.has(token)) {
			return json.get(token);
		}
		return null;
	}

	/**
	 * @param json
	 * @param token
	 * @return
	 * @throws JSONException
	 */
	protected String getString(JSONObject json, String token)
			throws JSONException {
		if (json.has(token)) {
			return json.getString(token);
		}
		return null;
	};

	public String capitalize(String input) {
		StringBuilder sb = new StringBuilder();
		char charArr[] = input.toCharArray();
		for (int idx = 0; idx < charArr.length; idx++) {
			char ch = charArr[idx];
			if (idx == 0) {
				sb.append(Character.toUpperCase(ch));
				continue;
			}
			if (Character.isUpperCase(ch)) {
				if (Character.isLowerCase(charArr[idx - 1])) {
					sb.append("");
				}
			}
			sb.append(ch);
		}
		return sb.toString();
	}
}
