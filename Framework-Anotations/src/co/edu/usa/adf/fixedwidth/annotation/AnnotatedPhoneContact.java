package co.edu.usa.adf.fixedwidth.annotation;

@FixedWidthData(recordLength = 100)
public class AnnotatedPhoneContact {

	@FixedWidthField(position=1, width=20) String  name;
	@FixedWidthField(position=2, width=30) String  phone;
	@FixedWidthField(position=3, width=50) String  address;
}
