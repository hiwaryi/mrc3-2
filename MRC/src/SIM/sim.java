package SIM;

public class sim {
    public void hello(){

    }

    public static void main(String[] args) {
        //System.out.println("hi");
        robotInterface RI = new robotInterface();
        RI.print();
    }
}

class robotInterface{
   public void print(){
       System.out.println("hihi");
   }
}