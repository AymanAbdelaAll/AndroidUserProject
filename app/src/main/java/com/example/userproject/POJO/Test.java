package com.example.userproject.POJO;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Entry class ,crud class to test my other class and implements the other class
 */
public class Test {
    private static ArrayList<Geo> geos=new ArrayList<Geo>(){{
        add(new Geo(-37.3159, 81.1496));
        add(new Geo(-43.9509,-34.4618));
        add(new Geo(-68.6102,-47.0653 ));
        add(new Geo(29.4572,-164.2990));
        add(new Geo(-31.8129,62.5342));
        add(new Geo(-71.4197,71.7478));
        add(new Geo(24.8918,21.8984));
        add(new Geo(-14.3990,-120.7677));
        add(new Geo(24.6463,-168.8889));
        add(new Geo(-38.2386,57.2232));
    }};
    private static ArrayList<Address> addresses=new ArrayList<Address>(){{
        add(Address.buildGeo(geos.get(0), "Kulas Light", "Apt. 556", "Gwenborough", "92998-3874"));
        add(Address.buildGeo(geos.get(1),"Victor Plains","Suite 879","Wisokyburgh","90566-7771"));
        add(Address.buildGeo(geos.get(2),"Douglas Extension","Suite 847","McKenziehaven","59590-4157"));
        add(Address.buildGeo(geos.get(3),"Hoeger Mall","Apt. 692","South Elvis","53919-4257"));
        add(Address.buildGeo(geos.get(4),"Skiles Walks","Suite 351","Roscoeview","33263"));
        add(Address.buildGeo(null,"Norberto Crossing","Apt. 950","South Christy","23505-1337"));
        add(Address.buildGeo (null,"Rex Trail","Suite 280","Howemouth","58804-1099"));
        add(Address.buildGeo(null,"Ellsworth Summit","Suite 729","Aliyaview","45169"));
        add(Address.buildGeo(null,"Dayna Park","Suite 449","Bartholomebury","76495-3109"));
        add(Address.buildGeo(null,"Kattie Turnpike","Suite 198","Lebsackbury","31428-2261"));

    }};
    private static ArrayList<Company> companies=new ArrayList<Company>(){{
        add(new Company("Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets"));
        add(new Company("Deckow-Crist","Proactive didactic contingency","synergize scalable supply-chains"));
        add(new Company("Romaguera-Jacobson","Face to face bifurcated interface","e-enable strategic applications"));
        add(new Company("Robel-Corkery","Multi-tiered zero tolerance productivity","transition cutting-edge web services"));
        add( new Company("Keebler LLC","User-centric fault-tolerant solution","revolutionize end-to-end systems"));
        add( new Company("Considine-Lockman","Synchronised bottom-line interface","e-enable innovative applications"));
        add(new Company("Johns Group","Configurable multimedia task-force","generate enterprise e-tailers"));
        add( new Company("Abernathy Group","Implemented secondary concept","e-enable extensible e-tailers"));
        add( new Company("Yost and Sons","Switchable contextually-based project","aggregate real-time technologies"));
        add(new Company("Hoeger LLC","Centralized empowering task-force","target end-to-end models"));
    }};

    private static ArrayList <User> users=new ArrayList<User>(){
        {
            add(new User(addresses.get(0), companies.get(0), "Leanne Graham", "Bret",
                    "Sincere@april.biz", "1-770-736-8031 x56442", "hildegard.org"));

            add(new User(addresses.get(1), companies.get(1), "Ervin Howell","Antonette",
                    "Shanna@melissa.tv", "010-692-6593 x09125","anastasia.net"));

            add(new User(addresses.get(2), companies.get(2), "Clementine Bauch","Samantha",
                    "Nathan@yesenia.net","1-463-123-4447","ramiro.info"));

            add(new User(addresses.get(3),companies.get(3), "Patricia Lebsack","Karianne",
                    "Julianne.OConner@kory.org","493-170-9623 x156","kale.biz"));

            add(new User(addresses.get(4),companies.get(4), "Chelsey Dietrich","Kamren",
                    "Lucio_Hettinger@annie.ca","(254)954-1289","demarco.info"));

            add(new User(addresses.get(5),companies.get(5), "Mrs. Dennis Schulist","Leopoldo_Corkery",
                    "Karley_Dach@jasper.info","1-477-935-8478 x6430","ola.org"));

            add(new User(addresses.get(6), companies.get(6), "Kurtis Weissnat","Elwyn.Skiles",
                    "Telly.Hoeger@billy.biz","210.067.6132","elvis.io"));

            add(new User(addresses.get(7),companies.get(7) , "Nicholas Runolfsdottir V","Maxime_Nienow",
                    "Sherwood@rosamond.me","586.493.6943 x140","jacynthe.com"));

            add(new User(addresses.get(8),companies.get(8), "Glenna Reichert","Delphine",
                    "Chaim_McDermott@dana.io","(775)976-6794 x41206","conrad.com"));

            add(new User(addresses.get(9), companies.get(9), "Clementina DuBuque","Moriah.Stanton",
                    "Rey.Padberg@karina.biz", "024-648-3804", "ambrose.net"));
        }
    };

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        boolean iterate=true;
        while (iterate) {
            System.out.println("Enter The Number Of Opearation you want :");
            System.out.println("Enter 0 to exit .");
            System.out.println("Enter 1 to show the Users .");
            System.out.println("Enter 2 to delete user .");
            System.out.println("Enter 3 to update the User");
            int input=scanner.nextInt();
            int id;
            switch (input) {
                case 0:
                    iterate=false; // to exit the loop and input choise
                    break;
                case 1:
                    showUsers(users);
                    break;
                case 2:
                    System.out.println("Enter The Id Of user you want to Delete it: ");
                    id = scanner.nextInt();
                    removeUsers(users, users.get(id - 1));
                    break;
                case 3:
                    System.out.println("Enter The Id Of user you want to update: ");
                    id = scanner.nextInt();
                    updateUserCli(users,id);
                    break;
                default:
                    System.out.println("You Enter invalid number .");
            }
        }
    }

    public static void updateUserCli(ArrayList<User>users,int id){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter 1 to update name .");
        System.out.println("Enter 2 to update username .");
        System.out.println("Enter 3 to update email .");
        System.out.println("Enter 4 to update website .");
        System.out.println("Enter 5 to update phone .");
        System.out.println("Enter 6 to update Address .");
        System.out.println("Enter 7 to update Company .");
        int update=scanner.nextInt();
        scanner.nextLine();

        switch (update){
            case 1:
                System.out.println("Enter The new name of user :");
                String name = scanner.nextLine();
                updateUsersName(users, name, id-1);
                break;
            case 2:
                System.out.println("Enter The new username of user :");
                String username=scanner.nextLine();
                updateUsersUserName(users,username,id-1);
                break;
            case 3:
                System.out.println("Enter The new Email of user :");
                String email=scanner.nextLine();
                updateUsersEmail(users,email,id-1);
                break;
            case 4:
                System.out.println("Enter The new website of user :");
                String website=scanner.nextLine();
                updateUsersWebsite(users,website,id-1);
                break;
            case 5:
                System.out.println("Enter The new phone of user :");
                String phone=scanner.nextLine();
                updateUsersPhone(users,phone,id-1);
                break;
            case 6:
                System.out.println("Enter The new Address-street of user :");
                String street=scanner.nextLine();
                System.out.println("Enter The new Address-suite of user : ");
                String suite=scanner.nextLine();
                System.out.println("Enter The new Address-city of user :");
                String city=scanner.nextLine();
                System.out.println("Enter The new Address-zipcode of user :");
                String zipcode=scanner.nextLine();
                User user=users.get(id);
                Address userAddress=user.getAddress();
                if (userAddress instanceof GeoAddress){
                    System.out.println("Enter The new Geo-lat of user :");
                    Double lat=scanner.nextDouble();
                    System.out.println("Enter The new Geo-lng of user :");
                    Double lng=scanner.nextDouble();
                    Geo geo=new Geo(lat,lng);
                    updateUsersAddress(users, (Address.buildGeo(geo,street,suite,city,zipcode)) ,id-1);
                }else{
                    updateUsersAddress(users,Address.buildGeo(null,street,suite,city,zipcode),id-1);
                }
                break;
            case 7:
                System.out.println("Enter The new Company-name of user :");
                String companyName=scanner.nextLine();
                System.out.println("Enter The new Company-catchphrase of user :");
                String catchphrase=scanner.nextLine();
                System.out.println("Enter The new Company-bs of user :");
                String bs =scanner.nextLine();
                updateUsersCompany(users,new Company(companyName,catchphrase,bs),id-1);
                break;
            default:
                System.out.println("You Enter invalid number .");
        }
    }
    public static User removeUsers(@NotNull ArrayList<User> users,@NotNull User user){
        boolean isremoved= users.remove(user);
        if (isremoved){
            System.out.println("The User Deleted .");
            return user;
        }else {
            System.out.println("The User Doesn't Deleted ,And it's Not in List.");
            return null;
        }
    }

    public static void showUsers(@NotNull ArrayList<User> users){
        System.out.println("The list of Users:");
        for (User user:users) {
            System.out.println(user.toString());
            System.out.println();
        }
    }

    public static boolean updateUsersName(@NotNull ArrayList<User> users, @NotNull String name, int index){
        if (index<users.size()&&index>=0){
            User user =users.get(index);
            user.setName(name);
            System.out.println("The Name Of User Updated .");
            return true;
        }else {
            System.out.println("The Name Of User doesn't Updated .");
            return false;
        }
    }

    public static boolean updateUsersUserName(@NotNull ArrayList<User> users, @NotNull String username, int index){
        if (index<users.size()&&index>=0){
            User user= users.get(index);
            user.setUsername(username);
            System.out.println("The Username Updated .");
            return true;
        }else {
            System.out.println("The Username doesn't Updated .");
            return false;
        }
    }

    public static boolean updateUsersEmail(@NotNull ArrayList<User> users, @NotNull String email, int index){
        if (index<users.size()&&index>=0){
            User user = users.get(index);
            user.setUsername(email);
            System.out.println("The User Email Updated .");
            return true;
        }else {
            System.out.println("The User Email Doesn't Updated .");
            return false;
        }
    }

    public static boolean updateUsersPhone(@NotNull ArrayList<User> users, @NotNull String phone, int index){
        if (index<users.size()&&index>=0){
            User user = users.get(index);
            user.setUsername(phone);
            System.out.println("The User Phone Updated .");
            return true;
        }else {
            System.out.println("The User Phone Doesn't Updated .");
            return false;
        }
    }

    public static boolean updateUsersWebsite(@NotNull ArrayList<User> users, @NotNull String website, int index){
        if (index<users.size()&&index>=0){
            User user = users.get(index);
            user.setUsername(website);
            System.out.println("The User Website Updated .");
            return true;
        }else {
            System.out.println("The User Website Doesn't Updated .");
            return false;
        }
    }

    public static boolean updateUsersAddress(@NotNull ArrayList<User> users, @NotNull Address address, int index){
        if (index<users.size()&&index>=0){
            User user = users.get(index);
            user.setAddress(address);
            System.out.println("The Address Updated .");
            return true;
        }else {
            System.out.println("The Address Doesn't Updated");
            return false;
        }
    }

    public static boolean updateUsersCompany(@NotNull ArrayList<User> users, @NotNull Company company, int index){
        if (index<users.size()&&index>=0){
            User user = users.get(index);
            user.setCompany(company);
            System.out.println("The Company Object Is Updated .");
            return true;
        }else {
            System.out.println("The Company Object Isn't Updated .");
            return false;
        }
    }
}

