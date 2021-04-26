import java.util.Scanner;

public class Main
{

    int	level=30;
    int	min_level=0;
    int	max_level=100;
    int targ = 80;



    boolean	stopped=false;

    public int nm() {
        return (int) (Math.random() * 100);
    }
    public int rn() {
        return (int) (Math.random() * 9);
    }
    public int gp(int min) {
        return (int) ((Math.random() * (10 - min)) + min);
    }
    public int cr() {
        return (int) (Math.random() * 5);
    }

    public ListSklad[] sklad = new ListSklad[10];
    class ListSklad {
        private int name;
        private int numbers;
        public ListSklad (int name, int numbers) {
            this.name = name;
            this.numbers = numbers;
        }

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }

        public int getNumbers() {
            return numbers;
        }

        public void setNumbers(int numbers) {
            this.numbers = numbers;
        }
    }


    public Main()
    {  ListSklad[] sklad = new ListSklad[11];
        int i;
        int j;
        for (i=0; i<10; i++){

            sklad[i]= new  ListSklad(i, nm()*3 );

            System.out.println("Товар "+ sklad[i].name + " имеется в количестве " + sklad[i].numbers);
        }
        Thread sups=new Thread() {
            public void run() {
                int s = 0;
                while (!stopped) {
                    for (int i = 0; i < 10; i++) {
                        s = s + sklad[i].getNumbers();

                    }

                        try {
                            if (s <= 10000) {
                                sleep(1000);
                                int t = rn();
                                int m = gp(t);
                                int g;
                                int n;


                                synchronized (sklad) {
                                    for (int i = t; i < m; i++) {
                                        g = rn();
                                        n = sklad[g].getNumbers();
                                        sklad[g].setNumbers(n + nm());
                                   
                                    }
                                }
                            } else
                                System.out.println ("Склад заполнен");
                                sleep(10000);
                        }
                        catch (Exception e) {
                            e.printStackTrace();


                        }

                }
                }

            };

        Thread cons=new Thread()
        {	public void run() {
            while (!stopped) {
            try {
                sleep(1000);
                int t = rn();
                int m = gp(t);
                int g;
                int c;
                int n;
                synchronized (sklad){
                    for (int i = t; i<m; i++){
                        g=rn();
                        c=nm();
                     
                        n= sklad[g].getNumbers();
                        n=n-c;
                        if ( n<0){
                            sklad[g].setNumbers(0);
                            System.out.println("Товар "+ sklad[g].getName() + " закончился");
                  
                        }
                    }

                }


            }
            catch(Exception e)
            {

                e.printStackTrace();
            }
        }


            }

        };

        Thread monitor=new Thread(){
            public void run() {
                while (!stopped) {
                    try {
                        sleep(3000);
                        for (int i = 0; i < 10; i++) {
                            System.out.println("Товар " + sklad[i].getName() + " имеется в количестве " + sklad[i].getNumbers());
                            //System.out.println("Товар "+ i + " имеется в количестве "+  k[i]);

                        }
                    } catch (Exception e) {
                        System.out.println("Error heigh: " + e);

                    }
                }
            }
        };

        for (i=0; i<cr(); i++){
            new Thread (sups).start();
        }
        for (i=0; i<cr(); i++){
            new Thread (cons).start();
        }
        monitor.start();


    }
    
    public static void main(String args[])
    {	System.out.println("Склад запущен");


        new Main();

    }

}
