import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import java.util.HashMap;
import java.util.Collection;

class User {
    private String name;
    // private String[] connections;
    // private int connectionscount;
    private HashMap<String, String> connections;
    User (String name) {
        this.name = name;
        // connections = new String[10];
        connections = new HashMap<String, String>();
        // connectionscount = 0;
    }

    public String getName() {
        return name;
    }

    public Collection<String> getConnections() {
        return connections.values();
    }

    public int getSize() {
        return connections.size();
    }

    public void addConnection (String friend) {
        connections.put(name, friend);
    }
}

class Network {
    private HashMap<String, User> users;
    // private User[] users;
    // private int userscount;
    Network () {
        // users = new User[20];
        users = new HashMap<String, User>();
        // userscount = 0;
    }

    public void addUser(String uname) {
        users.put(uname, new User(uname));
        // users[userscount++] = new User(uname);
    }

    public void addConnectionToUser (String u1, String[] u2) {
        if (!users.containsKey(u1)) {
            addUser(u1);
        }
        User user = users.get(u1);
        for (int j = 0; j < u2.length; j++) {
            user.addConnection(u2[j]);
        }
        users.remove(u1);
        users.put(u1, user);
        // for (int i = 0; i < userscount; i++) {
        //     if (users[i].getName().equals(u1)) {
        //         for (int j = 0; j < u2.length; j++) {
        //             users[i].addConnection(u2[j]);
        //         }
        //     }
        // }
    }

    public User getUser(String uname) {
        // if (checkUser(uname)) {
        //     for (int i = 0; i < userscount; i++) {
        //         if (users[i].getName().equals(uname)) {
        //             return users[i];
        //         }
        //     }
        // }
        // return null;
        return users.get(uname);
    }

    public String[] getUserConnections(String uname) {
        if (users.containsKey(uname)) {
            // Collection<String> allUserConnections = users.get(uname).getConnections();
            return Arrays.toString(users.get(uname).getConnections());
        }
        return null;
    }

    public String[] getCommonConnections(String u1, String u2) {
        String[] u1Connections = users.get(u1).getConnections();
        String[] u2Connections = users.get(u2).getConnections();
        String[] commonConnections = new String[10];
        int commonConnectionsCount = 0;
        boolean flag = false;
        for (int i = 0; i < users.get(u1).getSize(); i++) {
            for (int j = 0; j < users.get(u1).getSize(); j++) {
                if (u1Connections[i].equals(u2Connections[j])) {
                    commonConnections[commonConnectionsCount++] = u1Connections[i];
                    flag = true;
                }
            }
        }
        if (flag) {
            String[] temp = new String[commonConnectionsCount];
            System.arraycopy(commonConnections, 0, temp, 0, commonConnectionsCount);
            return temp;
        } else {
            return null;
        }
    }

    // public boolean checkUser(String username) {
    //     for (int i = 0; i < userscount; i++) {
    //         if (users[i].getName().equals(username)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public String toString() {
        // Set<String> keys = users.keySet();
        String st = "";
        for (String key : users.keySet()) {
            // String[] temp = new String[users.size()];
            // System.arraycopy(users[i].getConnections(), 0, temp, 0, users[i].getSize());
            st += Arrays.toString(users.get(key).getConnections());
            // if (i < userscount - 1) {
                st += "\n";
            // }
        }
        return st;
    }
}


public class SolutionHM {
    public static void main(String[] args) {
        // File file = new File("SocialNetwork.txt");
        Scanner sc = null;
        try{
            sc = new Scanner(System.in);
            Network network = new Network();
            while(sc.hasNext()){
                String input = sc.nextLine();
                input = input.replace(".", "");
                input = input.replace(" is connected to ", "-");
                String[] inputArr = input.split("-");
                String[] connectionsArr = {};
                if (inputArr.length > 1) {
                    connectionsArr = inputArr[1].split(", ");
                }
                switch(inputArr[0]) {
                    case "getConnections":
                    System.out.println("Connections of " + connectionsArr[0] + " are: " + Arrays.toString(network.getUserConnections(connectionsArr[0])));
                    break;
                    case "getCommonConnections":
                    String[] commonConnections = network.getCommonConnections(connectionsArr[0], connectionsArr[1]);
                    if (commonConnections != null) {
                        System.out.println("Common Connections of " + connectionsArr[0] + " and " + connectionsArr[1] + " are: " + Arrays.toString(commonConnections));
                    } else {
                        System.out.println("No Common Connections for " + connectionsArr[0] + " and " + connectionsArr[1] + ".");
                    }
                    break;
                    case "display":
                    System.out.println(network);
                    break;
                    default:
                    network.addConnectionToUser(inputArr[0], connectionsArr);
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
