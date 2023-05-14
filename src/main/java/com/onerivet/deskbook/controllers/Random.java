package com.onerivet.deskbook.controllers;

import java.io.File;

public class Random {

	public static void main(String[] args) {

//		String path = "D:\\deskbsook-file";
//
//		File file = new File(path);
//		file.mkdir();

		String s = "data:image/jpeg;base64,/9j/4AAQSkZJR";

		System.out.println(s.substring(23));
		System.out.println((byte) 0xFF);
		System.out.println((byte) 0xD8);

//		byte[] binaryData = DatatypeConverter.parseBase64Binary(base64String);
////	if (binaryData[0] != (byte) 0xFF || binaryData[1] != (byte) 0xD8) {
////		return false;
////	}
//
//	// Check the file extension to ensure it's .jpg or .jpeg with type Data
//	// URL--data:content/type;base64
//     if (!base64String.startsWith("data:image/jpeg;base64,") && !base64String.startsWith("data:image/jpg;base64,")) {
//        return false;
//    }
	}
}
