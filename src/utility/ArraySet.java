package utility;
import java.util.Arrays;

public class ArraySet {
	private boolean set[];
	private int size;

	public ArraySet (){
		set = new boolean[50];

		for(int i = 0;i < set.length; i++){
			set[i] = false;
		}
	}
	
	//Cambia in vero un valore dell'array, se la posizione di quest'ultimo é troppo piccola, viene incrementato la grandezza dell'array, inoltre restituisce vero se
	//il valore é nella posizione i é cambiato da falso a vero, e restituisce falso se il valore era vero di partenza e quindi non modificato
	public boolean add(int i){

		//Ingrandisce l'array se la posizione in cui modificare il valore é piú grande della lunghezza dell'array
		if(i >= set.length)
		{
			boolean temp[] = new boolean[i + 50]; 
			Arrays.fill(temp, false);
			System.arraycopy(set, 0, temp, 0, set.length);
			set = temp;
		}	

		//Salva in una variabile il valore di set[i] per controllare se é stato modificato, quindi se é vero, al return dará falso
		boolean added = set[i];
		set[i] = true;

		//Aumenta la dimensione di size in base a i. 
		if(i >= size){
			size = i + 1;
		}
		return !added;
	}
	
	public boolean delete(int i) {
        if (i < size) {

            boolean deleted = set[i];

            set[i] = false;

            if (i == size - 1) {
                //update size
                int j;
                for (j = size - 1; j >= 0 && !set[j]; j--) ;
                size = j + 1;
            }

            return deleted;
        }
        return false;
    }
	
	public boolean get(int i){
		return set[i];
	}
	
	public int[] toArray(){
		int a[] = new int[0];
		for(int i = 0; i < size; i++){
			if(get(i)){
				int temp[] = new int[a.length + 1];
				System.arraycopy(a, 0, temp, 0, a.length);
				a = temp;
				a[a.length-1] = i;
			}
		}
		return a;
	}
}
