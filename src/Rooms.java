class SingleRoom extends Room {
    SingleRoom() {
        id = IncrementId();
        countBed = 1;
        Price = 20;
    }

    @Override
    public String toString() {
        return "this room id is " + id + "  and bethroom , bed for One peopel, and  price is + " + Price + " ";
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        id = IncrementId();
        countBed = 2;
        Price = 35;
    }

    public String toString() {
        return "this room id is " + id + "  and  Tv,Closet,bethroom , bed for two peopel, and  price is + " + Price + " ";
    }

}

class DeLyux extends Room {
    DeLyux() {
        id = IncrementId();
        countBed = 2;
        Price = 55;
        hasBar = true;
        sitingArea = true;
    }

    public String toString() {
        return "this room id is " + id + "  and has Tv,Closet,bethroom , bed for two peopel,Mini bar,siting Area and  price is + " + Price + " ";
    }

}


