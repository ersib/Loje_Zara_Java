package lojeprojekt;
import java.util.Arrays;

public class Lojtar {

    private  String Emri;
	private  String Mbiemri;
	private String username;
	private int mosha;
	private int piket;
	private lidhjaDB db;
	
	public Lojtar(String e,String m,String uname,int mosha,int piket) {
		this.Emri=e;
		this.Mbiemri=m;
		this.mosha=mosha;
		this.piket=piket;
		this.username=uname;
		db=new lidhjaDB();
	}
	public int getPiket() {
		return piket;
	}
	public void setPiket(int piket) {
		this.piket = piket;
	}
	public Lojtar() {
	
	}

	public  String getEmri() {
		return Emri;
	}

	public  void setEmri(String emri) {
		Emri = emri;
		
	}

	public  String getMbiemri() {
		return Mbiemri;
	}

	public  void setMbiemri(String mbiemri) {
		Mbiemri = mbiemri;
	}

	public int getMosha() {
		return mosha;
	}

	public void setMosha(int mosha) {
		this.mosha = mosha;
	}
	public String toString() {
		return this.Emri+" "+this.Mbiemri+"";
	}

	public void updatePiket(int piketReja) {
		
		this.piket=(piketReja>this.piket)?piketReja:piket;
		
		this.db.updatePiketMax(this.username, piketReja);
	}
	
}
