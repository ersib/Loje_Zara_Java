package lojeprojekt;

// ScoreCell do te perdoret per te krijuar nje matrice objektesh me dimensione 17 x nr_lojtareve
// Krijohet nje tabele e rezultateve te hedhjeve per cdo lojtar
// Cdo qelize e matrices ka nje vlere integer, e cila do te mbaje vleren e pikeve te grumbulluara
// dhe gjithashtu nje vlere boolean selected e cila behet true nqs zgjidhet nga lojtari me ane te metodes setSelected() dhe eshte 
//false nqs nuk eshte zgjedhur nga lojtari
public class ScoreCell {

	private int vlera;
    private boolean selected;

public ScoreCell() {
	vlera=0;
	selected=false;
}
public ScoreCell(int piket) {
	vlera=piket;
	selected=false;
}
public int getVlera() {
	return vlera;
}
public boolean is_Selected() {
	return selected;
}
public void set_Selected() {
	selected=true;
}
public void setVlera(int vlera) {
	this.vlera = vlera;
}
public String toString() {
	return String.format("%s",vlera);
}
}
