package exercises14.exercise05.partner;

public class Partner {

	private String name;
	private Partner partner;

	public Partner(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		if(partner == null) {			
			if(this.partner != null) {
				Partner oldPartner = this.partner;
				this.partner = null;
				oldPartner.setPartner(null);				
			}
			return;
		}
		if(this.partner != null && this.partner != partner) {
			this.partner.setPartner(null);
		}
		this.partner = partner;
		if(this != partner.getPartner()) {
			partner.setPartner(this);
		}
	}

	public String toString() {
		if (partner == null) {
			return name;
		} else {
			return name + " -----> " + partner.getName();
		}
	}

	public static void main(String[] args) {
		Partner ole = new Partner("Ole");
		Partner kjersti = new Partner("Kjersti");
		
		Partner hans = new Partner("Hans");
		Partner therese = new Partner("Therese");
		
		ole.setPartner(kjersti);
		hans.setPartner(therese);
		System.out.println(ole.toString());
		System.out.println(kjersti.toString());
		System.out.println(hans.toString());
		System.out.println(therese.toString());
		kjersti.setPartner(null);
		System.out.println();
		System.out.println("______Kjersti gjør slutt med Ole______");
		System.out.println();
		System.out.println(ole.toString());
		System.out.println(kjersti.toString());	
		System.out.println(hans.toString());
		System.out.println(therese.toString());
		
		ole.setPartner(therese);
		System.out.println();
		System.out.println("______Therese dumper Hans for Ole______");
		System.out.println();
		System.out.println(ole.toString());
		System.out.println(kjersti.toString());	
		System.out.println(hans.toString());
		System.out.println(therese.toString());
		
		hans.setPartner(therese);
		System.out.println();
		System.out.println("______Hans tar tilbake Therese______");
		System.out.println();
		System.out.println(ole.toString());
		System.out.println(kjersti.toString());	
		System.out.println(hans.toString());
		System.out.println(therese.toString());
		
		hans.setPartner(ole);
		System.out.println();
		System.out.println("______Hans kommer ut av skapet og sjarmerer Ole______");
		System.out.println();
		System.out.println(ole.toString());
		System.out.println(kjersti.toString());	
		System.out.println(hans.toString());
		System.out.println(therese.toString());
		
		therese.setPartner(kjersti);
		System.out.println();
		System.out.println("______Therese og Kjersti blir sjalu______");
		System.out.println();
		System.out.println(ole.toString());
		System.out.println(kjersti.toString());	
		System.out.println(hans.toString());
		System.out.println(therese.toString());
	}
}
