package com.example.annotationtest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class ExportXml {
	private String getFieldValue(Field field, Class fieldTypeClass, Object obj){
		String value = null;
		try {
			if (fieldTypeClass == String.class) {
				value = (String) field.get(obj);
			} else if (fieldTypeClass == int.class) {
				value = Integer.toString(field.getInt(obj));
			} else if (fieldTypeClass == long.class) {
				value = Long.toString(field.getLong(obj));
			} else if (fieldTypeClass == short.class) {
				value = Short.toString(field.getShort(obj));
			} else if (fieldTypeClass == float.class) {
				value = Float.toString(field.getFloat(obj));
			} else if (fieldTypeClass == double.class) {
				value = Double.toString(field.getDouble(obj));
			} else if (fieldTypeClass == byte.class) {
				value = Byte.toString(field.getByte(obj));
			} else if (fieldTypeClass == char.class) {
				value = Character.toString(field.getChar(obj));
			} else if (fieldTypeClass == boolean.class) {
				value = Boolean.toString(field.getBoolean(obj));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			value = null;
		}
		return value;
	}

	/**
	 * 输出对象的字段，当对象的字段为Collection或者Map类型时，
	 * 要调用exportObject方法继续处理
	 * @param obj 被处理的对象
	 * @throws Exception
	 */
	public void exportFields(Object obj) throws Exception {
		Exportable exportable = obj.getClass().getAnnotation(Exportable.class);
		if (exportable != null) {
			if (exportable.value().length() > 0) {
				//System.out.println("Class annotation Name:"+exportable.value());
			} else {
				//System.out.println("Class annotation Name:"+exportable.name());
			}
		} else {
			//System.out.println(obj.getClass()+"类不是使用Exportable标注过的");
		}

		//取出对象的成员变量
		Field[] fields = obj.getClass().getDeclaredFields();

		for (Field field : fields) {
			//获得成员变量的标注
			Persistent fieldAnnotation = field.getAnnotation(Persistent.class);
			if (fieldAnnotation == null) {
				continue;
			}
			//重要:避免java虚拟机检查对私有成员的访问权限
			field.setAccessible(true);
			Class typeClass = field.getType();
			String name = field.getName();
			String value = getFieldValue(field, typeClass, obj);

			//如果获得成员变量的值，则输出
			if (value != null) {
				System.out.println(getIndent() + "<" + name + ">\n"
						+ getIndent() + "\t" + value + "\n" + getIndent() + "</" + name + ">");
			} //处理成员变量中类型为Collection或Map
			else if ((field.get(obj) instanceof Collection)
					|| (field.get(obj) instanceof Map)) {
				exportObject(field.get(obj));
			} else {
				exportObject(field.get(obj));
			}
		}
	}

	//缩进深度
	int levelDepth = 0;
	//防止循环引用的检查者，循环引用现象如：a包含b，而b又包含a
	Collection<Object> cyclicChecker = new ArrayList<>();

	/**
	 * 返回缩进字符串
	 * @return
	 */
	private String getIndent() {
		String s = "";
		for (int i = 0; i < levelDepth; i++) {
			s += "\t";
		}
		return s;
	}

	/**
	 * 输出对象，如果对象类型为Collection和Map类型，
	 * 则需要递归调用exportObject进行处理
	 * @param obj
	 * @throws Exception
	 */
	public void exportObject(Object obj) throws Exception {
		Exportable exportable = null;
		String elementName = null;

		//循环引用现象处理
		if (cyclicChecker.contains(obj)) {
			return;
		}

		cyclicChecker.add(obj);

		//首先处理Collection和Map类型
		if (obj instanceof Collection) {
			for (Iterator i = ((Collection) obj).iterator(); i.hasNext();) {
				exportObject(i.next());
			}
		} else if (obj instanceof Map) {
			for (Iterator i = ((Map) obj).keySet().iterator(); i.hasNext();) {
				exportObject(i.next());
			}
		} else {
			exportable = obj.getClass().getAnnotation(Exportable.class);
			//如果obj已经被Exportable Annotation修饰过了（注意annotation是具有继承性的），
			//则使用其name作为输出xml的元素name
			if (exportable != null) {
				if (exportable.value().length() > 0) {
					elementName = exportable.value();
				} else {
					elementName = exportable.name();
				}
			}
			//未被修饰或者Exportable Annotation的值为空字符串，
			//则使用类名作为输出xml的元素name
			if (exportable == null || elementName.length() == 0) {
				elementName = obj.getClass().getSimpleName();
			}
			//输出xml元素头
			System.out.println(getIndent() + "<" + elementName + ">");
			levelDepth++;
			//如果没有被修饰，则直接输出其toString()作为元素值
			if (exportable == null) {
				System.out.println(getIndent() + obj.toString());
			}
			else {  //否则将对象的成员变量导出为xml
				exportFields(obj);
			}
			levelDepth--;
			//输出xml元素结尾
			System.out.println(getIndent() + "</" + elementName + ">");

		}
		cyclicChecker.remove(obj);
	}

	public static void main(String[] argv) {
		try {
			AddressForTest ad = new AddressForTest("China", "Beijing",
					"Beijing", "winnerStreet", "10");
			ExportXml test = new ExportXml();
			ArrayList<String> telephoneList = new ArrayList<>();
			telephoneList.add("66608888");
			telephoneList.add("66608889");
			ArrayList<AddressForTest> adList = new ArrayList<>();
			adList.add(ad);
			ContactBookForTest adl = new ContactBookForTest("coolBoy",
					18, telephoneList, adList, "some words");
			test.exportObject(adl);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
