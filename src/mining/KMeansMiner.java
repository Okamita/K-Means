package mining;

import data.Data;
import data.OutOfRangeSampleSize;
public class KmeansMiner {
    private final ClusterSet C;

    public KmeansMiner(int k) throws OutOfRangeSampleSize{
        C = new ClusterSet(k);
    }

    public ClusterSet getC(){
        return C;
    }

    public int kmeans(Data data) throws OutOfRangeSampleSize{
        int numberOfIterations=0;
        //STEP 1
        C.initializeCentroids(data);

        boolean changedCluster=false;
        do{
            numberOfIterations++;
            //STEP 2
            changedCluster=false;
            for(int i=0;i<data.getNumberOfExamples();i++){
                Cluster nearestCluster = C.nearestCluster(data.getItemSet(i));
                //System.out.println("nearestCluster:"+nearestCluster);
                Cluster oldCluster=C.currentCluster(i);
                //System.out.println("oldCluster:"+oldCluster);
                boolean currentChange=nearestCluster.addData(i);
                //System.out.println("CurrentChange:"+currentChange);
                if(currentChange)
                    changedCluster=true;
                //rimuovo la tupla dal vecchio cluster
                if(currentChange && oldCluster!=null)
                    //il nodo va rimosso dal suo vecchio cluster
                    oldCluster.removeTuple(i);
                }
            //STEP 3
            //System.out.println("Numero di Iterazione precedente:"+numberOfIterations);
            C.updateCentroids(data);
        }while(changedCluster);
        return numberOfIterations;
    }
}
