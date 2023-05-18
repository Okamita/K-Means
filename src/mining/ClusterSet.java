package mining;
import data.Data;
import data.Tuple;

public class ClusterSet {
    private Cluster C[];
    private int i = 0;

    ClusterSet(int k){
        C = new Cluster[k];
    }

    void add(Cluster c){
        C[i] = c;
        i++;
    }

    Cluster get(int i){
        return C[i];
    }

    void initializeCentroids(Data data) {
        int[] centroidIndexes = data.sampling(C.length);
        for (int i = 0; i < centroidIndexes.length; i++) {
            Tuple centroidI = data.getItemSet(centroidIndexes[i]);
            add(new Cluster(centroidI));
        }
    }

    Cluster nearestCluster(Tuple tuple){

        double distance = tuple.getDistance((get(0).getCentroid()));;
        double temp = 0.0;
        int indexCluster = 0;

        for(int i = 0; i < C.length; i++){
            temp = tuple.getDistance((get(i).getCentroid()));

            if(temp < distance){
                distance  = temp;
                indexCluster = i;
            }
        }
        

        return C[indexCluster];
    }

    Cluster currentCluster(int id){

        for(int i = 0; i < C.length; i++ ){
            if(C[i].contain(id)){
                return C[i];
            }
        }

        return null;
    }

    void updateCentroids(Data data){
        for(int i = 0; i < C.length; i++ ){
            C[i].computeCentroid(data);
        }
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < C.length; i++) {
            if (C[i] != null) {
                str += i + ": " + C[i] + "\n";
            }
        }
        return str;
    }

    public String toString(Data data) {
        String str = "";
        for (int i = 0; i < C.length; i++) {
            if (C[i] != null) {
                str += i + ": " + C[i].toString(data) + "\n";
            }
        }
        return str;
    }
}
