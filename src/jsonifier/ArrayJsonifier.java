package jsonifier;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class ArrayJsonifier {
	static String jsonifyArray(Object array) {
		String s="[";
		for(int i=0;i<Array.getLength(array);i++) {
			Object obj = Array.get(array, i);
			if(PrimitiveJsonifier.isPrimitive(obj.getClass()))
				s+=",\""+obj.toString()+"\"";
			else
				s+=","+ObjectJsonifier.jsonifyObject(obj);
		}
		return s.replaceFirst(",", "")+"]";
	}
	static String getArrayFieldContent(Object o, Field f) throws Exception{
		if((f.getModifiers()&Modifier.PUBLIC)!=0)
			return getPublicArrayFieldContent(o,f);
		return getPrivateArrayFieldContent(o,f);	
	}
	static String getPublicArrayFieldContent(Object o, Field f) throws Exception{
		String s="[";
		Object array = f.get(o);
		//Array array = (Array)test;
		for(int i=0;i<Array.getLength(array);i++) {
			Object obj = Array.get(array, i);
			if(PrimitiveJsonifier.isPrimitive(obj.getClass()))
				s+=",\""+obj.toString()+"\"";
			else
				s+=","+ObjectJsonifier.jsonifyObject(obj);
		}
		return s.replaceFirst(",", "")+"]";
	}
	static String getPrivateArrayFieldContent(Object o, Field f) throws Exception{
		String fieldName = f.getName();
		//get getter method
		String getterName = "get"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1);
		Method getterMethod = o.getClass().getDeclaredMethod(getterName);
		//invoke method
		Object array = getterMethod.invoke(o,(Object[])null);
		
		String s="[";
		for(int i=0;i<Array.getLength(array);i++) {
			Object obj = Array.get(array, i);
			if(PrimitiveJsonifier.isPrimitive(obj.getClass()))
				s+=",\""+obj.toString()+"\"";
			else
				s+=","+ObjectJsonifier.jsonifyObject(obj);
		}
		return s.replaceFirst(",", "")+"]";
	}
}
