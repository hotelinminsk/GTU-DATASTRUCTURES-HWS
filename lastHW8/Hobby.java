//Instead of using strings to represent hobbies, i use enums to represent hobbies. This way we can avoid typos and also make the code more readable.

public enum Hobby {
    //List of hobbies
    SPORTS(1), MUSIC(2), READING(3), TRAVELING(4), GAMING(5), COOKING(6), PAINTING(7), DANCING(8), WRITING(9), PHOTOGRAPHY(10),
    GARDENING(11), FISHING(12), HIKING(13), CYCLING(14), SWIMMING(15), SKIING(16), POTTERY(17), KNITTING(18), BAKING(19), YOGA(20);

    private final int value ;
    private Hobby(int value){
        this.value = value;
    }
    //Static method to check if two given hobbies are the same
    public static boolean isSame(Hobby h1,Hobby h2){
        return h1.getVal() == h2.getVal();
    }
    public static Hobby getHobby(int value){
        for(Hobby h : Hobby.values()){
            if(h.getVal() == value){
                return h;
            }
        }
        throw new IllegalArgumentException("No such hobby exists.");
    }

    //Hobby value getter
    public int getVal(){
        return value;
    }
}
