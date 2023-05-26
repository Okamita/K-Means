package mining;

import java.util.*;
import utility.ArraySet;
import data.Data;
import data.Tuple;

import java.util.Objects;

public class Cluster {
	private final Tuple centroid;
	private final Set<Integer> clusteredData;

	Cluster(){
		centroid=null;
		clusteredData=new HashSet<Integer>();
	}

	Cluster(Tuple centroid){
		this.centroid=centroid;
		clusteredData=new HashSet<Integer>();
		
	}
		
	Tuple getCentroid(){
		return centroid;
	}
	
	void computeCentroid(Data data){
		for(int i = 0; i< Objects.requireNonNull(centroid).getLength(); i++){
			centroid.get(i).update(data,clusteredData);
		}
	}
	//return true if the tuple is changing cluster
	boolean addData(int id){
		return clusteredData.add(id);
	}
	
	//verifica se una transazione e' clusterizzata nell'array corrente
	boolean contain(int id){
		return clusteredData.contains(id);
	}
	
	//remove the tuplethat has changed the cluster
	void removeTuple(int id){
		clusteredData.remove(id);
	}
	
	public String toString(){
		StringBuilder str= new StringBuilder("Centroid=(");
		for(int i = 0; i< Objects.requireNonNull(centroid).getLength(); i++)
			str.append(centroid.get(i));
		str.append(")");
		return str.toString();
	}

	public String toString(Data data){
		StringBuilder str= new StringBuilder("Centroid=(");
		for(int i = 0; i< Objects.requireNonNull(centroid).getLength(); i++)
			str.append(centroid.get(i)).append(" ");
		str.append(")\nExamples:\n");
		for (int i:clusteredData)
		{
			str.append("[");
			for(int j=0;j<data.getNumberOfAttributes();j++)
				str.append(data.getAttributeValue(i, j)).append(" ");
			str.append("] dist=").append(getCentroid().getDistance(data.getItemSet(i))).append("\n");
		}
		str.append("AvgDistance=").append(getCentroid().avgDistance(data, clusteredData)).append("\n");
		return str.toString();
	}
}
