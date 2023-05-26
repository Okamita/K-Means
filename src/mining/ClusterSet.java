package mining;
import data.Data;
import data.OutOfRangeSampleSize;
import data.Tuple;

public class ClusterSet {
    private final Cluster[] C;
    private int i=0;

    ClusterSet(int k) throws OutOfRangeSampleSize{
        try
        {
           C = new Cluster[k]; 
        }
        catch(NegativeArraySizeException e)
        {
            throw new OutOfRangeSampleSize("C'è un'errore nella creazione dell'array di cluster");
        }
    }

    void add(Cluster c) {
        C[i++] = c;
    }

    Cluster get(int i) {
        return C[i];
    }

    void initializeCentroids(Data data) throws OutOfRangeSampleSize{
        int[] centroidIndexes = data.sampling(C.length);
        for (int i = 0; i < C.length; i++) {
            Tuple centroidI=data.getItemSet(centroidIndexes[i]);
            add(new Cluster(centroidI));
        }
    }

    Cluster nearestCluster(Tuple tuple){
        Cluster nearestCluster=null;
        double minDistance=Double.MAX_VALUE;
        for (Cluster cluster : C) {
            double distance = tuple.getDistance(cluster.getCentroid());
            if (distance < minDistance) {
                minDistance = distance;
                nearestCluster = cluster;
            }
        }
        return nearestCluster;
    }

    Cluster currentCluster(int id){
        for (Cluster cluster : C)
            if (cluster.contain(id))
                return cluster;
        return null;
    }

    void updateCentroids(Data data){
        for (Cluster cluster : C) cluster.computeCentroid(data);
    }

    public String toString(){
        StringBuilder str= new StringBuilder();
        for (Cluster cluster : C) str.append(cluster).append("\n");
        return str.toString();
    }

    public String toString(Data data){
        StringBuilder str= new StringBuilder();
        for(int i=0;i<C.length;i++){
            if(C[i]!=null){
                str.append(i).append(":").append(C[i].toString(data)).append("\n");
            }
        }
        return str.toString();
    }
}
