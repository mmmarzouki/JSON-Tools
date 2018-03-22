package jsonifier;

class PrimitiveJsonifier {
	static String jsonifyPrimitive(String name,Object value) {
		String s = value.toString();
		if(value.getClass().equals(String.class))
			s="\""+s+"\"";
		return "{\""+name+"\": "+value.toString()+"}";
	}
	
	static boolean isPrimitive(@SuppressWarnings("rawtypes") Class c) {
		switch(c.getSimpleName()) {
		case "Byte": return true;
		case "Short": return true;
		case "Long": return true;
		case "Integer": return true;
		case "Float": return true;
		case "Double": return true;
		case "Boolean": return true;
		case "Char": return true;
		}
		return false;
	}
}
