package examples.encapsulation;

public class Class2 {

	Class1 class1Object;
	
	/* 
	 * har ikke tilgang til "private instance variable"
	 * har tilgang til "default instance variable"
	 * har tilgang til "public instance variabel"
	 * 
	 * default variabel har vi ikke tilgang til hvis klassene ikke ligger
	 * i samme pakke.
	*/
	void privateFromOtherClass() {
		// return class1Object.privateField;
	}
	int defalutFromOtherClass() {
		return class1Object.defalutField;
	
	}int publicFromOtherClass() {
		return class1Object.publicField;
	}
}
