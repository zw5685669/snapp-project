//package com.snapp.admin.cache;
//
//import java.io.*;
//
//public class SerializeUtil {
//
//	/**
//	 * 序列化对象
//	 * @param obj
//	 * @return
//	 */
//	public static byte[] serialize(Object obj){
//		ObjectOutputStream oos;
//		ByteArrayOutputStream baos;
//		try{
//			baos = new ByteArrayOutputStream();
//			oos = new ObjectOutputStream(baos);
//
//			oos.writeObject(obj);
//			return baos.toByteArray();
//
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 反序列化对象
//	 * @param byteArray
//	 * @return
//	 */
//	public static Object unSerialize(byte[] byteArray){
//		ByteArrayInputStream bais;
//        try {
//            //反序列化为对象
//            bais = new ByteArrayInputStream(byteArray);
//            ObjectInputStream ois = new ObjectInputStream(bais);
//            return ois.readObject();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//	}
//
//}
