import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

class User {
    private String name;
    private String[] connections;
    private int connectionscount;
    User (String name) {
        this.name = name;
        connections = new String[10];
        connectionscount = 0;
    }

    public String getName() {
        return name;
    }

    public String[] getConnections() {
        return connections;
    }

    public int getSize() {
        return connectionscount;
    }

    public void addConnection (String uname) {
        connections[connectionscount++] = uname;
    }
}

class Network {
    private User[] users;
    private int userscount;
    Network () {
        users = new User[20];
        userscount = 0;
    }

    public void addUser(String uname) {
        users[userscount++] = new User(uname);
    }

    public void addConnectionToUser (String u1, String u2) {
        if (!checkUser(u1) || !checkUser(u2)) {
            System.out.println("Not a user in Network");
        } else {
            for (int i = 0; i < userscount; i++) {
                if (users[i].getName().equals(u1)) {
                    users[i].addConnection(u2);
                }
            }
        }
    }

    public void addConnectionToUser (String u1, String[] u2) {
        if (!checkUser(u1)) {
            addUser(u1);
        }
        for (int i = 0; i < userscount; i++) {
            if (users[i].getName().equals(u1)) {
                for (int j = 0; j < u2.length; j++) {
                    users[i].addConnection(u2[j]);
                }
            }
        }
    }

    public User getUser(String uname) {
        if (checkUser(uname)) {
            for (int i = 0; i < userscount; i++) {
                if (users[i].getName().equals(uname)) {
                    return users[i];
                }
            }
        }
        return null;
    }

    public String[] getUserConnections(String uname) {
        if (checkUser(uname)) {
            String[] allUserConnections = getUser(uname).getConnections();
            String[] userConnections = new String[getUser(uname).getSize()];
            System.arraycopy(allUserConnections, 0, userConnections, 0, getUser(uname).getSize());
            return userConnections;
        }
        return null;
    }

    public String[] getCommonConnections(String u1, String u2) {
        String[] u1Connections = getUser(u1).getConnections();
        String[] u2Connections = getUser(u2).getConnections();
        String[] commonConnections = new String[10];
        int commonConnectionsCount = 0;
        boolean flag = false;
        for (int i = 0; i < getUser(u1).getSize(); i++) {
            for (int j = 0; j < getUser(u2).getSize(); j++) {
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

    public boolean checkUser(String username) {
        for (int i = 0; i < userscount; i++) {
            if (users[i].getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {

        User[] copyUsers = new User[userscount];
        System.arraycopy(users, 0, copyUsers, 0, userscount);

        for (int i = 0; i < copyUsers.length; i++) {
            for (int j = 0; j < copyUsers.length - 1; j++) {
                if(copyUsers[i].getName().compareTo(copyUsers[j].getName()) < 0) {
                    User us = copyUsers[j];
                    copyUsers[j] = copyUsers[i];
                    copyUsers[i] = us;
                }
            }
        }

        String st = "";
        for (int i = 0; i < userscount; i++) {
            String[] temp = new String[copyUsers[i].getSize()];
            System.arraycopy(copyUsers[i].getConnections(), 0, temp, 0, copyUsers[i].getSize());
            st += copyUsers[i].getName() + ": ";
            st += Arrays.toString(temp);
            if (i < userscount - 1) {
                st += ", ";
            }
        }
        return st;
    }
}


public class Solution {
    public static void main(String[] args) {
        // File file = new File("SocialNetwork.txt");
        Scanner sc = null;
        try{
            sc = new Scanner(System.in);
            Network network = new Network();
            sc.nextLine();
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
                    String[] connArr = network.getUserConnections(inputArr[1]);
                    if (connArr != null){
                        System.out.println(Arrays.toString(connArr));
                    } else {
                        System.out.println("Not a user in Network");
                    }
                    break;
                    case "CommonConnections":
                    String[] commonConnections = network.getCommonConnections(inputArr[1], inputArr[2]);
                    if (commonConnections != null) {
                        System.out.println(Arrays.toString(commonConnections));
                    } else {
                        System.out.println("[]");
                    }
                    break;
                    case "Network":
                    System.out.println(network);
                    break;
                    case "addConnections":
                    network.addConnectionToUser(inputArr[1], inputArr[2]);
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
