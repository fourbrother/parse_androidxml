package com.wjdiankong.parsexml;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class ParseMain {
	
	public static void main(String[] args){
		
		byte[] byteSrc = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try{
			fis = new FileInputStream("xmltest/AndroidManifest1.xml");
			bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=fis.read(buffer)) != -1){
				bos.write(buffer, 0, len);
			}
			byteSrc = bos.toByteArray();
		}catch(Exception e){
			System.out.println("parse xml error:"+e.toString());
		}finally{
			try{
				fis.close();
				bos.close();
			}catch(Exception e){
				
			}
		}
		
		System.out.println("Parse XML Header------------");
		ParseChunkUtils.parseXmlHeader(byteSrc);
		System.out.println();
		
		System.out.println("Parse String Chunk-------------");
		ParseChunkUtils.parseStringChunk(byteSrc);
		System.out.println();
		
		System.out.println("Parse Resource Chunk------------");
		ParseChunkUtils.parseResourceChunk(byteSrc);
		System.out.println();
		
		System.out.println("Parse XML Content-------------");
		ParseChunkUtils.parseXmlContent(byteSrc);
		System.out.println();
		
		ParseChunkUtils.writeFormatXmlToFile();
		
	}
	
	

}
