package data;
class DiscreteItem extends Item {
    
    DiscreteItem(DiscreteAttribute attribute, String value){
        super(attribute,value);
    }

    double distance(Object a){
        int distance;
        if(getValue().equals(a)){
            distance = 0;
        }else{
            distance = 1;
        }
        return distance;
    }
}
