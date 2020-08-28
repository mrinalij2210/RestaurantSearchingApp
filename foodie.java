package mini_project;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class restaurant
{
    String name;   //name of restaurant
    float lower,upper; //lower=lower range of budget //upper=upper range of budget
    restaurant next; 
    String tel;  //tel=telephone number
    int cuisine;
    restaurant(String n,float l,float u,String t,int c)//parameterized constructor
    {
        name=n;
        lower=l;
        cuisine=c;
        upper=u;
        tel=t;
        next=null;
    }
}

class wallet
{
    restaurant head[]=new restaurant[6]; // table size is 6
    void create() //create function
    {
        insert("Yummy!",500,1000,2,"8467163216");
        insert("Food_Zone",500,1000,3,"7539514628");
        insert("McDonald's",1000,1500,4,"8949533912");
        insert("Chowpaty",0,500,3,"9783786451");
        insert("Pizza_Hut",1000,1500,4,"8239319850");
        insert("Taj",2000,2500,1,"9934532615");
        insert("Mariotte",2000,2500,1,"9936268059");
        insert("Vivanta",1500,2000,2,"7020458000");
        insert("The_Dragon",2500,3000,1,"9494143451");
        insert("Konichiwa",2500,3000,5,"7782456239");
    }
    void insert(String name,float low,float upp,int cus,String tel)//insert function
    {
        Scanner sc=new Scanner(System.in);
        restaurant temp;
        temp=new restaurant(name,low,upp,tel,cus);
        int adr=(int)low/500; //address is calculated based on budget
        if(head[adr]==null)
            head[adr]=temp;
        else
        {
            restaurant ptr=head[adr];
            while(ptr.next!=null)
                ptr=ptr.next;
            ptr.next=temp;
        }
    }
    void search() // search function
    {
        Scanner sc=new Scanner(System.in);
        char ch;
        do{
        System.out.print("\nEnter no. of guests: ");
        int n=sc.nextInt();
        System.out.print("Enter total budget (<3000 per head): ");
        float budget=sc.nextFloat();
        int temp=(int)budget/n; //budget divided by no. of people
        int adr=temp/500; //for mapping
        System.out.println("\nSuggested Restaurants:");
        if(head[adr]==null)
        System.out.println("       NONE AVAILABLE");
        restaurant ptr=head[adr];
        while(ptr!=null){
            System.out.print("\nName: "+ptr.name+"\tCost per head: "+ptr.lower+"-"+ptr.upper+"\tContact no.: "+ptr.tel+"\tcuisine:");
            if(ptr.cuisine==1)
                System.out.print("Chinese");
            else if(ptr.cuisine==2)
                System.out.print("South Indian");
            else if(ptr.cuisine==3)
                System.out.print("North Indian");
            else if(ptr.cuisine==4)
                System.out.print("American");
            else if(ptr.cuisine==5)
                System.out.print("Dessert");
            ptr=ptr.next;
        }
        System.out.print("\nDo you want to search again? (y/n): ");
        ch=sc.next().charAt(0);
        }while(ch=='y');
    }
    int del(String n,float l) //delete function
    {
        int adr=(int)l/500; //lower range divided by 500
        restaurant ptr=head[adr];
        int flag=0;
        while(ptr!=null){
            if(n.equalsIgnoreCase(ptr.name)){
                flag=1;
                break;
            }
            ptr=ptr.next;
        }
        if(flag==1){
            restaurant parent=head[adr];
            restaurant p=head[adr];
            while(p!=null){
                if(p==ptr){
                    if(p==parent)
                        head[adr]=p.next;
                    else
                        parent.next=p.next;
                    p.next=null;
                }
                p=p.next;
            }
        }
        return flag;
    }
}
class cuisine
{
    restaurant head[]=new restaurant[5]; //table size is 5 
    void create() // create function
    {
        insert("Yummy!",500,1000,2,"8467163216");
        insert("Food_Zone",500,1000,3,"7539514628");
        insert("McDonald's",1000,1500,4,"8949533912");
        insert("Chowpaty",0,500,3,"9783786451");
        insert("Pizza_Hut",1000,1500,4,"8239319850");
        insert("Taj",2000,2500,1,"9934532615");
        insert("Mariotte",2000,2500,1,"9936268059");
        insert("Vivanta",1500,2000,2,"7020458000");
        insert("The_Dragon",2500,3000,1,"9494143451");
        insert("Konichiwa",2500,3000,5,"7782456239");
    }
    void insert(String name,float low,float upp,int cus,String tel)//insert function
    {
        restaurant temp=new restaurant(name,low,upp,tel,cus);
        int adr=cus%5; // address is calculated by cus%5
        if(head[adr]==null)
            head[adr]=temp;
        else{
            restaurant ptr=head[adr];
            while(ptr.next!=null)
                ptr=ptr.next;
            ptr.next=temp;
        }
    }
    void search_by_name() // search by name function
    {
        restaurant ptr;
        int flag=0;
        Scanner sc=new Scanner(System.in);
        char ch;
            System.out.println("\nEnter the name of the restaurant : ");
            String n=sc.next();
            int r=0;
            while(head[r]!=null&&r<5)
            {
                if(head[r].name.equalsIgnoreCase(n)==true)
                {
                    System.out.println("\nName: "+head[r].name+"\tCost per head: "+head[r].lower+"-"+head[r].upper+"\tContact no.: "+head[r].tel);
                flag=1;
                break;
                }
                else{
                      ptr=head[r];
                      while(ptr!=null){
                      if(ptr.name.equalsIgnoreCase(n)==true){
                      System.out.println("\nName: "+ptr.name+"\tCost per head: "+ptr.lower+"-"+ptr.upper+"\tContact no.: "+ptr.tel);
                      flag=1;
                      break;
                      }
                      ptr=ptr.next;
                      }
               }
            r++;
            if(flag==1||r==5)
                break;
          }
            if(flag==0)
                System.out.println("\nThis restaurant does not currently exist on our page!");
        
    }

    void search()//search by cusine function
    {
        Scanner sc=new Scanner(System.in);
        char cont;
        do{
        System.out.print("Enter cuisine:\n1.Chinese\n2.South Indian\n3.North Indian\n4.American\n5.Dessert\nChoice: ");
        int ch=sc.nextInt();
        int adr=ch%5;
        System.out.println("\nSuggested Restaurants:");
        if(head[adr]==null)
            System.out.println("        NONE AVAILABLE");
        restaurant ptr=head[adr];
        while(ptr!=null){
            System.out.print("\nName: "+ptr.name+"\tCost per head: "+ptr.lower+"-"+ptr.upper+"\tContact no.: "+ptr.tel+"\tcuisine:");
            if(ptr.cuisine==1)
                System.out.print("Chinese");
            else if(ptr.cuisine==2)
                System.out.print("South Indian");
            else if(ptr.cuisine==3)
                System.out.print("North Indian");
            else if(ptr.cuisine==4)
                System.out.print("American");
            else if(ptr.cuisine==5)
                System.out.print("Dessert");
            ptr=ptr.next;
        }
        System.out.print("\nDo you want to search again? (y/n): ");
        cont=sc.next().charAt(0);
        }while(cont=='y');
    }
    int del(String n,int cu) //delete function
    {
        int adr=cu%5; //cusine%5
        restaurant ptr=head[adr];
        int flag=0;
        while(ptr!=null){
            if(n.equalsIgnoreCase(ptr.name)){
                flag=1;
                break;
            }
            ptr=ptr.next;
        }
        if(flag==1){
            restaurant parent=head[adr];
            restaurant p=head[adr];
            while(p!=null){
                if(p==ptr){
                    if(p==parent)
                        head[adr]=p.next;
                    else
                        parent.next=p.next;
                    p.next=null;
                }
                p=p.next;
            }
        }
        return flag;
    }
}
public class foodie {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    	//Opens the restaurant logo in a new window. Uncomment and update the path of image (if available).
    	/*JFrame window=new JFrame();
        window.setSize(1240,800);
        window.setTitle("FOODIE : THE PLACE OF YOUR CHOICE");
        window.setLayout(new BorderLayout());
        window.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Mrinali\\Desktop\\Foodie.jpg")));
        window.setLayout(new FlowLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);*/
    	
    	wallet c=new wallet();
        cuisine cus=new cuisine();
        c.create();
        cus.create();
        Scanner sc=new Scanner(System.in);
        System.out.println("____________________________________WELCOME TO FOODIE_____________________________________");
        char cont;
        do{
        System.out.println("\nAre you a:\n1.Restaurant Partner\n2.Customer");   //use app as customer or restaurant owner
        System.out.print("Choice: ");
        int ch=sc.nextInt();
        System.out.println("\n__________________________________________________________________________________________");
        switch(ch) //switch case
        {
        case 2: int choc;   //in customer mode: various restaurant searching options
        do{
            System.out.print("..........MENU..........\n1.Search by budget\n2.Search by cuisine\n3.Search by name\n4.Exit\nChoice:");
        choc=sc.nextInt();
        switch(choc)  //nested switch case
        {
        case 1: c.search();
        break;
        case 2: cus.search();
        break;
        case 3: cus.search_by_name();
        break;
        case 4: System.out.println("\nExiting...");
        break;
        default: System.out.println("INVALID INPUT");
        }
        }while(choc!=4);
        break;
        case 1:int x;        //in restaurant owner mode: register or unregister a restaurant
        do{
            System.out.print("\n..........MENU..........\n1.Register your restaurant with us\n2.Unregister your restaurant\n3.Exit\nChoice: ");
        x=sc.nextInt();
        switch(x)//nested switch case
        {
        case 1: 
            System.out.print("\nEnter restaurant name: ");
            String name=sc.next();
            System.out.println("Enter price range per head:\n1. Less than 500\n2. 500-1000\n3. 1000-1500\n4. 1500-2000\n5. 2000-2500\n6. 2500-3000");
            System.out.print("Choice: ");
            int cho=sc.nextInt();
            int l=3000,u=3000;
            switch(cho){
            case 1: l=0; u=500; 
            break; 
            case 2: l=500; u=1000;
            break;
            case 3: l=1000; u=1500;
            break;
            case 4: l=1500; u=2000;
            break;
            case 5: l=2000; u=2500;
            break;
            case 6: l=2500; u=3000;
            break;
            default: System.out.print("INVALID INPUT");
            }
            System.out.print("Enter contact no.: ");
            String tel=sc.next();
            while(tel.length()>10||tel.length()<10){
                System.out.print("\nINVALID NUMBER\nRe-Enter: ");
                tel=sc.next();
            }
            System.out.print("Enter cuisine:\n1.Chinese\n2.South Indian\n3.North Indian\n4.American\n5.Dessert\nChoice: ");
            int cu=sc.nextInt();
            cus.insert(name, l, u, cu, tel);
            c.insert(name,l,u,cu,tel);
            System.out.print("Congratulations! You have been registered with us!\nWelcome to the FOODIE FAMILY!!! ^_^");
        break;
        case 2: 
            System.out.print("\nEnter name of restaurant: ");
            String n=sc.next();
            System.out.println("Enter cost per head: "); 
            float lo=sc.nextFloat();
            System.out.print("Enter cuisine:\n1.Chinese\n2.South Indian\n3.North Indian\n4.American\n5.Dessert\nChoice: ");
            int cusi=sc.nextInt();
            int flag1=c.del(n,lo);
            int flag2=cus.del(n, cusi);
            if(flag1==0&&flag2==0)
                System.out.println("\nSorry! You are not registered with us! ('~')");
            else if(flag1==1&&flag2==1)
                System.out.println("Your restaurant has been unregistered!\nThank You for working with us! :)");
        break;
        case 3: System.out.print("\nExiting...");
        break;
        default: System.out.print("\nINVALID INPUT");
        }
        }while(x!=3);
        break;
        default: System.out.print("\nINVALID INPUT");
        }
        System.out.println("\n__________________________________________________________________________________________");
        System.out.println("\nDo you want to switch user? (y/n): ");
        cont=sc.next().charAt(0);
    }while(cont=='y');
        System.out.println("\n__________________________________________________________________________________________");
        System.out.print("\nHappy to help! Please visit again! ^_^");
    }
}