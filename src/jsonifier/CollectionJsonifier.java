package jsonifier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;

class CollectionJsonifier {
	static boolean isCollection(Field f) {
		boolean isIterable = Iterable.class.isAssignableFrom(f.getType());
		return isIterable;
	}
	static boolean isCollection(Object o) {
		return Iterable.class.isAssignableFrom(o.getClass());
	}
	static String getCollectionFieldContent(Object o, Field f) throws Exception {
		if((f.getModifiers()&Modifier.PUBLIC)!=0)
			return getPublicCollectionFieldContent(o,f);
		return getPrivateCollectionFieldContent(o,f);
	}
	static String getPublicCollectionFieldContent(Object o, Field f) throws Exception {
		String s="[";
		@SuppressWarnings("unchecked")
		Iterable<Object> c = (Iterable<Object>) f.get(o);
		Iterator<Object> it = c.iterator();
		while(it.hasNext()) {
			Object obj = it.next();
			if(PrimitiveJsonifier.isPrimitive(obj.getClass())||obj.getClass().equals(String.class))
				s+=",\""+obj.toString()+"\"";
			s+=","+ObjectJsonifier.jsonifyObject(obj);
		}
		s+="]";
		return s.replaceFirst(",", "");
	}
	static String getPrivateCollectionFieldContent(Object o, Field f) throws Exception {
		String fieldName = f.getName();
		//get getter method
		String getterName = "get"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1);
		Method getterMethod = o.getClass().getDeclaredMethod(getterName);
		//invoke method
		@SuppressWarnings("unchecked")
		Iterable<Object> c = (Iterable<Object>) getterMethod.invoke(o,(Object[])null);
		
		String s="[";
		Iterator<Object> it = c.iterator();
		while(it.hasNext()) {
			Object obj = it.next();
			if(PrimitiveJsonifier.isPrimitive(obj.getClass())||obj.getClass().equals(String.class))
				s+=",\""+obj.toString()+"\"";
			s+=","+ObjectJsonifier.jsonifyObject(obj);
		}
		s+="]";
		return s.replaceFirst(",", "");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	static String jsonifyCollection(Collection collection) {
		String s="[";
		Iterator<Object> it = collection.iterator();
		while(it.hasNext()) {
			Object obj = it.next();
			if(PrimitiveJsonifier.isPrimitive(obj.getClass())||obj.getClass().equals(String.class))
				s+=",\""+obj.toString()+"\"";
			s+=","+ObjectJsonifier.jsonifyObject(obj);
		}
		return s.replaceFirst(",", "")+"]";
	}
	
}
