package lojeprojekt;


public class Kategori {
private int piket;
private int sameFace(int face,int d1,int d2,int d3,int d4,int d5) {
	int count=0;
	if(d1==face)
		count+=face;
	if(d2==face)
		count+=face;
	if(d3==face)
		count+=face;
	if(d4==face)
		count+=face;	
	if(d5==face)
		count+=face;
	
	return count;
}
private int ThreeFace(int frequency,int d1,int d2,int d3,int d4,int d5) {
	int[] d=new int[5];
	d[0]=d1;d[1]=d2;d[2]=d3;d[3]=d4;d[4]=d5;
	int count;
	for(int i=0;i<5;i++)
	{
		count=0;
		for(int j=0;j<5;j++)
		{
			if(d[i]==d[j])
				count++;
		}
		if (count>=frequency) {
		return d1+d2+d3+d4+d5;
		}
	}
	return 0;
	
}
private int threeAndtwo(int d1,int d2,int d3,int d4,int d5) {
	int[] d=new int[5];
	d[0]=d1;d[1]=d2;d[2]=d3;d[3]=d4;d[4]=d5;
	int count=0,count2;
	for(int i=0;i<5;i++)
	{
		if(d[0]==d[i])
			count++;
	}
	
	if(count==1)
	return 0;
	
	else if(count==2)
	{
		count2=0;
		for(int i=0;i<5;i++)
		{
			if(d[i]!=d[0]) {
				int z=i;
				while(i<5) {
					if(d[z]==d[i])
						count2++;
					i++;
				}
				if(count2==3)
					return 25;
					else
						return 0;
			}
		}
		return 0;
		
	}
	else if(count==3)
	{
		count2=0;
		for(int i=0;i<5;i++)
		{
			if(d[i]!=d[0]){
				int z=i;
				while(i<5) {
					if(d[z]==d[i])
						count2++;
					i++;
				}
				if(count2==2)
					return 25;
					else
						return 0;
			}
		}
		return 0;
	}
	else
		return 0;
}
private int oneAFTERanother(int d1,int d2,int d3,int d4,int d5) {
		if(d2==d1+1 && d3==d2+1 && d4==d3+1)
			return 30;
		else if(d3==d2+1 && d4==d3+1 && d5==d4+1)
            return 30;
		else if(d2==d1+1 && d3==d2+1 && d4==d3+1 && d5==d4+1)
			return 40;
		else
			return 0;
}
private int allSame(int d1,int d2,int d3,int d4,int d5) {
	
		if(d1==d2 && d1==d3 && d1==d3 && d1==d4 && d1==d5)
			return 50;
		else
			return 0;
	
}

// Versionet jane nga 1 deri tek 13 per 13 kategorite e lojes
/*
 Nga 1 deri ne 6 perdoret funksioni sameFace()
 Per 7 dhe 8 perdoret ThreeFace() => per kategorite "Tre me nje vlere" dhe "Kater me nje vlere"
 Per 9 perdoret threeAndtwo()
 Per 10 dhe 11 perdoret oneAFTERanother() => per kategorite "Kater njepasnje" dhe "Pese njepasnje"
 Per 12 perdoret allSame() => kategoria Te gjitha njesoj
 Per 13 mblidhen vlerat e zarave per cdo zar => CdoRast()
 */
public int llogaritPiket(int version,int d1,int d2,int d3,int d4,int d5) {
	
	if(version>=1 && version<=6)
	return sameFace(version,d1,d2,d3,d4,d5);
	else {
		if(version==7)
			return ThreeFace(version-4,d1,d2,d3,d4,d5);
		if(version==8)
			return ThreeFace(version-4,d1,d2,d3,d4,d5);
		if(version==9)
			return threeAndtwo(d1,d2,d3,d4,d5);
		if(version==10)
			return oneAFTERanother(d1,d2,d3,d4,d5);
		if(version==11)
			return oneAFTERanother(d1,d2,d3,d4,d5);
		if(version==12)
			return allSame(d1,d2,d3,d4,d5);
		if(version==13)
			return d1+d2+d3+d4+d5;
	}
	return piket;
}
}
