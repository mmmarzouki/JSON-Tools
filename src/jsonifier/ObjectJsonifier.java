package jsonifier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class ObjectJsonifier {
	//main method
	static String jsonifyObject(Object o) {
		//start object
		String s="{";
		//for every field
		for(Field f: o.getClass().getDeclaredFields()) {
			try {
				//get its name
				String name=",\""+f.getName()+"\": ";
				//get its content
				String content=getFieldContent(o,f) ;
				s+=name+content;
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		//remove first ,
		s = s.replaceFirst(",", "");
		//end object
		s+="}";
		return s;
	}


	static String getObjectFieldContent(Object o,Field f) throws Exception {
		if((f.getModifiers()&Modifier.PUBLIC)!=0)
			return getPublicObjectFieldContent(o,f);
		return getPrivateObjectFieldContent(o,f);	
	}
	
	static String getPublicObjectFieldContent(Object o, Field f) throws Exception{
		Object obj = f.get(o);
		return jsonifyObject(obj);
	}
	static String getPrivateObjectFieldContent(Object o, Field f) throws Exception{
		//get field name
				String fieldName = f.getName();
				//get getter method
				String getterName = "get"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1);
				Method getterMethod = o.getClass().getDeclaredMethod(getterName);
				//invoke method
				Object attributeObject = getterMethod.invoke(o,(Object[])null);
				//return result of invocation of the getter method
				String s = jsonifyObject(attributeObject);
				return s;
	}

	static String getFieldContent(Object o, Field f) throws Exception
	{
		if(f.getType().isPrimitive())
			return PrimitiveFieldJsonifier.getPrimitiveFieldContent(o, f);
		if(f.getType().equals(String.class))
			return "\""+PrimitiveFieldJsonifier.getPrimitiveFieldContent(o, f)+"\"";
		else {
			if(CollectionJsonifier.isCollection(f))
				return CollectionJsonifier.getCollectionFieldContent(o,f);
			else {
				if(f.getType().isArray())
					return ArrayJsonifier.getArrayFieldContent(o, f);
				//f is an object
				return ObjectJsonifier.getObjectFieldContent(o,f);
			}
		}
	}
}
