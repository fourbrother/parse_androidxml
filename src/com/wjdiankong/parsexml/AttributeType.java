package com.wjdiankong.parsexml;


/**
attributes' types
enum{
	ATTR_NULL 	= 0,
	ATTR_REFERENCE 	= 1,
	ATTR_ATTRIBUTE 	= 2,
	ATTR_STRING 	= 3,
	ATTR_FLOAT 	= 4,
	ATTR_DIMENSION 	= 5,
	ATTR_FRACTION 	= 6,
	
	ATTR_FIRSTINT 	= 16,

	ATTR_DEC 	= 16,
	ATTR_HEX	= 17,
	ATTR_BOOLEAN	= 18,

	ATTR_FIRSTCOLOR	= 28,
	ATTR_ARGB8 	= 28,
	ATTR_RGB8	= 29,
	ATTR_ARGB4	= 30,
	ATTR_RGB4	= 31,
	ATTR_LASTCOLOR	= 31,

	ATTR_LASTINT	= 31,
};
 * @author i
 *
 */
public class AttributeType {
	
	public final static int ATTR_NULL = 0;
	public final static int ATTR_REFERENCE 	= 1;
	public final static int ATTR_ATTRIBUTE 	= 2;
	public final static int ATTR_STRING 	= 3;
	public final static int ATTR_FLOAT 	= 4;
	public final static int ATTR_DIMENSION 	= 5;
	public final static int ATTR_FRACTION 	= 6;
	public final static int ATTR_FIRSTINT 	= 16;
	public final static int ATTR_HEX	= 17;
	public final static int ATTR_BOOLEAN	= 18;
	public final static int ATTR_FIRSTCOLOR	= 28;
	public final static int ATTR_RGB8	= 29;
	public final static int ATTR_ARGB4	= 30;
	public final static int ATTR_RGB4	= 31;
	public final static int ATTR_LASTCOLOR	= 31;
	public final static int ATTR_LASTINT	= 31;
	
	public static final int
    COMPLEX_UNIT_PX			=0,
    COMPLEX_UNIT_DIP		=1,
    COMPLEX_UNIT_SP			=2,
    COMPLEX_UNIT_PT			=3,
    COMPLEX_UNIT_IN			=4,
    COMPLEX_UNIT_MM			=5,
	COMPLEX_UNIT_SHIFT		=0,
    COMPLEX_UNIT_MASK		=15,
    COMPLEX_UNIT_FRACTION	=0,
    COMPLEX_UNIT_FRACTION_PARENT=1,
    COMPLEX_RADIX_23p0		=0,
    COMPLEX_RADIX_16p7		=1,
    COMPLEX_RADIX_8p15		=2,
    COMPLEX_RADIX_0p23		=3,
    COMPLEX_RADIX_SHIFT		=4,
    COMPLEX_RADIX_MASK		=3,
    COMPLEX_MANTISSA_SHIFT	=8,
    COMPLEX_MANTISSA_MASK	=0xFFFFFF;
	
	/*public static String getAttributeData(AttributeData data){
		if(data.type == ATTR_STRING){
			return ParseChunkUtils.getStringContent(data.data);
		}else if(data.type == ATTR_BOOLEAN){
			return data.data==0 ? "false" : "true";
		}else{
			return ""+data.data;
		}
	}*/
	
	public static String getAttributeData(AttributeData data){
		if (data.type == ATTR_STRING) {
			return ParseChunkUtils.getStringContent(data.data);
		}
		if (data.type == ATTR_ATTRIBUTE) {
			return String.format("?%s%08X",getPackage(data.data),data.data);
		}
		if (data.type == ATTR_REFERENCE) {
			return String.format("@%s%08X",getPackage(data.data),data.data);
		}
		if (data.type == ATTR_FLOAT) {
			return String.valueOf(Float.intBitsToFloat(data.data));
		}
		if (data.type == ATTR_HEX) {
			return String.format("0x%08X",data.data);
		}
		if (data.type == ATTR_BOOLEAN) {
			return data.data!=0?"true":"false";
		}
		if (data.type == ATTR_DIMENSION) {
			return Float.toString(complexToFloat(data.data))+
				DIMENSION_UNITS[data.data & COMPLEX_UNIT_MASK];
		}
		if (data.type == ATTR_FRACTION) {
			return Float.toString(complexToFloat(data.data))+
				FRACTION_UNITS[data.data & COMPLEX_UNIT_MASK];
		}
		if (data.type >= ATTR_FIRSTCOLOR && data.type <= ATTR_LASTCOLOR) {
			return String.format("#%08X",data.data);
		}
		if (data.type >= ATTR_FIRSTINT && data.type <= ATTR_LASTINT) {
			return String.valueOf(data.data);
		}
		return String.format("<0x%X, type 0x%02X>",data.data, data.type);
	}
	
	private static String getPackage(int id) {
		if (id>>>24==1) {
			return "android:";
		}
		return "";
	}
	
	public static float complexToFloat(int complex) {
		return (float)(complex & 0xFFFFFF00)*RADIX_MULTS[(complex>>4) & 3];
	}
	
	private static final float RADIX_MULTS[]={
		0.00390625F,3.051758E-005F,1.192093E-007F,4.656613E-010F
	};
	
	private static final String DIMENSION_UNITS[]={
		"px","dip","sp","pt","in","mm","",""
	};
	
	private static final String FRACTION_UNITS[]={
		"%","%p","","","","","",""
	};
	
	public static String getAttrType(int type){
		switch(type){
			case ATTR_NULL:
				return "ATTR_NULL";
			case ATTR_REFERENCE:
				return "ATTR_REFERENCE";
			case ATTR_ATTRIBUTE:
				return "ATTR_ATTRIBUTE";
			case ATTR_STRING:
				return "ATTR_STRING";
			case ATTR_FLOAT:
				return "ATTR_FLOAT";
			case ATTR_DIMENSION:
				return "ATTR_DIMENSION";
			case ATTR_FRACTION:
				return "ATTR_FRACTION";
			case ATTR_FIRSTINT:
				return "ATTR_FIRSTINT";
			case ATTR_HEX:
				return "ATTR_HEX";
			case ATTR_BOOLEAN:
				return "ATTR_BOOLEAN";
			case ATTR_FIRSTCOLOR:
				return "ATTR_FIRSTCOLOR";
			case ATTR_RGB8:
				return "ATTR_RGB8";
			case ATTR_ARGB4:
				return "ATTR_ARGB4";
			case ATTR_RGB4:
				return "ATTR_RGB4";
		}
		return "";
	}
	
}
