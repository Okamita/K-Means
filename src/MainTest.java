import data.Data;
import mining.KmeansMiner;
import keyboardinput.Keyboard;
import data.OutOfRangeSampleSize;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int k=1, numIter;
		String s;
		char c;
		boolean run=true;
		KmeansMiner kmeans;
		Data data =new Data();
		System.out.println(data);
		do
		{
			System.out.println("Inserisci il numero di cluster:");
			k = Keyboard.readInt();
			try{
				kmeans=new KmeansMiner(k);
				numIter=kmeans.kmeans(data);
			}
			catch(OutOfRangeSampleSize e){
				System.out.println(e.getMessage());
				continue;
			}
			System.out.println("Numero di Iterazione:"+numIter);
			System.out.println(kmeans.getC().toString(data));
			do
			{
				System.out.println("Vuoi continuare? (y/n)");
				s = Keyboard.readString().toLowerCase();
				if(s.charAt(0)=='y' && s.length()==1)
					c='y';
				else if(s.charAt(0)=='n' && s.length()==1)
				{
					c='n';
					run=false;
				}
				else
					c='#';
			}while(c!='y' && c!='n');
		}while(run);
	}
}
