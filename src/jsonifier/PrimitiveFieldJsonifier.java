package jsonifier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class PrimitiveFieldJsonifier {
		
	static String getPrimitiveFieldContent(Object o, Field f) throws Exception {
		if((f.getModifiers()&Modifier.PUBLIC)!=0)
			return getPublicPrimitiveFieldContent(o,f);
		return getPrivatePrimitiveFieldContent(o,f);	
	}
	static String getPublicPrimitiveFieldContent(Object o, Field f) throws Exception {
		return f.get(o).toString();
	}
	static String getPrivatePrimitiveFieldContent(Object o, Field f) throws Exception {
		//get field name
				String fieldName = f.getName();
				//get getter method
				String getterName = "get"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1);
				Method getterMethod = o.getClass().getDeclaredMethod(getterName);
				//invoke method
				String s = getterMethod.invoke(o,(Object[])null).toString();
				//return result of invocation of the getter method
				return s;
	}
}
