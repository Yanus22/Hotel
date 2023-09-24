class SingleRoom extends Room {
    SingleRoom() {
        countBed = 1;
        Price = 20;
    }     @Override
    public String toString() {
       return  "this room has Tv,Closet,bethroom , bed for One peopel, and  price is + " + Price  + " ";
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        countBed = 2;
        Price = 35;
    }
    public String toString() {
        return  "this room has Tv,Closet,bethroom , bed for two peopel, and  price is + " + Price  + " ";
    }

}
class DeLyux extends Room {
    DeLyux() {
        countBed = 2;
        Price = 55;
        hasBar = true;
        sitingArea = true;
    }
    public String toString() {
        return  "this room has Tv,Closet,bethroom , bed for two peopel,Mini bar,siting Area and  price is + " + Price  + " ";
    }

}
