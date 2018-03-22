package jsonifier;

import java.util.Collection;

public class Jsonifier {
	public static String jsonifyPrimitive(String name, Object value) {
		return PrimitiveJsonifier.jsonifyPrimitive(name, value);
	}
	@SuppressWarnings("rawtypes")
	public static String jsonify(Object value) {
		//array
		if(value.getClass().isArray())
			return ArrayJsonifier.jsonifyArray(value);
		//collection
		if(CollectionJsonifier.isCollection(value))
			return CollectionJsonifier.jsonifyCollection((Collection)value);
		//object
		return ObjectJsonifier.jsonifyObject(value);
	}
}
