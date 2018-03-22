package test;


public class WrapperTestObject {
	/*
	private static int wrapperIntPrivate = 42;
	public static int wrapperIntPublic = 45;
	public SimpleTestObject simplePublicTestObject = new SimpleTestObject();
	//private SimpleTestObject simplePrivateTestObject = new SimpleTestObject();
	
	
	public int getWrapperIntPrivate() {
		return wrapperIntPrivate;
	}
	public SimpleTestObject getSimplePrivateTestObject() {
		return simplePrivateTestObject;
	}
	
	private ArrayList<SimpleTestObject> list = new ArrayList<>(); 
	public ArrayList<SimpleTestObject> getList() {
		return list;
	}
	public WrapperTestObject() {
		list.add(new SimpleTestObject());
		list.add(new SimpleTestObject());
	}
	
	
	public int tab[] = {1,2,3};*/
	private SimpleTestObject tab[] = { new SimpleTestObject(),new SimpleTestObject()};

	public SimpleTestObject[] getTab() {
		return tab;
	}
	
}

