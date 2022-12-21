package sample;

import javafx.beans.property.SimpleStringProperty;

import java.io.*;

public class Person {
    private SimpleStringProperty voterName;
    private SimpleStringProperty voterSex;
    private SimpleStringProperty voterPhoneNumber;
    private SimpleStringProperty voterVoterID;
    private SimpleStringProperty voterPassword;
    public static int line = 0;

    public static File file = new File("Credentials");

    public Person(String voterName, String voterSex, String voterPhoneNumber, String voterVoterID, String voterPassword, boolean write) throws FileNotFoundException {
        this.voterName = new SimpleStringProperty(voterName);
        this.voterSex = new SimpleStringProperty(voterSex);
        this.voterPhoneNumber = new SimpleStringProperty(voterPhoneNumber);
        this.voterVoterID = new SimpleStringProperty(voterVoterID);
        this.voterPassword = new SimpleStringProperty(voterPassword);
        if(write) {
            createFolder();
            readFile();
            countLines();
            addData(voterName, voterSex, voterPhoneNumber, voterVoterID, voterPassword);
        }

    }

    public void createFolder(){
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public void readFile(){
        try {
            FileReader fr = new FileReader(file + "\\voters.txt");
        }
        catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }

    public void addData(String voterName, String voterSex, String voterPhoneNumber, String username, String password){
        try{
            RandomAccessFile raf = new RandomAccessFile(file+"\\voters.txt","rw");
            for (int i=0;i<line;i++)
                raf.readLine();
            raf.writeBytes("Name: "+voterName+"\n");
            raf.writeBytes("Gender: "+voterSex+"\n");
            raf.writeBytes("Phone No: "+voterPhoneNumber+"\n");
            raf.writeBytes("Username: "+username+"\n");
            raf.writeBytes("Password: "+password+"\n\n");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void countLines(){
        try{
            line=1;
            RandomAccessFile raf = new RandomAccessFile(file + "\\voters.txt", "rw");
            while(raf.readLine()!=null){
                line++;
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getVoterName() {
        return voterName.get();
    }

    public SimpleStringProperty voterNameProperty() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName.set(voterName);
    }

    public String getVoterSex() {
        return voterSex.get();
    }

    public void setVoterSex(String voterSex) {
        this.voterSex.set(voterSex);
    }

    public SimpleStringProperty getVoterSexProperty() {
        return voterSex;
    }

    public String getVoterPhoneNumber() {
        return voterPhoneNumber.get();
    }

    public SimpleStringProperty voterPhoneNumberProperty() {
        return voterPhoneNumber;
    }

    public void setVoterPhoneNumber(String voterPhoneNumber) {
        this.voterPhoneNumber.set(voterPhoneNumber);
    }

    public String getVoterVoterID() {
        return voterVoterID.get();
    }

    public SimpleStringProperty voterVoterIDProperty() {
        return voterVoterID;
    }

    public void setVoterVoterID(String voterVoterID) {
        this.voterVoterID.set(voterVoterID);
    }

    public String getVoterPassword() {
        return voterPassword.get();
    }

    public SimpleStringProperty voterPasswordProperty() {
        return voterPassword;
    }

    public void setVoterPassword(String voterPassword) {
        this.voterPassword.set(voterPassword);
    }
}
